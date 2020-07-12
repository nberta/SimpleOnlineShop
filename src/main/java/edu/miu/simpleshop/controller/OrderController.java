package edu.miu.simpleshop.controller;

import edu.miu.simpleshop.domain.Order;
import edu.miu.simpleshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/getall")
    public String getAllOrders(@ModelAttribute("newOrder") Order order, Model model){
        model.addAttribute("orders", orderService.getAllOrders());
        return "order/details";
    }

    //Need OrderLine Service as well

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id){
        return orderService.getById(id);
    }

    @GetMapping("/edit/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("seller", orderService.getById(id));
        return "order/edit";
    }

    @PutMapping("/update")
    public String update(@Valid Order order, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "order/edit";
        orderService.save(order);
        return "order/details";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute("deleted", orderService.delete(id));
        return "order/details";
    }


}
