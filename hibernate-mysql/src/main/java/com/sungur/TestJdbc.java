package com.sungur;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {
    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/hibernate-tutorial?useSSL=false";
        String user = "abraham";
        String pass = "12345";
        try {

            System.out.println("Veritabani baglanti url :"+jdbcUrl);
            Connection conn = DriverManager.getConnection(jdbcUrl,user,pass);
            System.out.println("Veritabanina basarili bir sekilde baglandi.");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
