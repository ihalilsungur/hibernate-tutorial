package sungur.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sungur.model.*;


public class AddCoursesForStudentDemo {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // session baslatma

        Session session = sessionFactory.getCurrentSession();

        try {

            // transaction baslatik
            session.beginTransaction();

            // course Ongreciler eklenmesi
            long theStudent=1;
            Student tempStudent = session.get(Student.class,theStudent);
            System.out.println("Yuklenen Ogrenci : "+tempStudent);
            System.out.println("Course : "+tempStudent.getCourses());

            // Birden cok kurs olusturma
            Course tempCourse = new Course("Hibernate Kursu");
            Course tempCourse2 = new Course("MySql Kursu");

            //  Ogrenciye Kurs Ekleme
            tempCourse.addStudent(tempStudent);
            tempCourse2.addStudent(tempStudent);

            // Kurslari kaydetme
            System.out.println("Kurslari Kaydetme");
            session.save(tempCourse);
            session.save(tempCourse2);




            //commit transaction
            session.getTransaction().commit();
            System.out.println("Islemlerimiz bitti");


        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
