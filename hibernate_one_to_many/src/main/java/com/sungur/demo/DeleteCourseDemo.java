package com.sungur.demo;

import com.sungur.model.Course;
import com.sungur.model.Instructor;
import com.sungur.model.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeleteCourseDemo {

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


            // Course getir
            long theId =2;
            Course tempCourse = session.get(Course.class,theId);

            // Course sil
            System.out.println("Silinen Course : "+tempCourse);
            session.delete(tempCourse);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Islemlerimiz bitti");


        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
