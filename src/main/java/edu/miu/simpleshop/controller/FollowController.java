package edu.miu.simpleshop.controller;

import edu.miu.simpleshop.domain.Buyer;
import edu.miu.simpleshop.domain.Follow;
import edu.miu.simpleshop.domain.Product;
import edu.miu.simpleshop.domain.Seller;
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
        public String getSeller(@PathVariable Long id,  Model model, Principal principal){
            List<Seller> sellerList;
            //String email = principal.getName();
           // Buyer buyer = buyerService.getById(id);
            sellerList = sellerService.getById(...);
            //System.out.println("########################");
            //sellerList.forEach(x->System.out.println(x));
            model.addAttribute("sellerList",sellerList);
            return "buyer/followSeller";
        }

        //it will find list of product by seller
        @GetMapping("ListProduct")
        public String getSellerListProduct(Model model,Principal principal,@RequestParam("sellerId") Long sellerId){
            List<Product> productList;
            String email = principal.getName();
            Seller seller=sellerService.getById(sellerId);
            productList=productService.getBySellerId(sellerId);
            if(productList!=null){
                model.addAttribute("productList",productList);
                            }
            return "buyer/listProductSeller";

        }


        @GetMapping("/followSave")
        public String saveFollower(Model model, @RequestParam("sellerId") Long sellerId, Principal principal, RedirectAttributes redirectAttributes){

            String email = principal.getName();
            Buyer buyer = buyerService.findByEmail(email);
            Seller seller=sellerService.getById(sellerId);
            Follow follow= new Follow();
            follow.setBuyer(buyer);
            follow.setSeller(seller);
            //System.out.println(" follower      "+follow);
            //List<Seller> sellerList= new ArrayList<>();
            //System.out.println(" buyer      "+buyer);
            followerService.save(follow);
            redirectAttributes.addFlashAttribute("successMessage","You are successful following this seller "+ seller.getFullName());
            return "redirect:/follower/getSeller";

        }


        @GetMapping("/getFollowed")
        public String getFollowed(@PathVariable Long id, Model model, Principal principal){
            List<Seller> sellerList;
            //String email = principal.getName();
            Buyer buyer = buyerService.getById(id);
            sellerList=FollowService.find(Follow.FollowStatus.follow,buyer.getId());
            //sellerList.forEach(x->System.out.println(x));
            model.addAttribute("sellerList",sellerList);
            return "buyer/followedListSeller";
        }


        //UNFOLLOW/DELETE
        @GetMapping("/UnfollowSave")
        public String UnfollowSave(Model model, @RequestParam("sellerId") Long sellerId, Principal principal, RedirectAttributes redirectAttributes){
            String email = principal.getName();
            Buyer buyer = buyerService.findByEmail(email);//email will help us to validate the buyer later
            Seller seller=sellerService.getById(sellerId);
            Follow follow=FollowService.findFollowByBuyerAndSeller(sellerId,buyerId);
            //follower.setFollowStatus(Follow.FollowStatus.unfollow);

            FollowService.delete(follow);
            redirectAttributes.addFlashAttribute("successMessage","You are successful Unfollowing this seller "+ seller.getFullName());
            return "redirect:/follow/getFollowed";

        }

    }
}
