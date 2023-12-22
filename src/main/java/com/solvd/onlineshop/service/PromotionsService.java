package com.solvd.onlineshop.service;

import com.solvd.onlineshop.bin.Promotions;
import com.solvd.onlineshop.dao.PromotionsRepositoryImpl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PromotionsService {
    private static final Logger logger = LogManager.getLogger(PromotionsService.class);

    private PromotionsRepositoryImpl promotionsRepository;

    public PromotionsService() {
        this.promotionsRepository = new PromotionsRepositoryImpl();
    }

    // Create a new promotion
    public void createPromotion(int id, String promotionName, String startDate, String endDate, int productId) {
        // Check if the promotion_name already exists
        if (promotionsRepository.getPromotionByName(promotionName) != null) {
            // Log an error and return
            logger.error("Error creating promotion. Promotion with name '" + promotionName + "' already exists.");
            return;
        }

        // Parse start and end dates
        Date parsedStartDate = parseDate(startDate);
        Date parsedEndDate = parseDate(endDate);

        if (parsedStartDate == null || parsedEndDate == null) {
            // Log an error and return if date parsing fails
            logger.error("Error parsing start or end date. Promotion creation failed.");
            return;
        }

        Promotions newPromotion = new Promotions(id, promotionName, new Timestamp(parsedStartDate.getTime()), new Timestamp(parsedEndDate.getTime()), productId);

        // Add the new promotion to the repository
        promotionsRepository.addPromotion(newPromotion);
        logger.info("Promotion created successfully: " + newPromotion.getId());
    }

    private Date parseDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            logger.error("Error parsing date: " + dateString, e);
            return null;
        }
    }
}
