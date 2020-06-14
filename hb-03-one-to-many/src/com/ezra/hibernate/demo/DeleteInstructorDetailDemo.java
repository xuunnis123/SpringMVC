package com.ezra.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ezra.hibernate.demo.entity.Instructor;
import com.ezra.hibernate.demo.entity.InstructorDetail;
import com.ezra.hibernate.demo.entity.Student;
import java.util.logging.Logger;

import javax.persistence.CascadeType;

import java.util.logging.Level;
import java.util.logging.LogManager;

public class DeleteInstructorDetailDemo {
	
//	private static Logger _log = Logger.getLogger(PrimaryKeyDemo.class.getName());
	public static void main(String[] args) {
//		_log.info("123");
		SessionFactory factory=new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.buildSessionFactory();
		
		Session session=factory.getCurrentSession();
		
		try {
			
			
			
			//start a transaction
			session.beginTransaction();
			
			//get the instructor detail object
			int theId=3;
			
			InstructorDetail tempInstructorDetail=
					session.get(InstructorDetail.class, theId);
			
//			print the instructor detail
			
			System.out.println("tempInstructorDeytial:"+tempInstructorDetail);
			
//			print the associated instructor
			System.out.println("the associated instructor: "+tempInstructorDetail.getInstructor());
			
			//now let's delete the instructor detail
			System.out.println("Deleting tempInstructorDetail:"+tempInstructorDetail);
			
//			remove the associated object reference
//			break bi-directional link
			
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			
			
			session.delete(tempInstructorDetail);
			
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("done!");
		}
		catch( Exception e){
			e.printStackTrace();
		}
		finally {
			//handle connection leak issue
			session.clear();
			factory.close();
		}
		
		
	}

}
