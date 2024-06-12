package service;
import config.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import repository1.UserRepository;

import java.util.Locale;
import java.util.Random;
import repository1.ProductRepository;

public class MyRandom {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final int LENGTH = 4;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String getRandomCusID() {
        StringBuilder sb ;
        do {
            sb = new StringBuilder();
            sb.append("U");

            for (int i = 1; i <= LENGTH; i++) {
                sb.append(new Random().nextInt(10));
            }
        }
        while(UserRepository.checkExistID(sb.toString()));
        return sb.toString();
    }
    public static String getRandomEmpID() {
        StringBuilder sb ;
        do {
            sb = new StringBuilder();
            sb.append("E");

            for (int i = 1; i <= LENGTH; i++) {
                sb.append(new Random().nextInt(10));
            }
        }
        while(UserRepository.checkExistID(sb.toString()));
        return sb.toString();
    }
    public static String getRandomCTVID() {
        StringBuilder sb ;
        do {
            sb = new StringBuilder();
            sb.append("C");

            for (int i = 1; i <= LENGTH; i++) {
                sb.append(new Random().nextInt(10));
            }
        }
        while(UserRepository.checkExistID(sb.toString()));
        return sb.toString();
    }
    public static String getRandomBrandID() {
        StringBuilder sb ;
        do {
            sb = new StringBuilder();
            sb.append("B");

            for (int i = 1; i <= LENGTH; i++) {
                sb.append(new Random().nextInt(10));
            }
        }
        while(UserRepository.checkExistID(sb.toString()));
        return sb.toString();
    }
    
    public static String getRandomProductID(){
        StringBuilder sb ;
        do {
            sb = new StringBuilder();
            sb.append("P");

            for (int i = 1; i <= LENGTH; i++) {
                sb.append(new Random().nextInt(10));
            }
        }
        while(ProductRepository.checkExistProductID(String.valueOf(sb)));
        return sb.toString();
    }
    
   public static void main(String[] args) {

    }
     
}
