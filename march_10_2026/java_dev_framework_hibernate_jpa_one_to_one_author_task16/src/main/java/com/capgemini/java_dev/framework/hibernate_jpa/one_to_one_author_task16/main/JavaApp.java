package com.capgemini.java_dev.framework.hibernate_jpa.one_to_one_author_task16.main;

import java.time.LocalDate;
import java.util.Scanner;

import com.capgemini.java_dev.framework.hibernate_jpa.one_to_one_author_task16.entity.Author;
import com.capgemini.java_dev.framework.hibernate_jpa.one_to_one_author_task16.entity.Biography;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JavaApp {

    public static void execute() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("author-unit");
        EntityManager em = emf.createEntityManager();
        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println("\n===== Author Management =====");
            System.out.println("1 Add Author");
            System.out.println("2 Search Author");
            System.out.println("3 Update Biography");
            System.out.println("4 Delete Author");
            System.out.println("0 Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

            // ADD AUTHOR
            case 1:

                System.out.print("Enter ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Email: ");
                String email = sc.nextLine();

                System.out.print("Enter Summary: ");
                String summary = sc.nextLine();

                System.out.print("Enter Birth Place: ");
                String birthPlace = sc.nextLine();

                System.out.print("Enter Birth Date (yyyy-MM-dd): ");
                LocalDate birthDate = LocalDate.parse(sc.nextLine());

                if(name.isEmpty() || email.isEmpty() || summary.isEmpty() || birthPlace.isEmpty()) {
                    System.out.println("Fields must not be empty");
                    break;
                }

                Author author = new Author();
                Biography bio = new Biography();

                author.setId(id);
                author.setName(name);
                author.setEmail(email);

                bio.setId(id);
                bio.setSummary(summary);
                bio.setBirthPlace(birthPlace);
                bio.setBirthDate(birthDate);

                author.setBiography(bio);
                bio.setAuthor(author);

                em.getTransaction().begin();
                em.persist(author);
                em.getTransaction().commit();

                System.out.println("Author added successfully");

                printAuthor(author);

                break;

            // SEARCH AUTHOR
            case 2:

                System.out.print("Enter ID: ");
                int searchId = sc.nextInt();

                Author foundAuthor = em.find(Author.class, searchId);

                if(foundAuthor == null) {
                    System.out.println("No author found");
                } else {
                    printAuthor(foundAuthor);
                }

                break;

            // UPDATE BIOGRAPHY
            case 3:

                System.out.print("Enter Author ID: ");
                int updateId = sc.nextInt();
                sc.nextLine();

                Author updateAuthor = em.find(Author.class, updateId);

                if(updateAuthor == null) {
                    System.out.println("No author found");
                    break;
                }

                Biography updateBio = updateAuthor.getBiography();

                System.out.print("Summary: ");
                updateBio.setSummary(sc.nextLine());

                System.out.print("Birth Place: ");
                updateBio.setBirthPlace(sc.nextLine());

                System.out.print("Birth Date (yyyy-MM-dd): ");
                updateBio.setBirthDate(LocalDate.parse(sc.nextLine()));

                em.getTransaction().begin();
                em.merge(updateAuthor);
                em.getTransaction().commit();

                System.out.println("Biography updated successfully");

                break;

            // DELETE AUTHOR
            case 4:

                System.out.print("Enter ID: ");
                int deleteId = sc.nextInt();

                Author deleteAuthor = em.find(Author.class, deleteId);

                if(deleteAuthor == null) {
                    System.out.println("No author found");
                    break;
                }

                em.getTransaction().begin();
                em.remove(deleteAuthor);
                em.getTransaction().commit();

                System.out.println("Author deleted successfully");

                break;

            }

        } while(choice != 0);

        sc.close();
        em.close();
        emf.close();
    }

    // PRINT METHOD (CLEAN OUTPUT)
    private static void printAuthor(Author author) {

        Biography b = author.getBiography();

        System.out.println("ID: " + author.getId());
        System.out.println("Name: " + author.getName());
        System.out.println("Email: " + author.getEmail());

        System.out.println("Biography:");
        System.out.println("  Summary: " + b.getSummary());
        System.out.println("  Birth Place: " + b.getBirthPlace());
        System.out.println("  Birth Date: " + b.getBirthDate());
    }
}