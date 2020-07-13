package edu.miu.simpleshop.controller;


import edu.miu.simpleshop.domain.Buyer;
import edu.miu.simpleshop.domain.Product;
import edu.miu.simpleshop.domain.ProductReview;
import edu.miu.simpleshop.domain.Seller;
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

<<<<<<< Updated upstream
=======
    @Autowired
    private UserService userService;


>>>>>>> Stashed changes
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
    public String getReviews(Model model) {
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

    @GetMapping("/pending-sellers/{id}/reject")
    public String rejectSeller(@PathVariable("id") Long id) {
        sellerService.delete(id);
        return "/admin/pending-sellers";
    }

    @GetMapping("/pending-sellers/{id}/approve")
    public String approveSeller(@PathVariable("id") Long id) {
        Seller seller = sellerService.getById(id);
        seller.setIsActive(true);
        sellerService.save(seller);
        return "/admin/pending-sellers";
    }

    @GetMapping("/pending-buyers")
    public String pendingBuyers(Model model){
        model.addAttribute("pendingBuyers", buyerService.getPendingBuyers());
        return "/admin/pending-buyers";
    }

    @GetMapping("pending-buyers/{id}/reject")
    public String rejectBuyer(@PathVariable("id") Long id) {
        buyerService.delete(id);
        return "/admin/pending-buyers";
    }

    @GetMapping("/pending-buyers/{id}/approve")
    public String approveBuyer(@PathVariable("id") Long id) {
        Buyer buyer = buyerService.getById(id);
        buyer.setIsActive(true);
        buyerService.save(buyer);
        return "/admin/pending-buyers";
    }

}
