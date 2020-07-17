package edu.miu.simpleshop.controller;

import edu.miu.simpleshop.domain.Buyer;
import edu.miu.simpleshop.domain.Seller;
import edu.miu.simpleshop.service.BuyerService;
import edu.miu.simpleshop.service.CategoryService;
import edu.miu.simpleshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private ProductService service;

    @Autowired
    private CategoryService categoryService;



    @GetMapping("/")
    public String getIndex(Model model){
        Buyer buyer = new Buyer();
        Seller seller = new Seller();
        model.addAttribute("productsHome",service.getAllUnconfirmedProducts());
        model.addAttribute("categories", categoryService.getAllCategories() );
        model.addAttribute("productsCount", service.getAllUnconfirmedProducts().size());
        model.addAttribute("buyerRegister", buyer);
        model.addAttribute("sellerRegister", seller);
        //model.addAttribute("productsCount", service.getAllUnconfirmedProducts().size());
        return "index";

    }

    @GetMapping("/signup")
    public String getSignupPage(){
        return "signup";
    }



    @GetMapping(value = "/login")
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
}
