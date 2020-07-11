package edu.miu.simpleshop.controller;

import edu.miu.simpleshop.domain.*;
import edu.miu.simpleshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;


@Controller
@RequestMapping(value = "/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;


      @GetMapping("/{id}")
       public Order getShoppingCartById(@PathVariable Long id){
        return CartItemService.getById(id);//need cartItemService
       }

        @PutMapping("/update")
        public String update(@Valid ShoppingCart shoppingCart, BindingResult bindingResult) {
            if (bindingResult.hasErrors()) return "shoppingCart/edit";
            shoppingCartService.save(shoppingCart);
            return "shoppingCart/details";
        }

        @DeleteMapping(value = "/delete/{id}")
        public String removeItemCart(@PathVariable Long id, Model model) {
            model.addAttribute("deleted", shoppingCartService.delete(id));
            return "shoppingCart/details";
        }


}




