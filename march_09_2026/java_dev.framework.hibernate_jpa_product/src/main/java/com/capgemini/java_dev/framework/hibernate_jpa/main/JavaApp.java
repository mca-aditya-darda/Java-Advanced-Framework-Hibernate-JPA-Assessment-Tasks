package com.capgemini.java_dev.framework.hibernate_jpa.main;

import java.util.List;
import java.util.Scanner;

import com.capgemini.java_dev.framework.hibernate_jpa.dao.ProductDAO;
import com.capgemini.java_dev.framework.hibernate_jpa.entity.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JavaApp {

    public static void execution() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Product");
        EntityManager em = emf.createEntityManager();

        ProductDAO dao = new ProductDAO(em);

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n1 Add Product");
            System.out.println("2 View All Products");
            System.out.println("3 Search Product by ID");
            System.out.println("4 Search Product by Category");
            System.out.println("5 Update Product Price");
            System.out.println("6 Delete Product");
            System.out.println("7 Exit");

            int choice = sc.nextInt();

            switch (choice) {

            case 1:

                sc.nextLine();

                System.out.println("Name:");
                String name = sc.nextLine();

                System.out.println("Category:");
                String category = sc.nextLine();

                System.out.println("Price:");
                double price = sc.nextDouble();

                System.out.println("Quantity:");
                int quantity = sc.nextInt();

                Product p = new Product(name, category, price, quantity);

                dao.addProduct(p);

                break;

            case 2:

                List<Product> list = dao.getAllProducts();

                if (list.isEmpty()) {
                    System.out.println("No product found.");
                } else {
                    list.forEach(System.out::println);
                }

                break;

            case 3:

                System.out.println("Enter ID:");
                int id = sc.nextInt();

                Product product = dao.getProductById(id);

                if (product != null)
                    System.out.println(product);
                else
                    System.out.println("No product found.");

                break;

            case 4:

                sc.nextLine();

                System.out.println("Enter Category:");
                String cat = sc.nextLine();

                List<Product> products = dao.getProductsByCategory(cat);

                if (products.isEmpty())
                    System.out.println("No product found.");
                else
                    products.forEach(System.out::println);

                break;

            case 5:

                System.out.println("ID:");
                int pid = sc.nextInt();

                System.out.println("New Price:");
                double newPrice = sc.nextDouble();

                dao.updateProductPrice(pid, newPrice);

                break;

            case 6:

                System.out.println("ID:");
                int deleteId = sc.nextInt();

                dao.deleteProduct(deleteId);

                break;

            case 7:

                em.close();
                emf.close();
                sc.close();
                System.exit(0);
            }
        }
    }
}