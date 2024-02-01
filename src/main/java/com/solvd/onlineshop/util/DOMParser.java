package com.solvd.onlineshop.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DOMParser {

    public static void main(String[] args) {
        try {
            // Load the XML file
            File xmlFile = new File("src/main/resources/ShippingMethods.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = null;

            try {
                dBuilder = dbFactory.newDocumentBuilder();
            } catch (ParserConfigurationException ex) {
                throw new RuntimeException(ex);
            }

            Document document = null;
            try {
                document = dBuilder.parse(xmlFile);
            } catch (SAXException | IOException ex) {
                throw new RuntimeException(ex);
            }

            document.getDocumentElement().normalize();

            // Get the root element
            Element rootElement = document.getDocumentElement();

            // Parse shipping method details
            String shippingMethodName = rootElement.getElementsByTagName("shipping_method_name").item(0).getTextContent();
            double shippingCost = Double.parseDouble(rootElement.getElementsByTagName("shipping_cost").item(0).getTextContent());
            int orderId = Integer.parseInt(rootElement.getElementsByTagName("order_id").item(0).getTextContent());

            // Parse orders
            NodeList orderList = rootElement.getElementsByTagName("order");
            for (int i = 0; i < orderList.getLength(); i++) {
                Node orderNode = orderList.item(i);

                if (orderNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element orderElement = (Element) orderNode;

                    // Parse order details
                    int orderIdFromOrder = Integer.parseInt(orderElement.getAttribute("id"));
                    String orderDate = orderElement.getElementsByTagName("order_date").item(0).getTextContent();
                    double totalPrice = Double.parseDouble(orderElement.getElementsByTagName("total_price").item(0).getTextContent());

                    // Parse user details within the order
                    Element userElement = (Element) orderElement.getElementsByTagName("user").item(0);
                    int userId = Integer.parseInt(userElement.getAttribute("id"));
                    String username = userElement.getElementsByTagName("username").item(0).getTextContent();
                    String password = userElement.getElementsByTagName("passw").item(0).getTextContent();
                    String email = userElement.getElementsByTagName("email").item(0).getTextContent();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
