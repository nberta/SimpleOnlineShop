package edu.miu.simpleshop.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String getIndex(){
        return "index";
    }

    @GetMapping("/login")
    public String showLogin(){return "login";}

    //@PostMapping("/login")
}
