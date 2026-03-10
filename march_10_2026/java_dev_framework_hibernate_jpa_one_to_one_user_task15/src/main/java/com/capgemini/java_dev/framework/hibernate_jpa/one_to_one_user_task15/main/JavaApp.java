package com.capgemini.java_dev.framework.hibernate_jpa.one_to_one_user_task15.main;

import java.time.LocalDate;
import java.util.Scanner;

import com.capgemini.java_dev.framework.hibernate_jpa.one_to_one_user_task15.entity.Profile;
import com.capgemini.java_dev.framework.hibernate_jpa.one_to_one_user_task15.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JavaApp {

    public static void execute() {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("user-unit");

        EntityManager em = emf.createEntityManager();

        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println("\n===== User Profile Management =====");
            System.out.println("1 Add User");
            System.out.println("2 Search User");
            System.out.println("3 Update Email");
            System.out.println("4 Update Profile");
            System.out.println("5 Delete User");
            System.out.println("0 Exit");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();

            switch(choice) {

            case 1:

                User user = new User();
                Profile profile = new Profile();

                System.out.print("Enter ID: ");
                user.setId(sc.nextInt());

                System.out.print("Enter Username: ");
                user.setUsername(sc.next());

                System.out.print("Enter Email: ");
                user.setEmail(sc.next());

                profile.setId(user.getId());

                System.out.print("Enter Phone: ");
                profile.setPhone(sc.next());

                System.out.print("Enter Gender: ");
                profile.setGender(sc.next());

                System.out.print("Enter DOB (yyyy-MM-dd): ");
                profile.setDob(LocalDate.parse(sc.next()));

                user.setProfile(profile);
                profile.setUser(user);

                em.getTransaction().begin();
                em.persist(user);
                em.getTransaction().commit();

                System.out.println("User added successfully");

                // PRINT USER DETAILS
                System.out.println("ID: " + user.getId());
                System.out.println("Username: " + user.getUsername());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Profile:");
                System.out.println("  Phone: " + profile.getPhone());
                System.out.println("  Gender: " + profile.getGender());
                System.out.println("  DOB: " + profile.getDob());

                break;

            case 2:

                System.out.print("Enter ID: ");
                int id = sc.nextInt();

                User u = em.find(User.class, id);

                if(u==null) {
                    System.out.println("No user found");
                } else {

                    System.out.println("ID: " + u.getId());
                    System.out.println("Username: " + u.getUsername());
                    System.out.println("Email: " + u.getEmail());

                    Profile p = u.getProfile();

                    if(p!=null) {
                        System.out.println("Profile:");
                        System.out.println("  Phone: "+p.getPhone());
                        System.out.println("  Gender: "+p.getGender());
                        System.out.println("  DOB: "+p.getDob());
                    }
                }

                break;

            case 3:

                System.out.print("Enter ID: ");
                int uid = sc.nextInt();

                System.out.print("Enter New Email: ");
                String email = sc.next();

                em.getTransaction().begin();

                User ue = em.find(User.class, uid);

                if(ue!=null) {

                    ue.setEmail(email);
                    System.out.println("Email updated successfully");

                } else {
                    System.out.println("No user found");
                }

                em.getTransaction().commit();

                break;

            case 4:

                System.out.print("Enter User ID: ");
                int pid = sc.nextInt();

                em.getTransaction().begin();

                User up = em.find(User.class, pid);

                if(up!=null) {

                    Profile p = up.getProfile();

                    System.out.print("Phone: ");
                    p.setPhone(sc.next());

                    System.out.print("Gender: ");
                    p.setGender(sc.next());

                    System.out.print("DOB (yyyy-MM-dd): ");
                    p.setDob(LocalDate.parse(sc.next()));

                    System.out.println("Profile updated successfully");

                } else {
                    System.out.println("No user found");
                }

                em.getTransaction().commit();

                break;

            case 5:

                System.out.print("Enter ID: ");
                int did = sc.nextInt();

                em.getTransaction().begin();

                User du = em.find(User.class, did);

                if(du!=null) {

                    em.remove(du);
                    System.out.println("User deleted successfully");

                } else {
                    System.out.println("No user found");
                }

                em.getTransaction().commit();

                break;

            }

        }while(choice!=0);

        sc.close();
        em.close();
        emf.close();
    }
}