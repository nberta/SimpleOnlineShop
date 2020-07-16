package edu.miu.simpleshop.controller;


import edu.miu.simpleshop.domain.Product;
import edu.miu.simpleshop.service.CategoryService;
import edu.miu.simpleshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ProductService service;

    @Autowired
    CategoryService categoryService;


    @GetMapping("/")
    public String getIndex(Model model, Model model1, Model model3){
        model.addAttribute("productsHome",service.getAllUnconfirmedProducts());
        model1.addAttribute("categories", categoryService.getAllCategories().size());
        model3.addAttribute("productsCount", service.getAllUnconfirmedProducts().size());
        return "index";
    }

    @GetMapping("/login")
    public String showLogin(){return "login";}

//    @PostMapping("/login")
//    public String login(@ModelAttribute("username") String name, @ModelAttribute("password") String password) {
//        System.out.println(name + password);
//        return "";
//    }
}
