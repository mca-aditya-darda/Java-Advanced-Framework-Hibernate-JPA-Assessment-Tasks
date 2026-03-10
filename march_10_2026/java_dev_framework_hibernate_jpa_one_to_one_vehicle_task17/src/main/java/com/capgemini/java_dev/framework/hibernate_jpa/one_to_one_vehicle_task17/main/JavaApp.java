package com.capgemini.java_dev.framework.hibernate_jpa.one_to_one_vehicle_task17.main;

import java.time.LocalDate;
import java.util.Scanner;

import com.capgemini.java_dev.framework.hibernate_jpa.one_to_one_vehicle_task17.entity.Vehicle;
import com.capgemini.java_dev.framework.hibernate_jpa.one_to_one_vehicle_task17.entity.Registration;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JavaApp {

    private static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("vehicle-unit");

    private static EntityManager em = emf.createEntityManager();

    public static void execute() {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {

            System.out.println("\n1 Add Vehicle");
            System.out.println("2 Search Vehicle");
            System.out.println("3 Update Registration Expiry");
            System.out.println("4 Delete Vehicle");
            System.out.println("0 Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

            case 1:
                addVehicle(sc);
                break;

            case 2:
                searchVehicle(sc);
                break;

            case 3:
                updateRegistration(sc);
                break;

            case 4:
                deleteVehicle(sc);
                break;

            }

        } while (choice != 0);

        sc.close();
        em.close();
        emf.close();
    }

    // ADD VEHICLE
    private static void addVehicle(Scanner sc) {

        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Brand: ");
        String brand = sc.nextLine();

        System.out.print("Enter Model: ");
        String model = sc.nextLine();

        System.out.print("Enter Registration Number: ");
        String regNo = sc.nextLine();

        System.out.print("Enter Expiry Date (yyyy-MM-dd): ");
        LocalDate expiryDate = LocalDate.parse(sc.nextLine());

        if (brand.isEmpty() || model.isEmpty() || regNo.isEmpty()) {
            System.out.println("Fields must not be empty");
            return;
        }

        Vehicle v = new Vehicle();
        Registration r = new Registration();

        v.setId(id);
        v.setBrand(brand);
        v.setModel(model);

        r.setId(id);
        r.setRegistrationNumber(regNo);
        r.setExpiryDate(expiryDate);

        v.setRegistration(r);
        r.setVehicle(v);

        em.getTransaction().begin();
        em.persist(v);
        em.getTransaction().commit();

        System.out.println("Vehicle added successfully");
        printVehicle(v);
    }

    // SEARCH VEHICLE
    private static void searchVehicle(Scanner sc) {

        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        Vehicle v = em.find(Vehicle.class, id);

        if (v == null)
            System.out.println("No vehicle found");
        else
            printVehicle(v);
    }

    // UPDATE REGISTRATION
    private static void updateRegistration(Scanner sc) {

        System.out.print("Enter Vehicle ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Vehicle v = em.find(Vehicle.class, id);

        if (v == null) {
            System.out.println("No vehicle found");
            return;
        }

        System.out.print("Enter New Expiry Date (yyyy-MM-dd): ");
        LocalDate newDate = LocalDate.parse(sc.nextLine());

        em.getTransaction().begin();
        v.getRegistration().setExpiryDate(newDate);
        em.merge(v);
        em.getTransaction().commit();

        System.out.println("Registration updated successfully");
    }

    // DELETE VEHICLE
    private static void deleteVehicle(Scanner sc) {

        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        Vehicle v = em.find(Vehicle.class, id);

        if (v == null) {
            System.out.println("No vehicle found");
            return;
        }

        em.getTransaction().begin();
        em.remove(v);
        em.getTransaction().commit();

        System.out.println("Vehicle deleted successfully");
    }

    // PRINT VEHICLE
    private static void printVehicle(Vehicle v) {

        Registration r = v.getRegistration();

        System.out.println("ID: " + v.getId());
        System.out.println("Brand: " + v.getBrand());
        System.out.println("Model: " + v.getModel());

        System.out.println("Registration:");
        System.out.println("  Number: " + r.getRegistrationNumber());
        System.out.println("  Expiry Date: " + r.getExpiryDate());
    }
}