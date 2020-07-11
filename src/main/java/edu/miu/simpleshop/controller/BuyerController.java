package edu.miu.simpleshop.controller;

import edu.miu.simpleshop.domain.Buyer;
import edu.miu.simpleshop.domain.User;
import edu.miu.simpleshop.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/buyers")
public class BuyerController {

    @Autowired
    private BuyerService buyerService;

    @GetMapping
    public String getHomepage() {
        return "buyer/home";
    }

    @GetMapping("/register")
    public String getRegistrationForm(@ModelAttribute("user") User user) {
        return "buyer/buyerRegistrationForm";
    }

    @PostMapping("/register")
    public String save(@Valid User user, BindingResult bindingResult, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) return "buyer/buyerRegistrationForm";
        Buyer buyer = new Buyer();
        buyer.setUser(user);
        buyer = buyerService.save(buyer);
        attributes.addFlashAttribute("buyer", buyer);
        return "redirect:/buyers";
    }

    @GetMapping("/edit/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("buyer", buyerService.getById(id));
        return "buyer/edit";
    }

    @PutMapping("/update")
    public String update(@Valid Buyer buyer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "buyer/edit";
        buyerService.save(buyer);
        return "buyer/details";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute("deleted", buyerService.delete(id));
        return "buyer/details";
    }
}
