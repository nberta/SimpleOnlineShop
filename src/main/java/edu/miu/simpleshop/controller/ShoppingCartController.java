package edu.miu.simpleshop.controller;

import edu.miu.simpleshop.domain.*;
import edu.miu.simpleshop.service.BuyerService;
import edu.miu.simpleshop.service.CartItemService;
import edu.miu.simpleshop.service.ProductService;
import edu.miu.simpleshop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

public class ShoppingCartController {


    @Controller
    @RequestMapping(value = "/cartItem")
    public class CartController {
        private ProductService productService;
        private CategoryService categoryService;// will check if Category is added already
        private BuyerService buyerService;
        private CartItemService cartItemService;


        @Autowired
        public CartController(ProductService productService, CategoryService categoryService,
                              BuyerService buyerService, CartItemService cartItemService) {
            this.productService = productService;
            this.categoryService = categoryService;
            this.buyerService = buyerService;
            this.cartItemService = cartItemService;
        }

        //Find by category and then by id from category
        @RequestMapping("/{category}")
        public String getProductsByCategory(Model model, @PathVariable("category") Long productCategory, @ModelAttribute("item") Item item) {
            try {
                List<Category> categoryList = (List<Category>) categoryService.findAll();
                Category category = categoryService.findById(productCategory);
                List<Product> productList = productService.findAllByCategory(category);
                model.addAttribute(productList);
                model.addAttribute(categoryList);
                if (productList == null || productList.isEmpty()) {
                    throw new NoProductsFoundUnderCategoryException("No Product Found in This category , please Enter the correct Category");
                }

            } catch (Exception ex) {

            }
            return "cart/listProductCategory";
        }


        //SAVE
        @GetMapping(value = "/cartList")
        public String getListInCart(Principal principal, Model model) {
            double total;
            String email = principal.getName();
            Buyer buyer = buyerService.findByEmail(email);

            CartItem cartItem = cartItemService.findByBuyer(buyer);
            System.out.println("#############" + cartItem);
            if (cartItem != null) {
                total = getTotalAmount(cartItem);
                cartItem.setTotalPrice(total);
                cartItemService.save(cartItem);
                //List<CartItem> cartItems = cartItemBuyer.getItem();
                model.addAttribute("cartItemBuyer", cartItem);
                model.addAttribute("cartItemList", cartItems);
            }
            return "cart/shoppingCart";
        }



       /* @GetMapping(value = "/productList")
        public String listProduct(@ModelAttribute("cartItem") CartItem cartItem, Model model) {
            List<Category> categoryList = (List<Category>) categoryService.findAll();
            List<Product> productList = productService.findAllByAvailable(true);
            model.addAttribute(productList);
            model.addAttribute(categoryList);
            return "cart/listProductCategory";
        }*/

      /*  @RequestMapping("/viewDetails")
        public String displayDetailProduct(Model model, @RequestParam("productId") Long productId, @ModelAttribute("product")
                Product product) {
            Product productResult = productService.find(productId);
            List<ProductReview> productReviewList;
            //reviewList=ProductReviewService.findAllByProductAndReviewStatus(productResult, ReviewStatus.isConfirmed);
            model.addAttribute("reviewList",productreviewList);
            ProductReview review = new ProductReview();
            review.setProduct(productResult);
            model.addAttribute(productResult);
            model.addAttribute(review);
            return "cart/addShoppingCart";
        }*/


        //DELETE
        @DeleteMapping(value = "/delete/{id}")
        public String removeItemCart(@PathVariable Long id, Model model) {
            model.addAttribute("deleted", ShoppingCartService.delete(id));
            return "redirect:/shoppingCart/cartList";
        }
    }
}



