package com.ezra.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ezra.hibernate.demo.entity.Course;
import com.ezra.hibernate.demo.entity.Instructor;
import com.ezra.hibernate.demo.entity.InstructorDetail;

public class GetInstructorCoursesDemo {
	
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
			
			
			// get the instructor from db
			int theId=1;
			Instructor tempInstructor=session.get(Instructor.class, theId);
			
			System.out.println("instructor:"+ tempInstructor);
			// get course for the instructor
			
			System.out.println("Courses:"+tempInstructor.getCourses());
			
			
			
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
