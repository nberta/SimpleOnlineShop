package edu.miu.simpleshop.controller;

import edu.miu.simpleshop.domain.Seller;
import edu.miu.simpleshop.domain.User;
import edu.miu.simpleshop.domain.enums.Role;
import edu.miu.simpleshop.service.ProductService;
import edu.miu.simpleshop.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String getHomepage() {
        return "seller/home";
    }

    @GetMapping("/register")
    public String getRegistrationForm(@ModelAttribute("user") User user) {
        return "seller/sellerRegistrationForm";
    }

    @PostMapping("/register")
    public String save(@Valid User user, BindingResult bindingResult, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) return "seller/sellerRegistrationForm";
        Seller seller = new Seller();
        user.addRole(Role.SELLER);
        seller.setUser(user);
        seller = sellerService.save(seller);
        attributes.addFlashAttribute("seller", seller);
        return "redirect:/seller";
    }

    @GetMapping("/edit/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("seller", sellerService.getById(id));
        return "seller/edit";
    }

    @PutMapping("/update")
    public String update(@Valid Seller seller, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "seller/edit";
        sellerService.save(seller);
        return "seller/details";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute("deleted", sellerService.delete(id));
        return "seller/details";
    }


    @GetMapping("/register/{id}")
    public String getRegistrationForm(@PathVariable Long id) {
        return "seller/sellerRegistrationForm";
    }

    @GetMapping("/my-products")
    public String sellerProductPage(@ModelAttribute("seller") Seller seller, Model model) {
        model.addAttribute("products", productService.getBySellerId(seller.getId()));
        return "seller/productsDisplay";
    }

}