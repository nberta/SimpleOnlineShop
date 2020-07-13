package edu.miu.simpleshop.service.impl;

import edu.miu.simpleshop.domain.Category;
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
        return repository.findAll();
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

    @Override
    public Category update(Long id) {
        Category category1 = getById(id);
        repository.save(category1);
        return category1;
    }

    @Override
    public Category delete(Long id) {
        Category category = getById(id);
        repository.delete(category);
        return category;
    }
}
