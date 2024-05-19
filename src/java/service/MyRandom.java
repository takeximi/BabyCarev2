package service;

import repository.ProductRepository;
import repository.UserRepository;

import java.util.Locale;
import java.util.Random;

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

//    public static String getRandomPetID(){
//        StringBuilder sb ;
//        do {
//            sb = new StringBuilder();
//            sb.append("P");
//
//            for (int i = 1; i <= LENGTH; i++) {
//                sb.append(new Random().nextInt(10));
//            }
//        }
//        while(ProductRepository.checkExistPetID(String.valueOf(sb)));
//        return sb.toString();
//    }
//    public static String getRandomFoodID(){
//        StringBuilder sb ;
//        do {
//            sb = new StringBuilder();
//            sb.append("F");
//
//            for (int i = 1; i <= LENGTH; i++) {
//                sb.append(new Random().nextInt(10));
//            }
//        }
//        while(ProductRepository.checkExistFoodID(String.valueOf(sb)));
//        return sb.toString();
//    }
    public static String getRandomOTP(){
        StringBuilder sb ;

            sb = new StringBuilder();
            sb.append("F");

            for (int i = 1; i <= LENGTH; i++) {
                sb.append(ALPHABET.charAt(new Random().nextInt(26)));
            }

        return sb.toString();
    }
   
    public static String getRandomServiceBillID() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(20);

        for (int i = 0; i < 20; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }

     public static String getRandomDiscount() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(5);
        
        // Thêm "DC" vào đầu chuỗi
        sb.append("DC");

        // Thêm 3 chữ số ngẫu nhiên vào chuỗi
        for (int i = 0; i < 3; i++) {
            int randomNumber = random.nextInt(10); // Số ngẫu nhiên từ 0 đến 9
            sb.append(randomNumber);
        }

        return sb.toString();
    }
     
      public static void main(String[] args) {
        System.out.println(getRandomDiscount());
    }
     
}
