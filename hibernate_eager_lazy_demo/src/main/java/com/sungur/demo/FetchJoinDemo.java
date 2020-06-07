package com.sungur.demo;

import com.sungur.model.Course;
import com.sungur.model.Instructor;
import com.sungur.model.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class FetchJoinDemo {

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

            /*
             nesnelerimizi olusturalim
             secenek 2 : Hibernate HQL ile sorgu
            */
            long theId = 1;
            Query<Instructor> query =
                    session.createQuery("select i from Instructor i "
                                    + "JOIN FETCH i.courses "
                                    + "where i.id=:theInstructorId",
                            Instructor.class);

            // set parameter on query
            query.setParameter("theInstructorId",theId);

            //execute query and get instructor
            Instructor instructor = query.getSingleResult();

            System.out.println("Instructor : "+instructor);


            //commit transaction
            session.getTransaction().commit();
            System.out.println("Islemlerimiz bitti");

            // close the session
            session.close();
            System.out.println(" Session kapatildi");
            System.out.println("Courses : "+instructor.getCourses());


        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
