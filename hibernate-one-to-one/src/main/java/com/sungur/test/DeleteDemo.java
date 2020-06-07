package com.sungur.test;

import com.sungur.model.Instructor;
import com.sungur.model.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // session baslatma
        Session session = sessionFactory.getCurrentSession();

        try {


            // transaction baslatik
            session.beginTransaction();

           // instructor id ile nesnesyi getirme
            long theId=1;
            Instructor tempInstructor = session.get(Instructor.class,theId);
            System.out.println("Bulunana Instructor Nesnesi : "+tempInstructor);
            //instructor'i silme
            if(tempInstructor != null){
                System.out.println("Instructori basarili ile silindi ve Silinen nesne : "+tempInstructor);
                session.delete(tempInstructor);
            }


            //commit transaction
            session.getTransaction().commit();
            System.out.println("Islemlerimiz bitti");


        }catch (Exception exc){

        }finally {
            sessionFactory.close();
        }
    }
}
