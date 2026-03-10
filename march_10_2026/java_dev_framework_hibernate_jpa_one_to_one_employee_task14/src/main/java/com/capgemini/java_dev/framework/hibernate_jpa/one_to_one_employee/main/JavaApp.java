package com.capgemini.java_dev.framework.hibernate_jpa.one_to_one_employee.main;

import java.util.Scanner;

import com.capgemini.java_dev.framework.hibernate_jpa.one_to_one_employee.entity.Address;
import com.capgemini.java_dev.framework.hibernate_jpa.one_to_one_employee.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JavaApp {

	public static void execute() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("employee-unit");

		EntityManager em = emf.createEntityManager();

		Scanner sc = new Scanner(System.in);

		int choice;

		do {

			System.out.println("\n====== Employee Management System ======");
			System.out.println("1. Add Employee");
			System.out.println("2. Search Employee");
			System.out.println("3. Update Salary");
			System.out.println("4. Update Address");
			System.out.println("5. Delete Employee");
			System.out.println("0. Exit");

			System.out.print("Enter choice: ");
			choice = sc.nextInt();

			switch (choice) {

			case 1:

				Employee emp = new Employee();
				Address addr = new Address();

				System.out.print("Enter Employee ID: ");
				emp.setId(sc.nextInt());

				System.out.print("Enter Employee Name: ");
				emp.setName(sc.next());

				System.out.print("Enter Employee Email: ");
				emp.setEmail(sc.next());

				System.out.print("Enter Employee Salary: ");
				emp.setSalary(sc.nextDouble());

				addr.setId(emp.getId());

				System.out.print("Enter Street: ");
				addr.setStreet(sc.next());

				System.out.print("Enter City: ");
				addr.setCity(sc.next());

				System.out.print("Enter Zipcode: ");
				addr.setZipcode(sc.next());

				emp.setAddress(addr);
				addr.setEmployee(emp);

				em.getTransaction().begin();
				em.persist(emp);
				em.getTransaction().commit();

				System.out.println("Employee added successfully");

				break;

			case 2:

				System.out.print("Enter Employee ID: ");
				int id = sc.nextInt();

				Employee e = em.find(Employee.class, id);

				if (e == null) {
					System.out.println("No employee found.");
				} else {

					System.out.println("ID: " + e.getId());
					System.out.println("Name: " + e.getName());
					System.out.println("Email: " + e.getEmail());
					System.out.println("Salary: " + e.getSalary());

					Address a = e.getAddress();

					if (a != null) {
					    System.out.println("Address:");
					    System.out.println("  Street: " + a.getStreet());
					    System.out.println("  City: " + a.getCity());
					    System.out.println("  Zipcode: " + a.getZipcode());
					}
					
				}

				break;

			case 3:

				System.out.print("Enter Employee ID: ");
				int sid = sc.nextInt();

				System.out.print("Enter New Salary: ");
				double sal = sc.nextDouble();

				em.getTransaction().begin();

				Employee empSal = em.find(Employee.class, sid);

				if (empSal != null) {

					empSal.setSalary(sal);

					System.out.println("Salary updated successfully");

				} else {
					System.out.println("No employee found.");
				}

				em.getTransaction().commit();

				break;

			case 4:

				System.out.print("Enter Employee ID: ");
				int eid = sc.nextInt();

				em.getTransaction().begin();

				Employee empAddr = em.find(Employee.class, eid);

				if (empAddr != null) {

					Address a = empAddr.getAddress();

					System.out.print("Enter New Street: ");
					a.setStreet(sc.next());

					System.out.print("Enter New City: ");
					a.setCity(sc.next());

					System.out.print("Enter New Zipcode: ");
					a.setZipcode(sc.next());

					System.out.println("Address updated successfully");

				} else {
					System.out.println("No employee found.");
				}

				em.getTransaction().commit();

				break;

			case 5:

				System.out.print("Enter Employee ID: ");
				int did = sc.nextInt();

				em.getTransaction().begin();

				Employee empDel = em.find(Employee.class, did);

				if (empDel != null) {

					em.remove(empDel);

					System.out.println("Employee deleted successfully");

				} else {
					System.out.println("No employee found.");
				}

				em.getTransaction().commit();

				break;
			case 0:
				System.out.println("Employee Management System Ended...");
				break;
			
			default:
				System.out.println("Invalid choice!");
				break;

			}

		}while(choice!=0);

	sc.close();em.close();emf.close();
}}