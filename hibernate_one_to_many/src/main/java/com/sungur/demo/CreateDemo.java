package com.sungur.demo;

import com.sungur.model.Course;
import com.sungur.model.Instructor;
import com.sungur.model.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CreateDemo {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // session baslatma
        Session session = sessionFactory.getCurrentSession();

        try {

         // nesnelerimizi olusturalim
            Instructor instructor = new Instructor("Xalo","Sungur","xalo@gmail.com");
            InstructorDetail instructorDetail = new InstructorDetail("http://www.muzikdinle.com.tr","Muzik dinle");

            List<Course> courses = null;
            courses.add(new Course("Java"));
            courses.add(new Course("Angular "));
            //nesnelerin iliskilerini tanimlamak
            instructor.setInstructorDetail(instructorDetail);
            instructor.setCourses(courses);

            session.save(instructor);

            // transaction baslatik
            session.beginTransaction();

            // ogrenci nesnesini kaydettik
            System.out.println("nesnelerimizi  kaydettik");
            session.save(instructor);


            //commit transaction
            session.getTransaction().commit();
            System.out.println("Islemlerimiz bitti");


        }catch (Exception exc){

        }finally {
            sessionFactory.close();
        }
    }
}
