package com.capgemini.java_dev.framework.hibernate_jpa.main;

import com.capgemini.java_dev.framework.hibernate_jpa.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JavaApp {

	public static void execution() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Student");
		EntityManager em = emf.createEntityManager();

		// CREATE
		Student s1 = new Student();
		s1.setId(1);
		s1.setName("Aditya");
		s1.setMailid("aditya@gmail.com");
		s1.setContactNumber(9876543210L);

		Student s2 = new Student();
		s2.setId(2);
		s2.setName("Raja");
		s2.setMailid("raja@gmail.com");
		s2.setContactNumber(9839438199L);

		Student s3 = new Student();
		s3.setId(3);
		s3.setName("Rani");
		s3.setMailid("rani@gmail.com");
		s3.setContactNumber(9000003003L);

//		em.getTransaction().begin();
//		em.persist(s1);
//		em.persist(s2);
//		em.persist(s3);
//		em.getTransaction().commit();

		System.out.println("Students Inserted");

		// READ
		Student student = em.find(Student.class, 2);

		if (student != null) {
			System.out.println(student);
		} else {
			System.out.println("Student data not found!");
		}

		// UPDATE
		if (student != null) {
			em.getTransaction().begin();
			student.setName("Updated Raja");
			em.getTransaction().commit();

			System.out.println("Student Updated");
		}else {
			System.out.println("Student not found!");
		}

		// DELETE
		if (student != null) {
			em.getTransaction().begin();
			em.remove(student);
			em.getTransaction().commit();

			System.out.println("Student Deleted");
		}else {
			System.out.println("Student not found!");
		}

		em.close();
		emf.close();
	}
}