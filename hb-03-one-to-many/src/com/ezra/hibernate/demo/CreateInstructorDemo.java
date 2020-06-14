package com.ezra.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ezra.hibernate.demo.entity.Course;
import com.ezra.hibernate.demo.entity.Instructor;
import com.ezra.hibernate.demo.entity.InstructorDetail;

public class CreateInstructorDemo {
	
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
			//create the objects
			
			Instructor tempInstructor=
					new Instructor("Susan","Public","susan.public@gmail.com",null);
			
			InstructorDetail tempInstructorDetail=new InstructorDetail("http://www.youtube/ezraLin","ESearc!");
			
			
		
			//associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			//start a transaction
			session.beginTransaction();
			
			//save the instructor
		
			// Note: this will ALSO save the details object
			//because of CascadeType.ALL
			System.out.println("Saving instructor"+tempInstructor);
			session.save(tempInstructor);
			
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
