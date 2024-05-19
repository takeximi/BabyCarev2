package repository1;

import config.DBConnect;
import entity.Account;
import entity.User;
import javax.mail.PasswordAuthentication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.PasswordAuthentication;

public class UserRepository {
    public static boolean checkUserNameExist(String username) {
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement stmt = con.prepareStatement("select * from tblCustomer  where UserName =?");
            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();
            return resultSet.next();

        } catch (Exception e) {
            System.out.println("loi checkUserNameExsit(String userName)");
            e.printStackTrace();
        }
        return false;
    }


    public static boolean updateUser(String userID, String firstname, String lastname, String address, String phone) {
        try {
            String query;
            if (userID.startsWith("A"))
                query = "Update tblAdmin \n" +
                        "set FirstnameAd =?, LastnameAd= ?, AddressAd=?,PhoneAd=?\n" +
                        "where AdminID = ?";
            else if (userID.startsWith("E"))
                query = "Update tblEmployee \n" +
                        "set FirstnameEmp =?, LastnameEmp= ?, AddressEmp=?,PhoneEmp=?\n" +
                        "where EmployeeID = ?";
            else if (userID.startsWith("C"))
                query = "Update tblCTV \n" +
                        "set FirstnameEmp =?, LastnameEmp= ?, AddressEmp=?,PhoneEmp=?\n" +
                        "where EmployeeID = ?";
            else query = "Update tblCustomer \n" +
                        "set FirstnameCus =?, LastnameCus= ?, AddressCus=?,PhoneCus=?\n" +
                        "where CustomerID = ?";

            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, firstname);
            preSt.setString(2, lastname);
            preSt.setString(3, address);
            preSt.setString(4, phone);
            preSt.setString(5, userID);

            preSt.executeUpdate();
            con.close();
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
        return true;
    }


    public static boolean checkExistID(String userID) {

        try {
            String query;
            if (userID.startsWith("A"))
                query = "select AdminID from tblAdmin where AdminID=?";
            else if (userID.startsWith("E"))
                query = "select EmployeeID from tblEmployee where EmployeeID=?";
            else query = "select CustomerID from tblCustomer where CustomerID=?";

            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, userID);
            ResultSet rs = preSt.executeQuery();
            boolean checkID = rs.next();
            con.close();
            return checkID;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }

    }

    public static boolean checkExistUsername(String username) {
        try {
            String query = "select * from tblAccount where Username=?";
            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, username);
            ResultSet rs = preSt.executeQuery();
            boolean checkID = rs.next();
            con.close();
            return checkID;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkExistEmail(String email) {
        try {
            String query = "select * from tblAccount\n" +
                    "where Email=? \n";
            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, email);
            ResultSet rs = preSt.executeQuery();
            boolean checkEmail = rs.next();
            con.close();
            return checkEmail;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    public static boolean addEmployee(String userID, String firstname, String lastname, String address, String phone, String username, String password) {

        try {

            String query = "\n" +
                    "insert into tblEmployee values \n" +
                    "(?,?,?,?,?)\n";


            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, userID);
            preSt.setString(2, firstname);
            preSt.setString(3, lastname);
            preSt.setString(4, address);
            preSt.setString(5, "avatar.png");
            preSt.setString(6, phone);

            //insert to tblCustomer
            preSt.executeUpdate();
            //inster to tblAccount
            query = "insert into tblAccount values\n" +
                    "(?,?,?,1)";

            preSt = con.prepareStatement(query);
            preSt.setString(1, username);
            preSt.setString(2, password);
            preSt.setString(3, userID);
            preSt.executeUpdate();
            con.close();
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
        return true;
    }


//    public static boolean addCustomer(String userID, String firstname, String lastname, String address, String phone, String username, String password, String email) {
//
//
//        try {
//
//            String query = "insert into tblCustomer(CustomerID,FirstnameCus,LastnameCus,AddressCus,Avatar, PhoneCus)\n" +
//                    "values(?,?,?,?,?,?)";
//
//
//            Connection con = DBConnect.getConnection();
//            PreparedStatement preSt = con.prepareStatement(query);
//            preSt.setString(1, userID);
//            preSt.setString(2, firstname);
//            preSt.setString(3, lastname);
//            preSt.setString(4, address);
//            preSt.setString(5, "avatar.png");
//            preSt.setString(6, phone);
//            //insert to tblCustomer
//            preSt.executeUpdate();
//            con.close();
//            con = DBConnect.getConnection();
//            //inster to tblAccount
//            query = "insert into tblAccount (Username,PasswordAcc,UserID,StatusAcc,Email,Code)\n" +
//                    "values(?,?,?,1,?,0)";
//            preSt = con.prepareStatement(query);
//            preSt.setString(1, username);
//            preSt.setString(2, password);
//            preSt.setString(3, userID);
//            preSt.setString(4, email);
//
//            preSt.executeUpdate();
//
//            con.close();
//        } catch (Exception e) {
//
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
    public static boolean addEmp(String userID, String firstname, String lastname, String address, String phone, String username, String password, String email) {


        try {

            String query = "insert into tblEmployee(EmployeeID,FirstnameEmp,LastnameEmp,AddressEmp, Avatar,PhoneEmp)\n" +
                    "values(?,?,?,?,?,?)";


            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, userID);
            preSt.setString(2, firstname);
            preSt.setString(3, lastname);
            preSt.setString(4, address);
            preSt.setString(5, "avatar.png");
            preSt.setString(6, phone);
            //insert to tblCustomer
            preSt.executeUpdate();
            con.close();
            con = DBConnect.getConnection();
            //inster to tblAccount
            query = "insert into tblAccount (Username,PasswordAcc,UserID,StatusAcc,Email,Code)\n" +
                    "values(?,?,?,0,?,0)";
            preSt = con.prepareStatement(query);
            preSt.setString(1, username);
            preSt.setString(2, password);
            preSt.setString(3, userID);
            preSt.setString(4, email);

            preSt.executeUpdate();

            con.close();
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean checkOldPass(String userID, String oldPass) {
        try {
            String query = "select PasswordAcc from tblAccount where UserID=?";
            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, userID);
            ResultSet rs = preSt.executeQuery();
            if (rs.next())
                return oldPass.equals(rs.getString(1));
            else return false;
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }
    public static boolean changePass(String userID, String newPass) {
        try {
            String query = "update tblAccount set PasswordAcc=?\n" +
                    "where UserID=?";
            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1,newPass);
            preSt.setString(2, userID);
            preSt.executeUpdate();


        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static ArrayList<Account> getListEmpAccount() {
        ArrayList<Account> listEmpAcc;
        try {

            String query = "select acc.Username,acc.UserID,acc.StatusAcc,e.FirstnameEmp as firstname\n" +
                    ",e.LastnameEmp as lastname,e.PhoneEmp as phone,acc.Email from tblAccount acc\n" +
                    "join tblEmployee e on e.EmployeeID=acc.UserID";


            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            ResultSet rs=preSt.executeQuery();
            listEmpAcc=new ArrayList<>();
            while(rs.next()){
                String username = rs.getString(1);
                String userID = rs.getString(2);
                int status=rs.getInt(3);

                String firstname = rs.getString(4);
                String lastname = rs.getString(5);
                String phone = rs.getString(6);
                String email = rs.getString(7);
               Account newAcc=new Account( username,  userID,  firstname,  lastname,  phone,  email,  status);
               listEmpAcc.add(newAcc);
            }
            con.close();
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
        return listEmpAcc;
    }
  public static boolean  lockEmployee(String empID){
        try {
            String query = "update tblAccount set StatusAcc=0 where UserID=?";
            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1,empID);
            preSt.executeUpdate();


        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean  lockCustomer(String empID){
        try {
            String query = "update tblAccount set StatusAcc=0 where UserID=?";
            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1,empID);
            preSt.executeUpdate();


        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean  unlockEmployee(String empID){
        try {
            String query = "update tblAccount set StatusAcc=1 where UserID=?";
            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1,empID);
            preSt.executeUpdate();


        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean  unlockCustomer(String empID){
        try {
            String query = "update tblAccount set StatusAcc=1 where UserID=?";
            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1,empID);
            preSt.executeUpdate();


        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static ArrayList<Account> getListCusAccount() {
        ArrayList<Account> listCusAcc;
        try {

            String query = "select acc.Username,acc.UserID,acc.StatusAcc,cus.FirstnameCus as firstname,cus.LastnameCus as lastname,cus.PhoneCus as phone,acc.Email from tblAccount acc\n" +
                    "join tblCustomer cus on cus.CustomerID=acc.UserID";


            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            ResultSet rs=preSt.executeQuery();
            listCusAcc=new ArrayList<>();
            while(rs.next()){
                String username = rs.getString(1);
                String userID = rs.getString(2);
                int status=rs.getInt(3);

                String firstname = rs.getString(4);
                String lastname = rs.getString(5);
                String phone = rs.getString(6);
                String email = rs.getString(7);
                Account newAcc=new Account( username,  userID,  firstname,  lastname,  phone,  email,  status);
                listCusAcc.add(newAcc);
            }
            con.close();
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
        return listCusAcc;
    }
    // Tạo mã code ngẫu nhiên 6 chữ số
    private static String generateRandomCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    // Gửi mã code qua email
    private static void sendCodeToEmail(String email, String code) {
        final String username = "GuercioCarovski@gmail.com"; // Thay bằng email của bạn
        final String password = "cag2bukd5o"; // Thay bằng mật khẩu email của bạn

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(email));
            message.setSubject("Verification Code");
            message.setText("Your verification code is: " + code);
            Transport.send(message);
            System.out.println("Code sent successfully.");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    // Thêm khách hàng và gửi mã code
    public static boolean addCustomer(String userID, String firstname, String lastname, String address, String phone, String username, String password, String email) {
        try {
            String code = generateRandomCode(); 

            String query = "insert into tblCustomer(CustomerID,FirstnameCus,LastnameCus,AddressCus,Avatar, PhoneCus)\n" +
                    "values(?,?,?,?,?,?)";


            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, userID);
            preSt.setString(2, firstname);
            preSt.setString(3, lastname);
            preSt.setString(4, address);
            preSt.setString(5, "avatar.png");
            preSt.setString(6, phone);
            //insert to tblCustomer
            preSt.executeUpdate();
            con.close();
            con = DBConnect.getConnection();
            //inster to tblAccount
            query = "insert into tblAccount (Username,PasswordAcc,UserID,StatusAcc,Email,Code)\n" +
                    "values(?,?,?,0,?,?)";
            preSt = con.prepareStatement(query);
            preSt.setString(1, username);
            preSt.setString(2, password);
            preSt.setString(3, userID);
            preSt.setString(4, email);
            preSt.setString(5, code);
            preSt.executeUpdate();
            con.close();
        
            // Gửi mã code qua email
            sendCodeToEmail(email, code);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Kiểm tra mã code và cập nhật trạng thái
    public static boolean verifyCodeAndUpdateStatus(String userID, String code) {
        try {
            String query = "select * from Account where UserID=? and Code=?";
            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, userID);
            preSt.setString(2, code);
            ResultSet rs = preSt.executeQuery();
            if (rs.next()) {
                // Nếu mã code đúng, cập nhật trạng thái tài khoản
                query = "update tblAccount set StatusAcc=1 where UserID=?";
                preSt = con.prepareStatement(query);
                preSt.setString(1, userID);
                preSt.executeUpdate();
                return true;
            } else {
                return false; 
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        String userID = "U223";
        String username = "abcd";
        String password = "123456789";
        String firstname = "Đặng";
        String lastname = "Nam";
        String email = "khoiptde170417@fpt.edu.vn";
        String address = "abc";
        String phone = "0795632854";

    
        UserRepository.addCustomer(userID, firstname, lastname, address, phone, username, password, email);
        
        
    }
    
}
