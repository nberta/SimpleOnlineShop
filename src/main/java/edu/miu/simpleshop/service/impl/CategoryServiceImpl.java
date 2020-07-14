package edu.miu.simpleshop.service.impl;

import edu.miu.simpleshop.domain.Category;
import edu.miu.simpleshop.domain.Product;
import edu.miu.simpleshop.repository.CategoryRepository;
import edu.miu.simpleshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;


    @Override
    public List<Category> getAllCategories() {
        List<Category> categoryList;
        categoryList = repository.findAll();
        return categoryList;

    }

    @Override
    public Category save(Category category) {
        return repository.save(category);
    }

    @Override
    public Category getById(Long id) {
        return repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

  /*  @Override
    public Category getById(Long id) {
        return repository.findById(id).get();

    }*/

    @Override
    public Category update(Long id) {
        Category category1 = getById(id);
        repository.save(category1);
        return category1;
    }

    /*@Override
    public Category delete(Long id) {
        Category category = getById(id);
        repository.delete(category);
        return category;
    }*/


        @Override
        public Category delete(Product product) {
            System.out.println("*******************inside delete " + product);
            Category category = repository.findById(product.getCategory().getId()).get();
            //Category category = repository.findById(product.getCategory().getCategoryId()).get();
            System.out.println("*******************inside delete " +category);
            category.getProducts().remove(product);
            repository.flush();
            return category;
        }
    }


    //Category getById(Long id);
    //List<Category> getAllCategories();
    //Category save(Category category);
    //Category update(Long id);
    //Category delete(Long id);



