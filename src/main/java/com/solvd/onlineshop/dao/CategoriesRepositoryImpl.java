package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.bin.Categories;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class CategoriesRepositoryImpl {
    private static final String CREATE_CATEGORY = "INSERT INTO categories (category_name) VALUES (?)";
    private static final String GET_BY_ID = "SELECT * FROM categories WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE categories SET category_name = ? WHERE id = ?";
    private static final String GET_BY_CATEGORY = "SELECT * FROM categories WHERE category_name = ?";

    Connection connection = ConnectionPool.getConnection();

    private static final Logger logger = LogManager.getLogger(CategoriesRepositoryImpl.class);


    // Insert a new category
    public void addCategory(Categories category) {
        Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                CREATE_CATEGORY,
                Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, category.getCategory_Name());
            preparedStatement.executeUpdate();
            logger.info("Category created successfully: " + category.getId());
        } catch (SQLException e) {
            logger.error("Error creating category: " + category.getId(), e);

        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    // Retrieve a category by ID
    public Categories getCategoryById(int categoryId) {
        Connection connection = ConnectionPool.getConnection();
        Categories category = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)
        ) {
            preparedStatement.setInt(1, categoryId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    category = new Categories(
                            resultSet.getInt("id"),
                            resultSet.getString("category_name")
                    );
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving category: " + categoryId, e);

        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return category;
    }

    // Update category information
    public void updateCategory(Categories category) {
        Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)
        ) {
            preparedStatement.setString(1, category.getCategory_Name());
            preparedStatement.setInt(2, category.getId());
            preparedStatement.executeUpdate();
            logger.info("Category updated successfully: " + category.getId());
        } catch (SQLException e) {
            logger.error("Error updating category: " + category.getId(), e);

        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    // Retrieve a category by name
    public Categories getCategoryByName(String categoryName) {
        Connection connection = ConnectionPool.getConnection();
        Categories category = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_CATEGORY)
        ) {
            preparedStatement.setString(1, categoryName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    category = new Categories(
                            resultSet.getInt("id"),
                            resultSet.getString("category_name")
                    );
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving category by name: " + categoryName, e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return category;
    }

}