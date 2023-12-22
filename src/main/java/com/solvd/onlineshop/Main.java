package com.solvd.onlineshop;

import com.solvd.onlineshop.bin.Addresses;
import com.solvd.onlineshop.bin.Products;
import com.solvd.onlineshop.bin.Users;
import com.solvd.onlineshop.dao.AddressesRepositoryImpl;
import com.solvd.onlineshop.dao.UsersRepositoryImpl;
import com.solvd.onlineshop.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(Main.class);
        logger.info("Hello world!");

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");
            Statement statement = connection.createStatement();
            statement.execute("use online_shop");
            ResultSet resultSet = statement.executeQuery("select * from users");

            while (resultSet.next()) {
                logger.info(resultSet.getString("username"));
            }

            // Create an instance of UsersService
            UsersService usersService = new UsersService();

            // Check if the username already exists
            String usernameToCheck = "john_doe";
            boolean isUsernameExists = usersService.isUsernameExists(usernameToCheck);
            logger.info("Username exists: " + isUsernameExists);

            // Example: Create a new user
            Users newUser = new Users(21, "Lucas", "L123", "lucas@gmail.com");

            // Check if the username already exists
            boolean isNewUsernameExists = usersService.isUsernameExists(newUser.getUsername());

            if (!isNewUsernameExists) {
                // Create the user if the username doesn't exist
                usersService.createUser(newUser);
                logger.info("User created successfully: " + newUser.getUsername());
            } else {
                logger.info("Username already exists: " + newUser.getUsername());
            }

            ProductsService productsService = new ProductsService();

            // Example: Create a new product
            Products newProduct = new Products(3, "HP Laptop", "HP Laptop with 16 gigs of ram", 2000);

            // Check if the product already exists
            boolean isProductExists = productsService.isProductExists(newProduct.getId());

            if (!isProductExists) {
                // Create the product if it doesn't exist
                productsService.createProduct(newProduct);
                logger.info("Product created successfully: " + newProduct.getId());
            } else {
                logger.info("Product with ID " + newProduct.getId() + " already exists.");
            }

            // Example: Check if a product with a specific name exists
            String productNameToCheck = "HP Laptop";

            if (isProductExists) {
                logger.info("Product with name '" + productNameToCheck + "' already exists.");
            } else {
                logger.info("Product with name '" + productNameToCheck + "' does not exist.");
            }

            AddressesService addressesService = new AddressesService();

// Example: Create a new address
            Addresses newAddress = new Addresses(3, "1600 Pennsylvania Avenue NW", null, "Washington D.C", "Washington D.C", 37188, 8);

// Check if the user already has an address
            boolean isUserHasAddress = addressesService.isUserHasAddress(newAddress.getUser_id());

            if (!isUserHasAddress) {
                // Create the address if the user doesn't have one
                addressesService.createAddress(newAddress);
                logger.info("Address created successfully: " + newAddress.getId());
            } else {
                logger.info("User with ID " + newAddress.getUser_id() + " already has an address.");
            }

            CartsService cartsService = new CartsService();

            // Example: Create a new cart for a user with user ID 1
            int userIdToCheck = 1;

            // Check if the user already has a cart
            cartsService.createCart(1, userIdToCheck);

            // Check if a user with user ID 1 already has a cart
            boolean isCartExists = cartsService.isCartExists(userIdToCheck);

            if (isCartExists) {
                logger.info("User with ID " + userIdToCheck + " already has a cart.");
            } else {
                logger.info("User with ID " + userIdToCheck + " does not have a cart.");
            }

            // Create an instance of CartItemsService
            CartItemsService cartItemsService = new CartItemsService();

            // Example: Create a new CartItems
            int cartItemIdToCheck = 1;
            int newCartId = 1; // Change the value as needed
            int newProductId = 2; // Change the value as needed
            int newQuantity = 3; // Change the value as needed

            // Check if the cart_id already exists
            cartItemsService.createCartItem(cartItemIdToCheck, newCartId, newProductId, newQuantity);

            // Check if a CartItems with a specific cart_id already exists
            boolean isCartItemsExists = cartItemsService.isCartItemsExists(newCartId);

            if (isCartItemsExists) {
                logger.info("Cart with ID " + newCartId + " already has a CartItems.");
            } else {
                logger.info("Cart with ID " + newCartId + " does not have a CartItems.");
            }

            // Create an instance of CategoriesService
            CategoriesService categoriesService = new CategoriesService();

            // Example: Create a new category
            int categoryIdToCheck = 2;
            String categoryNameToCheck = "Computers";

            // Check if the category already exists
            categoriesService.createCategory(categoryIdToCheck, categoryNameToCheck);

            CouponsService couponsService = new CouponsService();

            // Example: Create a new coupon
            int couponId = 2;
            String couponCode = "SAVE20";
            double discountPercentage = 20.0;
            Date expirationDate = null;
            try {
                expirationDate = new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-31");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            int userId = 8;

            // Check if the user already has a coupon
            couponsService.createCoupon(couponId, couponCode, discountPercentage, expirationDate, userId);

            int orderId = 4;
            int orderUserId = 1;
            double totalPrice = 2000.0;
            Date orderDate = new Date();  // You should set the actual order date here

            // Create an instance of OrdersService
            OrdersService ordersService = new OrdersService();

            try {
                // Create a new order
                ordersService.createOrder(orderId, orderUserId, totalPrice, orderDate);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            OrderDetailsService orderDetailsService = new OrderDetailsService();

            // Example: Create a new order detail
            int orderDetailId = 2;
            int orderOrdersId = 1; // Assuming this order ID exists
            int productId = 2; // Replace with a valid product ID
            int quantity = 1;
            double subtotal = 700;

            // Create the order detail
            orderDetailsService.createOrderDetail(orderDetailId, orderOrdersId, productId, quantity, subtotal);


            PaymentMethodsService paymentMethodsService = new PaymentMethodsService();

            // Example: Create a new payment method
            int paymentMethodId = 2;
            int paymentUserId = 8;
            String cardNumber = "123456789";
            int paymentExpirationDate = 1224;
            int cvv = 123;

            // Create a new payment method and check if the card number already exists
            paymentMethodsService.createPaymentMethod(paymentMethodId, paymentUserId, cardNumber, paymentExpirationDate, cvv);

            PromotionsService promotionsService = new PromotionsService();

            // Example data for the new promotion
            int promotionId = 1;
            String promotionName = "Summer Sale";
            String startDate = "2023-06-01";
            String endDate = "2023-06-30";
            int promotionProductId = 1; // Replace with the actual product ID

            // Create a new promotion
            promotionsService.createPromotion(promotionId, promotionName, startDate, endDate, promotionProductId);

            ReviewsService reviewsService = new ReviewsService();

            // Example data for the new review
            int reviewId = 1;
            int reviewUserId = 1;  // Replace with the actual user ID
            int reviewProductId = 1;  // Replace with the actual product ID
            int rating = 5;
            String comment = "Great product!";
            Date reviewDate = new Date();  // Replace with the actual review date

            // Create a new review
            reviewsService.createReview(reviewId, reviewUserId, reviewProductId, rating, comment, reviewDate);

            ShippingMethodsService shippingMethodsService = new ShippingMethodsService();

            // Example data for the new shipping method
            int shippingMethodId = 2;
            String shippingMethodName = "Standard Shipping";
            double shippingCost = 10.99;
            int shippingOrderId = 3; // Replace with the actual order ID

            // Create a new shipping method
            shippingMethodsService.createShippingMethod(shippingMethodId, shippingMethodName, shippingCost, shippingOrderId);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
