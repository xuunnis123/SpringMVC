package com.ezra.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ezra.hibernate.demo.entity.Course;
import com.ezra.hibernate.demo.entity.Instructor;
import com.ezra.hibernate.demo.entity.InstructorDetail;

public class DeleteCourseDemo {
	
//	private static Logger _log = Logger.getLogger(PrimaryKeyDemo.class.getName());
	public static void main(String[] args) {
//		_log.info("123");
		SessionFactory factory=new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.buildSessionFactory();
		
		Session session=factory.getCurrentSession();
		
		try {
			
			//start a transaction
			session.beginTransaction();
			
			
			//get a course
			int theId=10;
			Course tempCourse=session.get(Course.class, theId);
			
			//delete course
			System.out.println("Deleting Course:"+tempCourse);
			
			session.delete(tempCourse);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("done!");
		}finally {
			
			
			//add clean up code
			session.close();
			
			factory.close();
		}
		
		
	}

}
