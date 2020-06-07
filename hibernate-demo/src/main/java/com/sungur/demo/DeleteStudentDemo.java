package com.sungur.demo;

import com.sungur.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeleteStudentDemo {
    public static void main(String[] args) {

        // sessionFactory baslatildi
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // session baslatildi
        Session session = factory.getCurrentSession();

        try {

            //silinecek ogrenci id'si
            long theStudentId=3;

            //transaction baslattik
            session.beginTransaction();


            System.out.println("Silinecek Ogrenci Id : "+theStudentId);

            Student myStudent = session.get(Student.class,theStudentId);

            //session uzerinden silinme
           // System.out.println("Silinen Ogrenci : "+myStudent);
           // session.delete(myStudent);

            //query ile ogrenci silme
            System.out.println("Silinen Ogrenci :"+myStudent);
            session.createQuery("delete from  Student  where id=3").executeUpdate();

            // commit the transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        }finally {
            factory.close();
        }
    }
}
