package edu.miu.simpleshop.controller;

import edu.miu.simpleshop.domain.*;
import edu.miu.simpleshop.service.BuyerService;
import edu.miu.simpleshop.service.FollowService;
import edu.miu.simpleshop.service.ProductService;
import edu.miu.simpleshop.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.security.Principal;
import java.util.List;


public class FollowController {

    @Controller
    @RequestMapping("/follow")
    public class FollowerController {
        private FollowService followService;
        private SellerService sellerService;
        private BuyerService buyerService;
        private ProductService productService;

        @Autowired
        public FollowerController(ProductService productService,FollowService followService, SellerService sellerService, BuyerService buyerService) {
            this.followService = followService;
            this.sellerService = sellerService;
            this.buyerService = buyerService;
            this.productService=productService;
        }

        @GetMapping("/getSeller")
        public String getSeller(@PathVariable Long id,  Model model){
            model.addAttribute("sellerList",sellerService.getAllFollow());
            return "buyer/followSeller";
        }


        @GetMapping("/followSave")
        public String saveFollower(Model model, @RequestParam("sellerId") Long sellerId, Principal principal, RedirectAttributes redirectAttributes){
            String email = principal.getName();
            Buyer buyer = buyerService.getByEmail(email);
            Seller seller=sellerService.getById(sellerId);
            Follow follow= new Follow();
            follow.setBuyer(buyer);
            follow.setSeller(seller);
            followService.save(follow);
            redirectAttributes.addFlashAttribute("successMessage","You are successfuly following this seller "+ seller.getFullName());
            return "redirect:/follower/getSeller";

        }

        @GetMapping("/getFollowed")
        public String getFollowed(Model model,Principal principal){
            List<Seller> sellerList;
            String email = principal.getName();
            Buyer buyer = buyerService.findByEmail(email);
            sellerList=followService.findAllByProductAndReviewStatus(Follower.FollowerStatus.follow,buyer.getId());
            //sellerList.forEach(x->System.out.println(x));
            model.addAttribute("sellerList",sellerList);
            return "buyer/followedListSeller";
        }


        //UNFOLLOW
        @GetMapping("/UnfollowSave")
        public String UnfollowSave(Model model, @RequestParam("sellerId") Long sellerId, Principal principal, RedirectAttributes redirectAttributes){
            String email = principal.getName();
            Buyer buyer = buyerService.findByEmail(email);//email will help us to validate the buyer later
            Seller seller=sellerService.getById(sellerId);
            Follow follow=FollowService.findFollowByBuyerAndSeller(sellerId,buyerId);
            follow.setFollowStatus(Follow.FollowStatus.unfollow);//i need to have followStatus
            FollowService.delete(follow);
            redirectAttributes.addFlashAttribute("successMessage","You are successful Unfollowing this seller "+ seller.getFullName());
            return "redirect:/follow/getFollowed";

            }
            }

        }

