package edu.miu.simpleshop.controller;

import edu.miu.simpleshop.domain.Order;
import edu.miu.simpleshop.domain.OrderLine;
import edu.miu.simpleshop.domain.enums.OrderStatus;
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


/*
    //method seems unnecessary
    //need to find order related to buyer only
    @GetMapping("/all")
    public String getAllOrders(@ModelAttribute("newOrder") Order order, Model model){
        model.addAttribute("orders", orderService.getAllOrders());
        return "order/details";
    }
*/

    //Need OrderLine Service as well

    @GetMapping("/order/{id}")
    public Order getOrderById(@PathVariable Long id){
        return orderService.getById(id);
    }

/*
    @GetMapping("/edit/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("seller", orderService.getById(id));
        return "order/edit";
    }
*/
    // would only be useful if we allow changing address after purchase
    @PutMapping("/update")
    public String update(@Valid Order order, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "order/edit";
        orderService.save(order);
        return "order/details";
    }

/*
    //unnecessary for our model
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute("deleted", orderService.delete(id));
        return "order/details";
    }
*/

    //refactor into buyer's controller
    @PutMapping("cancel/{id}")
    public String cancelWholeOrder(@PathVariable Long id) {
        orderService.cancel(id);
        return "redirect:/buyers/my-orders";
    }

}
