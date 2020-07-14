package edu.miu.simpleshop.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String getIndex(){
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
