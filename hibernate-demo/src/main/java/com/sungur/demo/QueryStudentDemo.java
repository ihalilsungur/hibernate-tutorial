package com.sungur.demo;

import com.sungur.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();
        try {
            // transaction baslatma
            session.beginTransaction();

            //query students
            List<Student>theStudents = session.createQuery("from Student").getResultList();

            //display students
            displayStudents(theStudents);

            // query students: lastName="sungur"
            System.out.println("Soyadı sungur olanlar getirilecektir ");
            theStudents = session.createQuery("from Student s where s.lastName='sungur'").getResultList();

            //display the students
            displayStudents(theStudents);

            //query students : lastName :"sungur" OR name="ibrahim"
            System.out.println("Soyadı sungur veya ismi ibrahim olanlar getirilecek");
            theStudents = session.createQuery("from Student s where s.lastName='sungur ' or s.name='abraham'").getResultList();

            //display the students
            displayStudents(theStudents);

            //query students where email LIKE '%gmail.com'
            theStudents = session.createQuery("from Student s where s.email like '%ham@gmail.com'").getResultList();

            System.out.println("\n\n studentlerden gmail'in sonu gmail.com ile bitenler");
            displayStudents(theStudents);



            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        }finally {
            factory.close();
        }
    }

    private static  void displayStudents(List<Student> theStudents){
        for (Student tempStudent : theStudents){
            System.out.println(tempStudent);
        }
    }
}
