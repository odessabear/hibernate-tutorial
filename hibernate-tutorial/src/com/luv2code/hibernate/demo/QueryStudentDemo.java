package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {			
			
			//start a transaction
			session.beginTransaction();
			
			//query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			System.out.println();
			
			//display the students
			displayStudents(theStudents);
			System.out.println();
			System.out.println();
			
			theStudents = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
			System.out.println();
			System.out.println("Students who have last name Doe");
			displayStudents(theStudents);
			System.out.println();
			
			//query student : lastName='Doe' OR firstName = 'Daffy'
			
			theStudents = session.createQuery("from Student s where " + "s.lastName = 'Doe' OR s.firstName = 'Daffy'").getResultList();
			System.out.println("\n\nStudents who have last name doe or first name Daffy");
			displayStudents(theStudents);
		
			//query students where email like '%luv2code.com'
			
			theStudents = session.createQuery("from Student s where s.email like '%luv2code.com'").getResultList();
			System.out.println();
			System.out.println("\n\nStudents who email ends with luv2code.com");

			displayStudents(theStudents);
			System.out.println();
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Well Done!");
			
		} finally {
			factory.close();
		}

	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
