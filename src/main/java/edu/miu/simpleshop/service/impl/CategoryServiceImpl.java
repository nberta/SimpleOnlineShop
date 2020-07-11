package edu.miu.simpleshop.service.impl;

import edu.miu.simpleshop.domain.Category;
import edu.miu.simpleshop.repository.CategoryRepository;
import edu.miu.simpleshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> allCategories() {
        return categoryRepository.findAll();
    }
}
