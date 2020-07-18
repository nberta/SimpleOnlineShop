package edu.miu.simpleshop.api;

import edu.miu.simpleshop.domain.Product;
import edu.miu.simpleshop.service.ProductService;
import javassist.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class MainApi {

    @Autowired
    private ProductService service;

    @GetMapping("/getAll")
    public List<Product> getAllAdminsByStatus() {
        return service.getAllUnconfirmedProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id)throws NotFoundException{
        return service.getById(id);
    }


}
