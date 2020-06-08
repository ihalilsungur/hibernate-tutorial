package sungur.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sungur.model.*;


public class CreateCourseAndStudentDemo {

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

            // Bir kurs olusturma
            Course tempCourse = new Course("springboot kursu");


            // course'larimizi kaydettik
            System.out.println("Course'larimizi kaydedildi.");
            System.out.println(tempCourse);
            session.save(tempCourse);

            // ogrenciler olusturduk
            Student tempStudent = new Student("ibrahim","Sungur","sungur@gmail.com");
            Student tempStudent2 = new Student("Xalo","Sungur","xalo@gmail.com");
            Student tempStudent3 = new Student("Halil","Sungur","halil@gmail.com");

            // kursa ogrecileri kaydetme
            tempCourse.addStudent(tempStudent);
            tempCourse.addStudent(tempStudent2);
            tempCourse.addStudent(tempStudent3);

            // ogrenciler kaydedildi
            System.out.println("Ogrenciler Kaydedildi.");
            session.save(tempStudent);
            session.save(tempStudent2);
            session.save(tempStudent3);

            System.out.println("Kaydedilen Ogrenciler : "+ tempCourse.getStudents());



            //commit transaction
            session.getTransaction().commit();
            System.out.println("Islemlerimiz bitti");


        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
