package sungur.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sungur.model.*;


public class DeleteStudentDemo {

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
            long studentId= 1;
            Student tempStudent = session.get(Student.class,studentId);
            System.out.println("Yuklnen Ogrenciler : "+tempStudent);
            System.out.println("\n Kurslarimiz : "+tempStudent.getCourses());
            System.out.println("\nSilinen Ogreciler : "+tempStudent);
            session.delete(tempStudent);


            //commit transaction
            session.getTransaction().commit();
            System.out.println("Islemlerimiz bitti");


        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
