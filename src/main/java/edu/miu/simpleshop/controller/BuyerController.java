
package edu.miu.simpleshop.controller;


import edu.miu.simpleshop.domain.*;
import edu.miu.simpleshop.domain.enums.Role;
import edu.miu.simpleshop.service.BuyerService;
import edu.miu.simpleshop.service.OrderService;
import edu.miu.simpleshop.service.UserService;
import edu.miu.simpleshop.util.PdfReceiptDownload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/buyers")
public class BuyerController {

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;


    @GetMapping("/register")
    public String getRegistrationForm(@ModelAttribute("buyer") Buyer buyer) {
        return "buyer/register";
    }

    @PostMapping("/register")
    public String save(@Valid Buyer buyer, RedirectAttributes attributes, Model model) {
//        if (bindingResult.hasErrors()) return "register";
        //buyer.setUser(userService.save(buyer.getUser()));
        buyer = buyerService.save(buyer);
        model.addAttribute("buyer", buyer);
        attributes.addFlashAttribute("buyer", buyer);
        return "buyer/details";
    }

    @GetMapping("/edit/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("buyer", buyerService.getById(id));
        return "buyer/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("buyer") Buyer buyer,
                         BindingResult bindingResult, RedirectAttributes attributes, Model model) {
        buyer = buyerService.update(buyer, id);
        model.addAttribute("buyer", buyer);
        attributes.addFlashAttribute("buyer", buyer);
        return "buyer/details";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute("deleted", buyerService.delete(id));
        return "buyer/details";
    }

    //Follows
    @GetMapping("/following")
    public String getSellersFollowed(Model model, HttpSession session) {
        Buyer buyer = (Buyer)session.getAttribute("loggedInBuyer");
        model.addAttribute("follows", buyerService.getFollowedSellersForBuyer(buyer.getId()));
        return "buyer/mysellers";
    }

    @GetMapping("/following/{id}/follow")
    public String followSeller(@PathVariable("id") Long id, Model model, HttpSession session) {
        Buyer buyer = (Buyer)session.getAttribute("loggedInBuyer");
        buyerService.followSeller(buyer, id);
        model.addAttribute("follows", buyerService.getFollowedSellersForBuyer(buyer.getId()));
        return "buyer/mysellers";
    }

    @GetMapping("/following/{id}/unfollow")
    public String unfollowSeller(@PathVariable("id") Long id, Model model, HttpSession session) {
        Buyer buyer = (Buyer)session.getAttribute("loggedInBuyer");
        buyerService.unfollowSeller(buyer, id);
        model.addAttribute("follows", buyerService.getFollowedSellersForBuyer(buyer.getId()));
        return "buyer/mySellers";
    }


    //shopping cart and order processing
    @GetMapping("/my-cart")
    public String loadShoppingCart(Model model,
                                   @ModelAttribute("errorMessage") String errorMessage, HttpSession session) {
        Buyer buyer = (Buyer)session.getAttribute("loggedInBuyer");
        ShoppingCart cart = buyerService.getShoppingCartForBuyer(buyer);
        model.addAttribute("cartItems", cart.getCartItems());
        model.addAttribute("itemsForCheckOut", new ArrayList<CartItem>());
        return "buyer/shoppingCart";
    }

    @PostMapping("/my-cart/make-purchase")
    public String makePurchase(@ModelAttribute("itemsForCheckOut")List<CartItem> itemsForCheckOut,
                               RedirectAttributes redirectAttributes, HttpSession session) {
        Buyer buyer = (Buyer)session.getAttribute("loggedInBuyer");
        buyer = buyerService.getById(buyer.getId());
        if (itemsForCheckOut.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Order failed. Attempted to make purchase without any items");
            return "redirect:/buyers/my-cart";
        }
        Order order;
        Collection<CartItem> refuse = new ArrayList<>();
        if (orderService.canMakeOrder(itemsForCheckOut, refuse)) {
            order = orderService.prepareOrder(itemsForCheckOut, buyer);
            redirectAttributes.addFlashAttribute("order", order);
            return "redirect:/buyers/my-orders/order-successful";
        }
        else {
            Collection<CartItem> current = buyer.getShoppingCart().getCartItems();
            current.removeIf(refuse::contains);
            buyerService.save(buyer);
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Order failed. There were products in your cart that were not available");
            return "redirect:/buyers/my-cart";
        }
    }

    @GetMapping("/my-orders/order-successful")
    public String successfulOrder(@ModelAttribute("order") Order order) {
        return "order/details";
    }


    @GetMapping(value = "/receipt/download/{orderId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> downloadReceipt(@PathVariable("orderId") long orderId) {
        Order orderForPDF = orderService.getById(orderId);
        ByteArrayInputStream bis = PdfReceiptDownload.Report(orderForPDF);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
    //Check Order History
    @GetMapping("/buyer/orders")
    public String orderList(Model model, HttpSession session) {
        Buyer buyer = (Buyer)session.getAttribute("loggedInBuyer");
        model.addAttribute("orders", buyer.getOrders());
        return "buyer/orders";
    }
}

