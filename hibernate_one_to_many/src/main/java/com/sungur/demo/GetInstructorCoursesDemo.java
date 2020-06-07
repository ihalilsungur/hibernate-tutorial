package com.sungur.demo;

import com.sungur.model.Course;
import com.sungur.model.Instructor;
import com.sungur.model.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class GetInstructorCoursesDemo {

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

            System.out.println("Instructor : "+instructor);

            //instructor için course getir
            System.out.println("Courses :"+instructor.getCourses());


            //commit transaction
            session.getTransaction().commit();
            System.out.println("Islemlerimiz bitti");


        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
