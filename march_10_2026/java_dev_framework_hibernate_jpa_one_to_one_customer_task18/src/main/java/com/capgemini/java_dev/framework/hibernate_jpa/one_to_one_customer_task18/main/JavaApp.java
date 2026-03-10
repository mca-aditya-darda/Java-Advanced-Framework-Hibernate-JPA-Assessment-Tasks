package com.capgemini.java_dev.framework.hibernate_jpa.one_to_one_customer_task18.main;

import java.util.Scanner;

import com.capgemini.java_dev.framework.hibernate_jpa.one_to_one_customer_task18.entity.Customer;
import com.capgemini.java_dev.framework.hibernate_jpa.one_to_one_customer_task18.entity.Wallet;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JavaApp {

    public static void execute() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("customer-unit");
        EntityManager em = emf.createEntityManager();

        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println("\n====== Customer Wallet System ======");
            System.out.println("1 Add Customer");
            System.out.println("2 Search Customer");
            System.out.println("3 Update Wallet Balance");
            System.out.println("4 Delete Customer");
            System.out.println("0 Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

            case 1:

                Customer c = new Customer();
                Wallet w = new Wallet();

                System.out.print("Enter ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                c.setId(id);
                w.setId(id);

                System.out.print("Enter Name: ");
                c.setName(sc.nextLine());

                System.out.print("Enter Email: ");
                c.setEmail(sc.nextLine());

                System.out.print("Enter Balance: ");
                double balance = sc.nextDouble();
                sc.nextLine();

                if (balance <= 0) {
                    System.out.println("Balance must be positive");
                    break;
                }

                System.out.print("Enter Currency: ");
                w.setCurrency(sc.nextLine());

                w.setBalance(balance);

                c.setWallet(w);
                w.setCustomer(c);

                try {

                    em.getTransaction().begin();
                    em.persist(c);
                    em.getTransaction().commit();

                    System.out.println("Customer added successfully");
                    System.out.println("ID: " + c.getId());
                    System.out.println("Name: " + c.getName());
                    System.out.println("Email: " + c.getEmail());
                    System.out.println("Wallet:");
                    System.out.println("  Balance: " + w.getBalance());
                    System.out.println("  Currency: " + w.getCurrency());

                } catch (Exception e) {

                    if (em.getTransaction().isActive())
                        em.getTransaction().rollback();

                    System.out.println("Error adding customer");
                }

                break;

            case 2:

                System.out.print("Enter ID: ");
                int sid = sc.nextInt();

                Customer cs = em.find(Customer.class, sid);

                if (cs == null) {

                    System.out.println("No customer found");

                } else {

                    Wallet ws = cs.getWallet();

                    System.out.println("ID: " + cs.getId());
                    System.out.println("Name: " + cs.getName());
                    System.out.println("Email: " + cs.getEmail());

                    if (ws != null) {
                        System.out.println("Wallet:");
                        System.out.println("  Balance: " + ws.getBalance());
                        System.out.println("  Currency: " + ws.getCurrency());
                    }
                }

                break;

            case 3:

                System.out.print("Enter Customer ID: ");
                int uid = sc.nextInt();

                Customer cu = em.find(Customer.class, uid);

                if (cu == null) {
                    System.out.println("No customer found");
                    break;
                }

                System.out.print("Enter New Balance: ");
                double newBalance = sc.nextDouble();

                if (newBalance <= 0) {
                    System.out.println("Balance must be positive");
                    break;
                }

                try {

                    em.getTransaction().begin();

                    Wallet wu = cu.getWallet();

                    if (wu != null)
                        wu.setBalance(newBalance);

                    em.getTransaction().commit();

                    System.out.println("Wallet updated successfully");

                } catch (Exception e) {

                    if (em.getTransaction().isActive())
                        em.getTransaction().rollback();

                    System.out.println("Error updating wallet");
                }

                break;

            case 4:

                System.out.print("Enter ID: ");
                int did = sc.nextInt();

                Customer cd = em.find(Customer.class, did);

                if (cd == null) {

                    System.out.println("No customer found");

                } else {

                    try {

                        em.getTransaction().begin();
                        em.remove(cd);
                        em.getTransaction().commit();

                        System.out.println("Customer deleted successfully");

                    } catch (Exception e) {

                        if (em.getTransaction().isActive())
                            em.getTransaction().rollback();

                        System.out.println("Error deleting customer");
                    }
                }

                break;

            case 0:
                System.out.println("Exiting system...");
                break;

            default:
                System.out.println("Invalid choice");
            }

        } while (choice != 0);

        sc.close();
        em.close();
        emf.close();
    }
}