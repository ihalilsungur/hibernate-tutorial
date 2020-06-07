package com.sungur.demo;

import com.sungur.model.Course;
import com.sungur.model.Instructor;
import com.sungur.model.InstructorDetail;
import com.sungur.model.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class GetCourseAndReviewsDemo {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        // session baslatma

        Session session = sessionFactory.getCurrentSession();

        try {

            // transaction baslatik
            session.beginTransaction();

          // Bir course getir
            long theId =3;
            Course tempCourse = session.get(Course.class,theId);

            // kursu yazdir
            System.out.println(tempCourse);

            // Bir kursa bagli reviews yazdir
            System.out.println(tempCourse.getReviews());


            //commit transaction
            session.getTransaction().commit();
            System.out.println("Islemlerimiz bitti");


        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
