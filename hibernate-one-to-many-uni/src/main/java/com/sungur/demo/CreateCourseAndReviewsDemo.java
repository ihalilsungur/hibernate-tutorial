package com.sungur.demo;

import com.sungur.model.Course;
import com.sungur.model.Instructor;
import com.sungur.model.InstructorDetail;
import com.sungur.model.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateCourseAndReviewsDemo {

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

            // Bir kurs olusturma
            Course tempCourse = new Course("springboot kursu");

            // reviews ekleme
            tempCourse.add(new Review("Spring boot proje gelistirmeye kolay"));
            tempCourse.add(new Review("Spring boot kolaydir."));
            tempCourse.add(new Review("Spring boot guzeldir"));

            // course'larimizi kaydettik
            System.out.println("Course'larimizi kaydedildi.");
            System.out.println(tempCourse);
            System.out.println(tempCourse.getReviews());
            session.save(tempCourse);


            //commit transaction
            session.getTransaction().commit();
            System.out.println("Islemlerimiz bitti");


        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
