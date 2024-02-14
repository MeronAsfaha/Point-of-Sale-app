package business.dao;

import business.domain.Product;
import business.domain.ProductType;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductDAO {
    public static List<Product> products = new ArrayList<>();

    //            new Product("Coffee", ProductType.BEVERAGE, 1L, 2.50),
//            new Product("Iced Coffee", ProductType.BEVERAGE, 6L, 2.55),
//            new Product("Tea", ProductType.BEVERAGE, 5L, 1.75),
//            new Product("Iced Tea", ProductType.BEVERAGE, 4L, 1.99),
//            new Product("Latte", ProductType.BEVERAGE, 3L, 4.30),
//            new Product("Iced Latte", ProductType.BEVERAGE, 2L, 4.55),
//
//            new Product("Bagel", ProductType.FOOD, 12L, 2.00),
//            new Product("Croissant", ProductType.FOOD, 13L, 2.48),
//            new Product("Cookie", ProductType.FOOD, 14L, 1.80)));
    public static void readFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("products.txt"))) {
            String product = br.readLine();
            while (product != null) {
                String[] productsArray = product.split(",");
                String pName = productsArray[0];
                ProductType pType = ProductType.valueOf(productsArray[1]);
                Long pID = Long.valueOf(productsArray[2]);
                Double pPrice = Double.valueOf(productsArray[3]);
                Product p = new Product(pName, pType, pID, pPrice);
                products.add(p);
                product = br.readLine();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void writeToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("products.txt"))) {
            for (Product p : products) {
                String pName = p.getName();
                String pType = p.getType().toString();
                String pID = String.valueOf(p.getId());
                String pPrice = String.valueOf(p.getPrice());
                bw.write(String.format("%s,%s,%s,%s\n", pName, pType, pID, pPrice));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
