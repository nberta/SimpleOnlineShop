package edu.miu.simpleshop.controller;

import edu.miu.simpleshop.SimpleshopApplication;
import edu.miu.simpleshop.domain.Category;
import edu.miu.simpleshop.domain.Product;
import edu.miu.simpleshop.exception.IncorrectFileTypeException;
import edu.miu.simpleshop.service.CategoryService;
import edu.miu.simpleshop.service.ProductService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.List;


import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    public String mainPage(@RequestParam(value = "category", required = false) Integer category, Model model) {
        List<Product> products = productService.getAllUnconfirmedProducts();
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/product/add")
    public String addProduct(@ModelAttribute("product") Product product) throws IOException {
        // model.addAttribute("categories", categoryService.getAllCategories());
        return "product/productForm";
    }

    //New image upload logic
    private String saveFile(MultipartFile file,String fileName) throws IOException{
        final String imagePath = "src\\main\\resources\\static\\images\\"; //path ... it might be different slash for windows
        FileOutputStream output = new FileOutputStream(imagePath+fileName);
        output.write(file.getBytes());
        return imagePath+fileName;
    }


    @PostMapping("/product/add")
    public String saveProduct(@ModelAttribute("product") Product product,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes, HttpServletRequest request) throws IOException {

        if (bindingResult.hasErrors()) {
            return "product/productForm";
        }
        //prep for image processing
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        //image identifier set here will also be used to retrieve image in view
        //consider also including the whole path in the identifier
        product.setImageIdentifier(RandomStringUtils.randomAlphanumeric(17) + product.getName());

        try {
            File file = productService.processImage(product, "src\\main\\resources\\static\\images\\");
            System.out.println(file.getAbsolutePath());
        } catch(IncorrectFileTypeException e) {
            //user entered a file that's not 'image/pgn' in type
            bindingResult.addError(new FieldError("product","productImage", e.getMessage()));
            return "product/productForm";
        }
//        MultipartFile multipartFile = product.getProductImage();
//        product.setImageIdentifier(RandomStringUtils.randomAlphanumeric(17) + multipartFile.getOriginalFilename());
//        saveFile(multipartFile, product.getImageIdentifier());

        product = productService.save(product);
        redirectAttributes.addFlashAttribute("product", product);

        return "/seller/singleproduct";
    }

    @GetMapping("/productList")
    public String getAllProducts(Model model){
        model.addAttribute("productsAll", productService.getAllUnconfirmedProducts());
        return "product/productList";
    }


    @GetMapping("/product/edit/{productId}")
    public String updateProduct(@PathVariable("productId") long productId, Model model) {
        Product product = productService.getProduct(productId);
        return "seller/edit-product";
    }

    @DeleteMapping("/product/remove/{productId}")
    public String deleteProduct(@PathVariable("productId") long productId) {
        productService.delete(productId);
        return "redirect:/seller/my-products";
    }

//    @GetMapping("/{id}")
//    public Order getShoppingCartById(@PathVariable Long id) {
////        return CartItemService.getById(id);// will check what to return here
//        return null;
//    }
//
//    @PutMapping("/update")
//    public String update(@Valid ShoppingCart shoppingCart, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) return "shoppingCart/edit";
//        shoppingCartService.save(shoppingCart);
//        return "shoppingCart/details";
//    }

//    @DeleteMapping(value = "/delete/{id}")
//    public String removeItemCart(@PathVariable Long id, Model model) {
//
//
//        model.addAttribute("deleted", shoppingCartService.delete(id));
//        return "shoppingCart/details";
//    }
}


//    List<Product> products = productService.getAllUnconfirmedProducts(category);