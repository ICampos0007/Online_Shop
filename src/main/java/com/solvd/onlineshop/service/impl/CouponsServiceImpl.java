package com.solvd.onlineshop.service.impl;

import com.solvd.onlineshop.bin.Coupons;
import com.solvd.onlineshop.dao.CouponsRepositoryImpl;

import java.io.File;
import java.util.Date;

import com.solvd.onlineshop.service.CouponsService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

public class CouponsServiceImpl implements CouponsService {

    private static final Logger logger = LogManager.getLogger(CouponsServiceImpl.class);
    private CouponsRepositoryImpl couponsRepository;

    public CouponsServiceImpl() {
        this.couponsRepository = new CouponsRepositoryImpl();
    }

    // Create a new coupon and check if the user already has one
//    public void createCoupon(String couponCode, double discountPercentage, Date expirationDate, int userId) throws SQLException {
//        // Check if the user already has a coupon
//        Coupons existingCoupon = couponsRepository.getCouponByUserId(userId);
//
//        if (existingCoupon == null) {
//            // User does not have a coupon, create a new one
//            Coupons newCoupon = new Coupons(id, couponCode, discountPercentage, expirationDate, userId);
//
//            // Create the new coupon
//            couponsRepository.addCoupon(newCoupon);
//            logger.info("Coupon created successfully for user ID: " + userId);
//        } else {
//            // User already has a coupon
//            logger.info("User with ID " + userId + " already has a coupon (ID: " + existingCoupon.getId() + ")");
//        }
//    }


    @Override
    public void createCoupons(Coupons coupons) {
        try {
            File file = new File("src/main/resources/Coupons.xml");
            File xsdFile = new File("src/main/resources/Coupons.xsd");

            JAXBContext jaxbContext = JAXBContext.newInstance(Coupons.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema couponSchema = sf.newSchema(xsdFile);
            jaxbUnmarshaller.setSchema(couponSchema);

             coupons = (Coupons) jaxbUnmarshaller.unmarshal(file);

            // Rest of your code...

        } catch (JAXBException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
