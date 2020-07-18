package edu.miu.simpleshop.controller;

import edu.miu.simpleshop.domain.Product;
import edu.miu.simpleshop.exception.WrongImageException;
import edu.miu.simpleshop.service.CategoryService;
import edu.miu.simpleshop.service.ProductService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/products")
public class ProductController {

    /*
        ALL PARTS NEED TO BE REFACTORED INTO OTHER CONTROLLERS
            -addProduct, saveProduct, updateProduct, and deleteProduct will move to SellerController
            -getAll will move to LoginController(?)
    */

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/product/add")
    public String addProduct(@ModelAttribute("product") Product product) throws IOException {
        // model.addAttribute("categories", categoryService.getAllCategories());
        return "product/productForm";
    }

//    //New image upload logic
//    private String saveFile(MultipartFile file,String fileName) throws IOException{
//        final String imagePath = "src/main/resources/static/images/"; //path ... it might be different slash for windows
//        FileOutputStream output = new FileOutputStream(imagePath+fileName);
//        output.write(file.getBytes());
//        return imagePath+fileName;
//    }


    @PostMapping("/product/add")
    public String saveProduct( Product product, BindingResult bindingResult, Model model) {

//        if (bindingResult.hasErrors()) {
//            return "product/productForm";
//        }
//        //prep for image processing
//        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
//        //image identifier set here will also be used to retrieve image in view
//        //consider also including the whole path in the identifier
//        product.setImageIdentifier(product.getName() + RandomStringUtils.randomAlphanumeric(17));
//
//        try {
//            File file = productService.processImage(product, rootDirectory);
//            System.out.println(file.getAbsolutePath());
//        } catch(IncorrectFileTypeException e) {
//            //user entered a file that's not 'image/pgn' in type
//            bindingResult.addError(new FieldError("product","productImage", e.getMessage()));
//            return "product/productForm";
//        }

//        MultipartFile multipartFile = product.getProductImage();
//        product.setImageIdentifier(RandomStringUtils.randomAlphanumeric(17) + ".png");
//        saveFile(multipartFile, product.getImageIdentifier());
//        try{
//            multipartService.store(file);
//            files.add(file.getOriginalFilename());
//            product.setImageIdentifier("img/"+filename);
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        Files.copy(file.getInputStream(), this.rootLocation.resolve(filename),
//                StandardCopyOption.REPLACE_EXISTING);


        MultipartFile productImage = product.getProductImage();
        String uploadLocation = "src/main/resources/static/images/";
        String imageName = "";
        if (productImage != null) {
            if (productImage.getContentType().contains("image/")) {
                System.out.println("Image is not null. " + productImage.getContentType());
                try {
                    imageName = UUID.randomUUID().toString() +productImage.getOriginalFilename();
                    imageName = imageName.toLowerCase().replaceAll(" ", "-");
                    System.out.println(uploadLocation + imageName);
                    FileOutputStream output = new FileOutputStream(uploadLocation+imageName);
                    //System.out.println(uploadLocation + imageName+" 2nd");
                   // productImage.transferTo(new File(uploadLocation+imageName));
                    output.write(productImage.getBytes());
                    System.out.println("Image Uploaded");
                } catch (Exception e) {
                    throw new RuntimeException("Problem on saving product picture.", e);
                }
            } else {
                throw new WrongImageException();
            }
        } else {
            System.out.println("Please select image.");
        }
        product.setImageIdentifier("img/" + imageName);
        productService.save(product);
       //redirectAttributes.addFlashAttribute("product", product);

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
        return "sellers/edit-product";
    }



//    @GetMapping("/category/{id}")
//    public String getCategoriesById(@PathVariable("id") Long id, Model model){
//        model.addAttribute("categoryLink", categoryService.getById(id));
//        return "product/singlecategory";
//    }

    @GetMapping
    public String getProductsByCategory(@RequestParam("category") Long id, Model model){
        model.addAttribute("productsFromCat", productService.getByCategoryId(id));
        return "product/singlecategory";
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