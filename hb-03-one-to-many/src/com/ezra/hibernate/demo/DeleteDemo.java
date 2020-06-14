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

public class DeleteDemo {
	
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
			
			
//			get instructor by primary key/id
			int theId=1;
			Instructor tempInstructor=session.get(Instructor.class, theId);
			
			System.out.println("Found instructor:"+ tempInstructor);
			
//			delete the instructor
			if(tempInstructor!=null) {
				System.out.println("Deleting:" +tempInstructor);
//				Note:will ALSO delete associated "details" object
//				because of CascadeType.ALL
				
				session.delete(tempInstructor);
			
			}
			
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("done!");
		}finally {
			
			factory.close();
		}
		
		
	}

}
