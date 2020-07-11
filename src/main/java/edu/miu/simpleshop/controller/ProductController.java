package edu.miu.simpleshop.controller;

import edu.miu.simpleshop.domain.Order;
import edu.miu.simpleshop.domain.Product;
import edu.miu.simpleshop.domain.Seller;
import edu.miu.simpleshop.domain.ShoppingCart;
import edu.miu.simpleshop.service.CategoryService;
import edu.miu.simpleshop.service.ProductService;
import edu.miu.simpleshop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private CategoryService categoryService;

    public String mainPage(@RequestParam(value = "category", required = false) Integer category, Model model) {
        List<Product> products = productService.getAllUnconfirmedProducts();
        model.addAttribute("products", products);
        return "index";
    }

    public String addProduct(@ModelAttribute("product") Product product, Model model) throws IOException {
        model.addAttribute("categories", categoryService.allCategories());
        return "seller/add-product";
    }

    @PostMapping("/product/add")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult,
                              @ModelAttribute("seller") Seller seller,
                              Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.allCategories());
            return "seller/add-product";
        }
        MultipartFile productImage = product.getProductImage();
        product.setSeller(seller);

        productService.save(product);

        return "redirect:/seller/my-products";
    }

    @GetMapping("/product/edit/{productId}")
    public String updateProduct(@PathVariable("productId") long productId, Model model) {
        model.addAttribute("categories", categoryService.allCategories());
        Product product = productService.getProduct(productId);
        return "seller/edit-product";
    }

    @DeleteMapping("/product/remove/{productId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("productId") long productId) {
        productService.delete(productId);
    }

    @GetMapping("/{id}")
    public Order getShoppingCartById(@PathVariable Long id) {
        return CartItemService.getById(id);// will check what to return here
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


//    List<Product> products = productService.getAllUnconfirmedProducts(category);