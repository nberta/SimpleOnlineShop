
package edu.miu.simpleshop.controller;


import edu.miu.simpleshop.domain.*;
import edu.miu.simpleshop.domain.enums.Role;
import edu.miu.simpleshop.exception.SessionlessUserException;
import edu.miu.simpleshop.service.*;
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
import java.util.Optional;

@Controller
@RequestMapping("/buyers")
public class BuyerController {

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductReviewService productReviewService;

    @Autowired
    private ShoppingCartService shoppingCartService;


    @GetMapping("/register")
    public String getRegistrationForm(@ModelAttribute("buyer") Buyer buyer, Model model) {
        //This is to shop Top Products on registration page sidebar!
        model.addAttribute("productTop", productService.getAllUnconfirmedProducts());
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
        Buyer buyer = Optional.ofNullable((Buyer)session.getAttribute("loggedInBuyer"))
                .orElseThrow(SessionlessUserException::new);
        model.addAttribute("follows", buyerService.getFollowedSellersForBuyer(buyer.getId()));
        return "buyer/mysellers";
    }

    @GetMapping("/following/{id}/follow")
    public String followSeller(@PathVariable("id") Long id, Model model, HttpSession session) {
        Buyer buyer = Optional.ofNullable((Buyer)session.getAttribute("loggedInBuyer"))
                .orElseThrow(SessionlessUserException::new);
        buyerService.followSeller(buyer, id);
        model.addAttribute("follows", buyerService.getFollowedSellersForBuyer(buyer.getId()));
        return "buyer/mysellers";
    }

    @GetMapping("/following/{id}/unfollow")
    public String unfollowSeller(@PathVariable("id") Long id, Model model, HttpSession session) {
        Buyer buyer = Optional.ofNullable((Buyer)session.getAttribute("loggedInBuyer"))
                .orElseThrow(SessionlessUserException::new);
        buyerService.unfollowSeller(buyer, id);
        model.addAttribute("follows", buyerService.getFollowedSellersForBuyer(buyer.getId()));
        return "buyer/mySellers";
    }


    //shopping cart and order processing
    @GetMapping("/my-cart")
    public String loadShoppingCart(Model model,
                                   @ModelAttribute("errorMessage") String errorMessage, HttpSession session) {
        Buyer buyer = Optional.ofNullable((Buyer)session.getAttribute("loggedInBuyer"))
                .orElseThrow(SessionlessUserException::new);
        model.addAttribute("cartItems", buyer.getShoppingCart().getCartItems());
        model.addAttribute("itemsForCheckOut", new ArrayList<CartItem>());
        return "buyer/shoppingCart";
    }

    @PostMapping("/cart/add/{id}")
    public String addToCart(@PathVariable("id") Long productId,
                            @RequestParam("quantity") Integer quantity, HttpSession session) {
        Buyer buyer = Optional.ofNullable((Buyer)session.getAttribute("loggedInBuyer"))
                .orElseThrow(SessionlessUserException::new);
        buyer.getShoppingCart().addCartItem(new CartItem(productService.getProduct(productId), quantity));

        //redirect to whatever page they were on
        return "redirect:/buyers/my-cart";
    }

    @PostMapping("/my-cart/remove/{id}")
    public String removeFromCart(@PathVariable("id") Long cartItemId, HttpSession session) {
        Buyer buyer = Optional.ofNullable((Buyer)session.getAttribute("loggedInBuyer"))
                .orElseThrow(SessionlessUserException::new);
        shoppingCartService.removeCartItem(cartItemId, buyer);
        return "redirect:/buyers/my-cart";
    }
    @PostMapping("/my-cart/make-purchase")
    public String makePurchase(RedirectAttributes redirectAttributes, HttpSession session) {
        Buyer buyer = Optional.ofNullable((Buyer)session.getAttribute("loggedInBuyer"))
                .orElseThrow(SessionlessUserException::new);
        buyer = buyerService.getById(buyer.getId());
        List<CartItem> itemsForCheckOut = buyer.getShoppingCart().getCartItems();
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
    @GetMapping("/orders")
    public String orderList(Model model, HttpSession session) {
        Buyer buyer = Optional.ofNullable((Buyer)session.getAttribute("loggedInBuyer"))
                .orElseThrow(SessionlessUserException::new);
        buyer = buyerService.getById(buyer.getId());
        model.addAttribute("orders", buyer.getOrders());
        return "buyer/orders";
    }

    @GetMapping("/orders/{id}")
    public String orderDetails(@PathVariable("id") Long orderId, Model model, HttpSession session) {
        Buyer buyer = Optional.ofNullable((Buyer)session.getAttribute("loggedInBuyer"))
                .orElseThrow(SessionlessUserException::new);
        for (Order o : buyer.getOrders())
            if (o.getId().equals(orderId))
                model.addAttribute("order", o);
        return "order/details";
    }

    //reviews
    @GetMapping("/product/{id}/review")
    public String getReviewPageForProduct(@ModelAttribute("productReview") ProductReview productReview,
                                          @PathVariable("id") Long id, Model model) {
        productReview.setProduct(productService.getProduct(id));
        model.addAttribute("productId", productReview.getProduct().getId());
        return "product/create-review";
    }

    @PostMapping("/product/{id}/review")
    public String createReview(@Valid ProductReview productReview, BindingResult bindingResult,
                               @PathVariable("id") Long id, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            productReview.setProduct(productService.getProduct(id));
            model.addAttribute("productId", productReview.getProduct().getId());
            return "product/create-review";
        }
        Buyer buyer = Optional.ofNullable((Buyer)session.getAttribute("loggedInBuyer"))
                .orElseThrow(SessionlessUserException::new);
        productReview.setBuyer(buyer);
        productReview.setProduct(productService.getProduct(id));
        productReviewService.save(productReview);
        return "redirect:/buyers/buyer/orders";
    }

}

