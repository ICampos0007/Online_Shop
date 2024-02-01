package com.solvd.onlineshop.service.impl;

import com.solvd.onlineshop.bin.Categories;
import com.solvd.onlineshop.dao.CategoriesRepositoryImpl;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CategoriesService {

    private static final Logger logger = LogManager.getLogger(CategoriesService.class);
    private CategoriesRepositoryImpl categoriesRepository;

    public CategoriesService() {
        this.categoriesRepository = new CategoriesRepositoryImpl();
    }

    // Create a new category and check if it already exists
    public void createCategory(int id, String categoryName) throws SQLException {
        // Check if the category already exists
        Categories existingCategory = categoriesRepository.getCategoryByName(categoryName);

        if (existingCategory == null) {
            // Category does not exist, create a new one
            Categories newCategory = new Categories(id, categoryName);

            // Create the new category
            categoriesRepository.addCategory(newCategory);
            logger.info("Category created successfully: " + newCategory.getId());
        } else {
            // Category already exists
            logger.info("Category with name '" + categoryName + "' already exists (ID: " + existingCategory.getId() + ")");
        }
    }
}
