package com.sungur.demo;

import com.sungur.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateStudentDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // session baslatma
        Session session = sessionFactory.getCurrentSession();

        try {

            // bir ogrenci nesnesi olusturma
            System.out.println("Bir nesne olusturuldu");
            Student student = new Student("ibrahim","Sungur","sungur@gmail.com");

            // transaction baslatik
            session.beginTransaction();

            // ogrenci nesnesini kaydettik
            System.out.println("Ogrenci nesnesini kaydettik");
            session.save(student);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Islemlerimiz bitti");


        }catch (Exception exc){

        }finally {
            sessionFactory.close();
        }
    }
}
