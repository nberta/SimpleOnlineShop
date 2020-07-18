package edu.miu.simpleshop.controller;

import edu.miu.simpleshop.domain.Buyer;
import edu.miu.simpleshop.domain.Seller;
import edu.miu.simpleshop.service.CategoryService;
import edu.miu.simpleshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String getIndex(Model model){
        Buyer buyer = new Buyer();
        Seller seller = new Seller();
        model.addAttribute("productsHome", productService.getAllUnconfirmedProducts());
        model.addAttribute("categories", categoryService.getAllCategories() );
        model.addAttribute("productsCount", productService.getAllUnconfirmedProducts().size());
        model.addAttribute("buyerRegister", buyer);
        model.addAttribute("sellerRegister", seller);
        //model.addAttribute("productsCount", service.getAllUnconfirmedProducts().size());
        return "index";

    }

    @GetMapping("/signup")
    public String getSignupPage(){
        return "signup";
    }



    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "Username or Password is incorrect !!";
        }
        if (logout != null) {
            errorMessage = "You have been successfully logged out !!";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

    @GetMapping("/denied")
    public String accessDenied(){
        return "accessDenied";
    }


    @GetMapping("/products")
    public String getProductsByCategory(@RequestParam("category") Long id, Model model){
        model.addAttribute("productsFromCat", productService.getByCategoryId(id));
        return "product/singlecategory";
    }

    @GetMapping("/products/productList")
    public String getAllProducts(Model model){
        model.addAttribute("productsAll", productService.getAllUnconfirmedProducts());
        return "product/productList";
    }
}
