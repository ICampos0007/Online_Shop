package com.solvd.onlineshop;

import com.solvd.onlineshop.bin.*;
import com.solvd.onlineshop.dao.mybatisimpl.ProductsRepositoryMyBatisImpl;
import com.solvd.onlineshop.dao.mybatisimpl.UsersRepositoryMyBatisImpl;
import com.solvd.onlineshop.dao.persistence.PaymentMethodsRepository;
import com.solvd.onlineshop.dao.persistence.ProductsRepository;
import com.solvd.onlineshop.dao.persistence.UsersRepository;
import com.solvd.onlineshop.service.OrderService;
import com.solvd.onlineshop.service.PaymentMethodService;
import com.solvd.onlineshop.service.impl.*;
import com.solvd.onlineshop.service.impl.PaymentMethodsImpl;
import com.solvd.onlineshop.service.impl.ProductsServiceImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(Main.class);
        logger.info("Hello world!");

        try (InputStream is = Resources.getResourceAsStream("mybatis-config.xml")) {

            // Initialize SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

            // Create an instance of UsersService
            UsersServiceImpl usersService = new UsersServiceImpl(sqlSessionFactory);


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

            // Initialize SqlSessionFactory

//            ProductsService productsService = new ProductsService(sqlSessionFactory);
//
//            // Example: Create a new product
//            Products newProduct = new Products(3, "HP Laptop", "HP Laptop with 16 gigs of ram", 2000);
//
//            // Check if the product already exists
//            boolean isProductExists = productsService.isProductExists(newProduct.getId());
//
//            if (!isProductExists) {
//                // Create the product if it doesn't exist
//                productsService.createProduct(newProduct);
//                logger.info("Product created successfully: " + newProduct.getId());
//            } else {
//                logger.info("Product with ID " + newProduct.getId() + " already exists.");
//            }
//
//            // Example: Check if a product with a specific name exists
//            String productNameToCheck = "HP Laptop";
//
//            if (isProductExists) {
//                logger.info("Product with name '" + productNameToCheck + "' already exists.");
//            } else {
//                logger.info("Product with name '" + productNameToCheck + "' does not exist.");
//            }

            AddressesServiceImpl addressesService = new AddressesServiceImpl(sqlSessionFactory);

// Example: Create a new address
            Addresses newAddress = new Addresses(3, "1600 Pennsylvania Avenue NW", null, "Washington D.C", "Washington D.C", 37188, 8);

// Check if the user already has an address
            boolean isUserHasAddress = addressesService.isUserHasAddress(newAddress.getUser_id());

            if (!isUserHasAddress) {
                // Create the address if the user doesn't have one
//                addressesService.createAddress(newAddress, 0);
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

            CouponsServiceImpl couponsService = new CouponsServiceImpl();

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
//            couponsService.createCoupon(couponId, couponCode, discountPercentage, expirationDate, userId);
//            OrderService orderService = null;

//            int orderId = 25;
//            int orderUserId = 1;
//            double totalPrice = 2000.0;
            Date orderDate = new Date();  // You should set the actual order date here

            // Create an instance of OrdersService
//            Orders newOrder = new Orders(50, 1, orderDate, 20);

            // Create an instance of OrdersService
            OrderService orderService = new OrdersServiceImpl(sqlSessionFactory);

            // Call the create method to create a new order
//            orderService.create(newOrder);




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

            UsersRepository usersRepository = new UsersRepositoryMyBatisImpl(sqlSessionFactory);
            UsersMyBatisService usersMyBatisService = new UsersMyBatisService(usersRepository);
//
//            // Create a new user using mybatis
//            Users newMyBatisUser = new Users(50,"Nora","norapass", "nora@gmail.com");
//            usersMyBatisService.createUser(newMyBatisUser);

            ProductsRepository productsRepository = new ProductsRepositoryMyBatisImpl(sqlSessionFactory);
            ProductsServiceImpl productsMyBatisService = new ProductsServiceImpl(productsRepository);
            // Create a new Products using mybatis
//            Products newMyBatisProduct = new Products(1,"Ipad Pro","Ipad for fun", 1000);
//            productsMyBatisService.createProduct(newMyBatisProduct);


            PaymentMethods paymentMethod = new PaymentMethods(20,1,"777888000",2025,565);
            PaymentMethodsRepository paymentMethodsRepository = null;
            PaymentMethodService paymentMethodService = new PaymentMethodsImpl(paymentMethodsRepository);
            paymentMethodService.create(paymentMethod);

            Products newServiceProduct = new Products(5,"Gucci Guilty","Gucci Guilty cologne",200);

            ProductsServiceImpl productsService = new ProductsServiceImpl(productsRepository);
            productsService.create(newServiceProduct);


            // DOM for ShippingMethod.xml file
//            File file = new File("src/main/resources/ShippingMethods.xml");
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            try {
//                DocumentBuilder builder = factory.newDocumentBuilder();
//                Document document = builder.parse(file);
//                // Retrieve shipping method data
//                NodeList shippingMethodNodes = document.getElementsByTagName("ShippingMethods");
//                for (int i = 0; i < shippingMethodNodes.getLength(); i++) {
//                    Element shippingMethodElement = (Element) shippingMethodNodes.item(i);
//
//                    ShippingMethods shippingMethod = new ShippingMethods(0, "space shipping", 50, 1);
//
//                }
//
//            } catch (ParserConfigurationException | SAXException e) {
//                throw new RuntimeException();
//            }
//            File file2 = new File("src/main/resources/Coupons.xml");
//            JAXBContext jaxbContext = JAXBContext.newInstance(Coupons.class);
//            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//            Coupons coupons = (Coupons) unmarshaller.unmarshal(file2);
//
//            // Display the retrieved data
//            logger.info("Codes: " + coupons.getCodes());
//            logger.info("Discount: " + coupons.getDiscount());


//            File file3 = new File("src/main/resources/Users.json");
//            ObjectMapper mapper = new ObjectMapper();
//            Users users = mapper.readValue(file3, Users.class);
//
//            // Display the retrieved data
//            logger.info("User ID: " + users.getId());
//            logger.info("Username: " + users.getUsername());
//            logger.info("Password: " + users.getPassword());
//            logger.info("Email: " + users.getEmail());

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
