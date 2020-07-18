package edu.miu.simpleshop.controller;

import edu.miu.simpleshop.domain.Product;
import edu.miu.simpleshop.domain.Seller;
import edu.miu.simpleshop.domain.User;
import edu.miu.simpleshop.service.OrderLineService;
import edu.miu.simpleshop.service.ProductService;
import edu.miu.simpleshop.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/sellers")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderLineService orderLineService;

    @GetMapping
    public String getHomepage() {
        return "seller/home";
    }

    @GetMapping("/register")
    public String getRegistrationForm(@ModelAttribute("user") User user) {

        return "seller/sellerRegistrationForm";
    }

    @PostMapping("/register")
    public String save( User user, BindingResult bindingResult, RedirectAttributes attributes, Model model) {
        if (bindingResult.hasErrors()) return "seller/sellerRegistrationForm";
        Seller seller = new Seller();
        seller.setUser(user);
        seller = sellerService.save(seller);
        model.addAttribute("productTop", productService.getAllUnconfirmedProducts());
        attributes.addFlashAttribute("seller", seller);
        return "redirect:/sellers";
    }

    @GetMapping("/profile/{id}")
    public String sellerProfile(@PathVariable Long id, Model model){
        model.addAttribute("sellerPro", sellerService.getById(id));
        return "seller/details";
    }

    @GetMapping("/edit/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("seller", sellerService.getById(id));
        return "seller/edit";
    }

    @PutMapping("/update")
    public String update(@Valid Seller seller, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "seller/edit";
        sellerService.save(seller);
        return "seller/details";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute("deleted", sellerService.delete(id));
        return "seller/details";
    }

    @GetMapping("/register/{id}")
    public String getRegistrationForm(@PathVariable Long id) {
        return "seller/sellerRegistrationForm";
    }

    @GetMapping("/my-products")
    public String sellerProductPage(Model model, HttpSession session) {
        Seller seller = (Seller)session.getAttribute("loggedInSeller");
        model.addAttribute("products", productService.getBySellerId(seller.getId()));
        return "seller/singleproduct";
    }

    @DeleteMapping("/product/remove/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId, HttpSession session) {
        Seller seller = (Seller)session.getAttribute("loggedInSeller");
        Product product = productService.getProduct(productId);
        if (product.getSeller().getId().equals(seller.getId()))
            productService.delete(productId);
        return "redirect:/sellers/my-products";
    }

    @GetMapping("/my-orders")
    public String sellerOrders(Model model, HttpSession session) {
        Seller seller = (Seller)session.getAttribute("loggedInSeller");
        model.addAttribute("orderLines", orderLineService.findAllByOrderId(seller.getId()));
        return "seller/orders";
    }

    @GetMapping("/orders/{id}/set-status/shipped")
    public String sellerStatusUpdate(@PathVariable("id") Long id, HttpSession session) {
        Seller seller = (Seller)session.getAttribute("loggedInSeller");
        orderLineService.updateToShipped(id, seller);
        return "redirect:/sellers/my-orders";
    }
    @GetMapping("/orders/{id}/set-status/canceled")
    public String sellerCancelOrder(@PathVariable("id") Long id,
                                    HttpSession session) {
        Seller seller = (Seller)session.getAttribute("loggedInSeller");
        sellerService.cancelOrderLineForSeller(id, seller);
        return "redirect:/sellers/my-orders";
    }

}