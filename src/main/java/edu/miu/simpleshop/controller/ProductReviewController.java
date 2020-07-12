package edu.miu.simpleshop.controller;

import edu.miu.simpleshop.domain.Buyer;
import edu.miu.simpleshop.domain.ProductReview;
import edu.miu.simpleshop.service.ProductReviewService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/productReview")
public class ProductReviewController {

    @Autowired
    ProductReviewService productReviewService;

    public String getPageReview(){
        return "product/review";
    }

    //create review
    @PostMapping("/create-review/{orderProductId}")
    public String createReview(@Valid @ModelAttribute ProductReview review, BindingResult bindingResult,
                               Buyer buyer, Model model) throws NotFoundException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("review", review);
            return "create-review";
        }
        productReviewService.save(review);
        return "redirect:/buyer/order/list";
    }


    //read all review
    @GetMapping("/created-reviews")
    public String readReviews(Model model){
        model.addAttribute("createdReviews", productReviewService.getAllUnconfirmedReviews());
        return "admin/created-reviews";
    }
    //update review
    @PutMapping("/post-review/{reviewId}")
    public String updateReview(@Valid ProductReview review, BindingResult bindingResult)  {
        productReviewService.save(review);
        return "review";
    }

    @DeleteMapping("/delete/{reviewId}")
    public String declineReview(@PathVariable Long id, Model model) {
        model.addAttribute("deleted", productReviewService.delete(id));
        return "review/details";
    }
}
