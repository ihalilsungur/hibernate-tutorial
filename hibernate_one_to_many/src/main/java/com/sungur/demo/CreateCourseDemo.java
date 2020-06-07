package com.sungur.demo;

import com.sungur.model.Course;
import com.sungur.model.Instructor;
import com.sungur.model.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateCourseDemo {

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

            // transaction baslatik
            session.beginTransaction();

            // nesnelerimizi olusturalim

            long theId = 1;
            Instructor instructor = session.get(Instructor.class, theId);
            System.out.println(instructor);
            Course course = new Course("Java");
            Course course2 = new Course("Angular");

            //nesnelerin iliskilerini tanimlamak
            instructor.add(course);
            instructor.add(course2);



            // ogrenci nesnesini kaydettik
            System.out.println("nesnelerimizi  kaydettik");
            session.save(course);
            session.save(course2);


            //commit transaction
            session.getTransaction().commit();
            System.out.println("Islemlerimiz bitti");


        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
