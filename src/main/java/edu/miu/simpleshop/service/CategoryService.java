package edu.miu.simpleshop.service;

import edu.miu.simpleshop.domain.Category;
import edu.miu.simpleshop.domain.Product;

import java.util.List;

public interface CategoryService {

    Category getById(Long id);
    List<Category> getAllCategories();
    Category save(Category category);
    Category update(Long id);
    //Category delete(Long id);
    Category delete(Product product);
}
