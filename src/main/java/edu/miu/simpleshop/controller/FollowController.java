package edu.miu.simpleshop.controller;

import edu.miu.simpleshop.domain.*;
import edu.miu.simpleshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.security.Principal;
import java.util.List;

    @Controller
    @RequestMapping("/follow")
    public class FollowController {

        @Autowired
        private FollowService followService;


        @GetMapping("/getSeller")
        public String getSeller(@PathVariable Long id,  Model model){
            model.addAttribute("sellerList",sellerService.getAllFollow());
            return "buyer/followSeller";
        }


        @GetMapping("/followSave")
        public String saveFollower(Model model, @RequestParam("sellerId") Long buyerId, RedirectAttributes redirectAttributes){
            Buyer buyer = buyerService.getById(buyerId);
            Follow follow= new Follow();
            follow.setBuyer(buyer);
            followService.save(follow);
            redirectAttributes.addFlashAttribute("successMessage","You are successfuly following this seller "+ seller.getFullName());
            return "redirect:/follower/getSeller";
        }

       @GetMapping("/getFollowed")
        public String getFollowed(Model model, Long buyerId){
            List<Seller> sellerList;
            Buyer buyer = buyerService.getById(buyerId);
            sellerList=followService.getFollowByBuyerAndSeller(Follow.FollowStatus.follow,buyer.getId());
            //sellerList.forEach(x->System.out.println(x));
            model.addAttribute("sellerList",sellerList);
            return "buyer/followedListSeller";
        }


        //UNFOLLOW
        @GetMapping("/UnfollowSave")
        public String UnfollowSave(Model model, @RequestParam("sellerId") Long sellerId, Long buyerId, Principal principal, RedirectAttributes redirectAttributes){
            String email = principal.getName();
            Buyer buyer = buyerService.findByEmail(email);//email will help us to validate the buyer later
            Seller seller=sellerService.getById(sellerId);
            Follow follow=followService.getFollowByBuyerAndSeller(sellerId,buyerId);
            follow.setFollowStatus(Follow.FollowStatus.unfollow);//i need to have followStatus
            followService.delete(follow);
            redirectAttributes.addFlashAttribute("successMessage","You are successful Unfollowing this seller "+ seller.getFullName());
            return "redirect:/follow/getFollowed";

            }
            }

        }

