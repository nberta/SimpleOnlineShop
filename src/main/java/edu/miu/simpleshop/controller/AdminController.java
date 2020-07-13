package edu.miu.simpleshop.controller;


import edu.miu.simpleshop.domain.Product;
import edu.miu.simpleshop.domain.ProductReview;
import edu.miu.simpleshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductReviewService productReviewService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private UserService userService;

    @PostMapping("/products/{productId}/approve")
    public String approveProduct(@PathVariable("productId") Long productId, Model model){
        Product prod = productService.getProduct(productId);
        model.addAttribute("product", prod);
        return "redirect:/admin/products";
    }

    @GetMapping("/products")
    public String getProducts(Model model) {
        model.addAttribute("products", productService.getAllUnconfirmedProducts());
        return "/admin/products";
    }

    @PostMapping("/products/{productId}/reject")
    public String rejectProduct(@PathVariable("productId") Long productId, Model model) {
        Product prod= productService.getProduct(productId);
        model.addAttribute("product", prod);
        return "redirect:/admin/products";
    }


    @GetMapping("/reviews")
    public String getReview(Model model) {
        model.addAttribute("productReviews", productReviewService.getAllUnconfirmedReviews());
        return "/admin/reviews";
    }

    @PostMapping("/reviews/{productReviewId}/approve")
    public String approveReview(@PathVariable("productReviewId") Long productReviewId, Model model) {
        ProductReview prod= productReviewService.getById(productReviewId);
        model.addAttribute("productReview", prod);
        return "redirect:/admin/reviews";
    }

    @PostMapping("/reviews/{productReviewId}/reject")
    public String rejectReview(@PathVariable("productReviewId") Long productReviewId, Model model) {
        ProductReview prod= productReviewService.getById(productReviewId);
        model.addAttribute("productReview", prod);
        return "redirect:/admin/reviews";
    }
    @GetMapping("/pending-sellers")
    public String pendingSellers(Model model){
        model.addAttribute("pendingSellers", sellerService.getPendingSellers());
        return "/admin/pending-sellers";
    }
    @GetMapping("/pending-buyers")
    public String pendingBuyers(Model model){
        model.addAttribute("pendingBuyers", buyerService.getPendingBuyers());
        return "/admin/pending-buyers";
    }



}
