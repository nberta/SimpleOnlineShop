package edu.miu.simpleshop.controller;

import edu.miu.simpleshop.domain.Buyer;
import edu.miu.simpleshop.domain.Product;
import edu.miu.simpleshop.domain.Seller;
import edu.miu.simpleshop.domain.User;
import edu.miu.simpleshop.exception.SessionlessUserException;
import edu.miu.simpleshop.exception.WrongImageException;
import edu.miu.simpleshop.service.BuyerService;
import edu.miu.simpleshop.service.OrderLineService;
import edu.miu.simpleshop.service.ProductService;
import edu.miu.simpleshop.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/sellers")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderLineService orderLineService;

    @GetMapping
    public String getHomepage() {
        return "seller/home";
    }

    @GetMapping("/register")
    public String getRegistrationForm(@ModelAttribute("user") User user) {

        return "seller/sellerRegistrationForm";
    }

    @PostMapping("/register")
    public String save( User user, BindingResult bindingResult, RedirectAttributes attributes, Model model) {
        if (bindingResult.hasErrors()) return "seller/sellerRegistrationForm";
        Seller seller = new Seller();
        seller.setUser(user);
        seller = sellerService.save(seller);
        model.addAttribute("productTop", productService.getAllUnconfirmedProducts());
        attributes.addFlashAttribute("seller", seller);
        return "redirect:/sellers";
    }

    @GetMapping("/profile/{id}")
    public String sellerProfile(@PathVariable Long id, Model model, HttpSession session){
        Buyer buyer = buyerService.getById(Optional
                .ofNullable((Buyer)session.getAttribute("loggedInBuyer"))
                .orElseThrow(SessionlessUserException::new).getId());
        Seller seller = sellerService.getById(id);
        model.addAttribute("followed", buyer.isFollowing(seller));
        model.addAttribute("sellerPro", seller);
        model.addAttribute("reviews",
                seller.getProducts().stream()
                        .flatMap(p -> p.getProductReviews().stream())
                        .collect(Collectors.toList()));
        return "seller/details";
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
    public String sellerProductPage(Model model, HttpSession session) {
        Seller seller = getLoggedInSeller(session);
        model.addAttribute("products", productService.getBySellerId(seller.getId()));
        return "seller/sellerProductsList";
    }

    @DeleteMapping("/product/remove/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId, HttpSession session) {
        Seller seller = getLoggedInSeller(session);
        Product product = productService.getProduct(productId);
        if (product.getSeller().getId().equals(seller.getId()))
            productService.delete(productId);
        return "redirect:/sellers/my-products";
    }

    @GetMapping("/my-orders")
    public String sellerOrders(Model model, HttpSession session) {
        Seller seller = getLoggedInSeller(session);
        model.addAttribute("orderLines", orderLineService.findAllByOrderId(seller.getId()));
        return "seller/orders";
    }

    @GetMapping("/orders/{id}/set-status/shipped")
    public String sellerStatusUpdate(@PathVariable("id") Long id, HttpSession session) {
        Seller seller = getLoggedInSeller(session);
        orderLineService.updateToShipped(id, seller);
        return "redirect:/sellers/my-orders";
    }
    @GetMapping("/orders/{id}/set-status/canceled")
    public String sellerCancelOrder(@PathVariable("id") Long id,
                                    HttpSession session) {
        Seller seller = getLoggedInSeller(session);
        sellerService.cancelOrderLineForSeller(id, seller);
        return "redirect:/sellers/my-orders";
    }

    @GetMapping("/product/add")
    public String addProduct(@ModelAttribute("product") Product product, HttpSession session)  {
        if (!getLoggedInSeller(session).isActive())
            return "redirect:/";
        return "product/productForm";
    }

    @PostMapping("/product/add")
    public String saveProduct( Product product, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes, Model model, HttpSession session) {
        if (!getLoggedInSeller(session).isActive())
            return "product/productForm";

        if (bindingResult.hasErrors()) {
            return "product/productForm";
        }
        MultipartFile productImage = product.getProductImage();
        String uploadLocation = "src\\main\\resources\\static\\images\\";
        String imageName = "";
        if (productImage != null) {
            if (productImage.getContentType().contains("image/")) {
                System.out.println("Image is not null. " + productImage.getContentType());
                try {
                    imageName = UUID.randomUUID().toString() + productImage.getOriginalFilename();
                    imageName = imageName.toLowerCase().replaceAll(" ", "-");
                    System.out.println(uploadLocation + imageName);
                    FileOutputStream output = new FileOutputStream(uploadLocation + imageName);
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
        product.setImageIdentifier("images/" + imageName);
        productService.save(product);
        redirectAttributes.addFlashAttribute("product", product);
        return "redirect:/thisProduct/" + product.getId();
    }

    @GetMapping("/product/edit/{productId}")
    public String updateProduct(@PathVariable("productId") long productId, Model model) {
        Product product = productService.getProduct(productId);
        return "sellers/edit-product";
    }

    private Seller getLoggedInSeller(HttpSession session) {
        return sellerService.getById(Optional.ofNullable((Seller)session.getAttribute("loggedInSeller"))
                    .orElseThrow(SessionlessUserException::new).getId());
    }


}