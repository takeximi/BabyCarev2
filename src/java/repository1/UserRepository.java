package repository1;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import config.DBConnect;
import entity.Account;
import entity.Brand;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.PasswordAuthentication;
import org.apache.tomcat.util.codec.binary.Base64;
import service.MyRandom;

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

    public static boolean updateUser(String userID, String firstname, String lastname, String address, String avatar, String phone) {
        try {
            String query;
            if (userID.startsWith("A")) {
                query = "Update tblAdmin \n"
                        + "set FirstnameAd =?, LastnameAd= ?, AddressAd=?, Avatar=?, PhoneAd=?\n"
                        + "where AdminID = ?";
            } else if (userID.startsWith("E")) {
                query = "Update tblEmployee \n"
                        + "set FirstnameEmp =?, LastnameEmp= ?, AddressEmp=?, Avatar=?, PhoneEmp=?\n"
                        + "where EmployeeID = ?";
            } else if (userID.startsWith("U")) {
                query = "Update tblCustomer \n"
                        + "set FirstnameCus =?, LastnameCus= ?, AddressCus=?, Avatar=?, PhoneCus=?\n"
                        + "where CustomerID = ?";
            } else {
                query = "Update tblCTV \n"
                        + "set FirstnameCTV =?, LastnameCTV= ?, AddressCTV=?, Avatar=?, PhoneCTV=?\n"
                        + "where CTVID = ?";
            }

            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, firstname);
            preSt.setString(2, lastname);
            preSt.setString(3, address);
            preSt.setString(4, avatar);
            preSt.setString(5, phone);
            preSt.setString(6, userID);

            preSt.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean updateUser1(String userID, String firstname, String lastname, String address, String phone) {
        try {
            String query;
            if (userID.startsWith("A")) {
                query = "Update tblAdmin \n"
                        + "set FirstnameAd =?, LastnameAd= ?, AddressAd=?,  PhoneAd=?\n"
                        + "where AdminID = ?";
            } else if (userID.startsWith("E")) {
                query = "Update tblEmployee \n"
                        + "set FirstnameEmp =?, LastnameEmp= ?, AddressEmp=?,  PhoneEmp=?\n"
                        + "where EmployeeID = ?";
            } else if (userID.startsWith("U")) {
                query = "Update tblCustomer \n"
                        + "set FirstnameCus =?, LastnameCus= ?, AddressCus=?,  PhoneCus=?\n"
                        + "where CustomerID = ?";
            } else {
                query = "Update tblCTV \n"
                        + "set FirstnameCTV =?, LastnameCTV= ?, AddressCTV=?,  PhoneCTV=?\n"
                        + "where CTVID = ?";
            }

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

    public static void updateAvatar(String userID, String avatar) throws SQLException, ClassNotFoundException {
        String query;
        if (userID.startsWith("A")) {
            query = "UPDATE tblAdmin SET Avatar = ? WHERE AdminID = ?";
        } else if (userID.startsWith("E")) {
            query = "UPDATE tblEmployee SET Avatar = ? WHERE EmployeeID = ?";
        } else if (userID.startsWith("U")) {
            query = "UPDATE tblCustomer SET Avatar = ? WHERE CustomerID = ?";
        } else {
            query = "UPDATE tblCTV SET Avatar = ? WHERE CTVID = ?";
        }

        try (Connection conn = DBConnect.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, avatar);
            stmt.setString(2, userID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating user avatar", e);
        }
    }

    public static boolean checkExistID(String userID) {

        try {
            String query;
            if (userID.startsWith("A")) {
                query = "select AdminID from tblAdmin where AdminID=?";
            } else if (userID.startsWith("E")) {
                query = "select EmployeeID from tblEmployee where EmployeeID=?";
            } else {
                query = "select CustomerID from tblCustomer where CustomerID=?";
            }

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
            String query = "select * from tblAccount\n"
                    + "where Email=? \n";
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

    public static boolean addEmp(String userID, String firstname, String lastname, String address, String phone, String username, String password, String email) {

        try {

            String query = "insert into tblEmployee(EmployeeID,FirstnameEmp,LastnameEmp,AddressEmp, Avatar,PhoneEmp)\n"
                    + "values(?,?,?,?,?,?)";

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
            query = "insert into tblAccount (Username,PasswordAcc,UserID,StatusAcc,Email,Code)\n"
                    + "values(?,?,?,1,?,0)";
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

    public static ArrayList<Account> getListEmpAccount() {
        ArrayList<Account> listEmpAcc;
        try {

            String query = "select acc.Username,acc.UserID,acc.StatusAcc,e.FirstnameEmp as firstname\n"
                    + ",e.LastnameEmp as lastname,e.PhoneEmp as phone,acc.Email from tblAccount acc\n"
                    + "join tblEmployee e on e.EmployeeID=acc.UserID";

            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            ResultSet rs = preSt.executeQuery();
            listEmpAcc = new ArrayList<>();
            while (rs.next()) {
                String username = rs.getString(1);
                String userID = rs.getString(2);
                int status = rs.getInt(3);

                String firstname = rs.getString(4);
                String lastname = rs.getString(5);
                String phone = rs.getString(6);
                String email = rs.getString(7);
                Account newAcc = new Account(username, userID, firstname, lastname, phone, email, status);
                listEmpAcc.add(newAcc);
            }
            con.close();
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
        return listEmpAcc;
    }

    public static boolean lockEmployee(String empID) {
        try {
            String query = "update tblAccount set StatusAcc=0 where UserID=?";
            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, empID);
            preSt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean lockCustomer(String empID) {
        try {
            String query = "update tblAccount set StatusAcc=0 where UserID=?";
            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, empID);
            preSt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean unlockEmployee(String empID) {
        try {
            String query = "update tblAccount set StatusAcc=1 where UserID=?";
            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, empID);
            preSt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean unlockCustomer(String empID) {
        try {
            String query = "update tblAccount set StatusAcc=1 where UserID=?";
            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, empID);
            preSt.executeUpdate();

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
            if (rs.next()) {
                return oldPass.equals(rs.getString(1));
            } else {
                return false;
            }
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    public static boolean changePass(String userID, String newPass) {
        try {
            String query = "update tblAccount set PasswordAcc=?\n"
                    + "where UserID=?";
            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, newPass);
            preSt.setString(2, userID);
            preSt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static String MaHoa(String str) {
        String salt = "asjrlkmcoewj@tjle;oxqskjhdjksjf1jurVn";// Làm cho mật khẩu phức tap
        String result = null;

        str = str + salt;
        try {
            byte[] dataBytes = str.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            result = Base64.encodeBase64String(md.digest(dataBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // Tạo mã code ngẫu nhiên 6 chữ số
    private static String generateRandomCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    // Gửi mã code qua email
    private static void sendCodeToEmail(String email, String code) {
        final String username = "phuongnam121103@gmail.com"; // Thay bằng email của bạn
        final String password = "eqna xeml exop mnzc"; // Thay bằng mật khẩu email của bạn

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

            String query = "insert into tblCustomer(CustomerID,FirstnameCus,LastnameCus,AddressCus,Avatar, PhoneCus)\n"
                    + "values(?,?,?,?,?,?)";

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
            query = "insert into tblAccount (Username,PasswordAcc,UserID,StatusAcc,Email,Code)\n"
                    + "values(?,?,?,0,?,?)";
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
            String query = "select * from tblAccount where UserID=? and Code=?";
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
     public static boolean resendVerificationCode(String userID) {
        try {
            String newCode = generateRandomCode();

            // Update the code in the database
            String query = "update tblAccount set Code=? where UserID=?";
            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, newCode);
            preSt.setString(2, userID);
            int rowsUpdated = preSt.executeUpdate();
            con.close();

            if (rowsUpdated > 0) {
                // Get the email of the user
                query = "select Email from tblAccount where UserID=?";
                con = DBConnect.getConnection();
                preSt = con.prepareStatement(query);
                preSt.setString(1, userID);
                ResultSet rs = preSt.executeQuery();
                String email = null;
                if (rs.next()) {
                    email = rs.getString("Email");
                }
                con.close();

                if (email != null) {
                    // Send the new code to the email
                    sendCodeToEmail(email, newCode);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteCustomer(String customerID) {
        try {
            String query = "DELETE FROM tblCustomer WHERE CustomerID = ?";
            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, customerID);
            preSt.executeUpdate();
            con.close();

            query = "DELETE FROM tblAccount WHERE UserID = ?";
            con = DBConnect.getConnection();
            preSt = con.prepareStatement(query);
            preSt.setString(1, customerID);
            preSt.executeUpdate();
            con.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean sendVerifyCodeAndUpdatePassword(String userEmail) {
        try {
            String verificationCode = generateRandomCode(); // Generate random verification code
            // Send verification code to the user's email
            sendCodeToEmail(userEmail, verificationCode);

            // Update the password with the verification code
            boolean updated = UserRepository.updatePassword(userEmail, verificationCode);

            return updated;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updatePassword(String email, String newPassword) {
        try {
            Connection con = DBConnect.getConnection();
            String query = "UPDATE tblAccount SET PasswordAcc = ? WHERE Email = ?";
            PreparedStatement preSt = con.prepareStatement(query);
            newPassword = UserRepository.MaHoa(newPassword);
            preSt.setString(1, newPassword);
            preSt.setString(2, email);
            int rowsUpdated = preSt.executeUpdate();
            con.close();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateCustomerToCTV(String oldUserID) {
        try {
            // Generate new CTV UserID
            String newUserID = MyRandom.getRandomCTVID();

            Connection con = DBConnect.getConnection();

            // Get user email before updating
            String getEmailQuery = "SELECT Email FROM tblAccount WHERE UserID = ?";
            PreparedStatement getEmailStmt = con.prepareStatement(getEmailQuery);
            getEmailStmt.setString(1, oldUserID);
            ResultSet rs = getEmailStmt.executeQuery();
            String userEmail = null;
            if (rs.next()) {
                userEmail = rs.getString("Email");
            }

            // Update tblCustomer to tblCTV
            String updateCustomerQuery = "INSERT INTO tblCTV (CTVID, FirstnameCTV, LastnameCTV, AddressCTV, Avatar, PhoneCTV) "
                    + "SELECT ?, FirstnameCus, LastnameCus, AddressCus, Avatar, PhoneCus FROM tblCustomer WHERE CustomerID = ?";
            PreparedStatement updateCustomerStmt = con.prepareStatement(updateCustomerQuery);
            updateCustomerStmt.setString(1, newUserID);
            updateCustomerStmt.setString(2, oldUserID);
            updateCustomerStmt.executeUpdate();

            // Update UserID in tblAccount
            String updateAccountQuery = "UPDATE tblAccount SET UserID = ? WHERE UserID = ?";
            PreparedStatement updateAccountStmt = con.prepareStatement(updateAccountQuery);
            updateAccountStmt.setString(1, newUserID);
            updateAccountStmt.setString(2, oldUserID);
            updateAccountStmt.executeUpdate();
            
            // Delete old customer record
            String deleteCustomerQuery = "DELETE FROM tblCustomer WHERE CustomerID = ?";
            PreparedStatement deleteCustomerStmt = con.prepareStatement(deleteCustomerQuery);
            deleteCustomerStmt.setString(1, oldUserID);
            deleteCustomerStmt.executeUpdate();
            
            // Update CTVID in tblBrand
            String updateBrandQuery = "UPDATE tblBrand SET CTVID = ?, Status = 1 WHERE CTVID = ?";
            PreparedStatement updateBrandStmt = con.prepareStatement(updateBrandQuery);
            updateBrandStmt.setString(1, newUserID);
            updateBrandStmt.setString(2, oldUserID);
            updateBrandStmt.executeUpdate();
            con.close();

            // Send email notification
            if (userEmail != null) {
                sendUpdateNotificationEmail(userEmail, newUserID);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    

    private static void sendUpdateNotificationEmail(String email, String newUserID) {
        final String username = "phuongnam121103@gmail.com"; // Thay bằng email của bạn
        final String password = "eqna xeml exop mnzc"; // Thay bằng mật khẩu email của bạn

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
            message.setSubject("Account Update Notification");
            message.setText("Dear User,\n\nTài khoản đăng kí làm CTV đã được duyệt thành công. \n\nBest Regards,\nBabyCare Company");

            Transport.send(message);
            System.out.println("Update notification sent successfully.");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkExistBrandID(String brandID) {
        try {
            String query = "select * from tblBrand\n"
                    + "where BrandID=? \n";
            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, brandID);
            ResultSet rs = preSt.executeQuery();
            boolean checkBrandID = rs.next();
            con.close();
            return checkBrandID;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }

    }

    public static boolean checkBrandNameExist(String brandName) {
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement stmt = con.prepareStatement("select * from tblBrand  where BrandName =?");
            stmt.setString(1, brandName);
            ResultSet resultSet = stmt.executeQuery();
            return resultSet.next();

        } catch (Exception e) {
            System.out.println("loi checkBrandNameExsit(String brandName)");
            e.printStackTrace();
        }
        return false;
    }

    public static boolean adBrand1(String brandID, String brandName, String brandDescription, String brandLogo, String brandAddess, String email, String oldUserID) {
        try {
            // Generate new CTV UserID
            String newUserID = MyRandom.getRandomCTVID();

            Connection con = DBConnect.getConnection();
            // Update tblCustomer to tblCTV
            String updateCustomerQuery = "INSERT INTO tblCTV (CTVID, FirstnameCTV, LastnameCTV, AddressCTV, Avatar, PhoneCTV) "
                    + "SELECT ?, FirstnameCus, LastnameCus, AddressCus, Avatar, PhoneCus FROM tblCustomer WHERE CustomerID = ?";
            PreparedStatement updateCustomerStmt = con.prepareStatement(updateCustomerQuery);
            updateCustomerStmt.setString(1, newUserID);
            updateCustomerStmt.setString(2, oldUserID);
            updateCustomerStmt.executeUpdate();

            // Update UserID in tblAccount
            String updateAccountQuery = "UPDATE tblAccount SET UserID = ? WHERE UserID = ?";
            PreparedStatement updateAccountStmt = con.prepareStatement(updateAccountQuery);
            updateAccountStmt.setString(1, newUserID);
            updateAccountStmt.setString(2, oldUserID);
            updateAccountStmt.executeUpdate();

            // Delete old customer record
            String deleteCustomerQuery = "DELETE FROM tblCustomer WHERE CustomerID = ?";
            PreparedStatement deleteCustomerStmt = con.prepareStatement(deleteCustomerQuery);
            deleteCustomerStmt.setString(1, oldUserID);
            deleteCustomerStmt.executeUpdate();
            //inster to tblbrand
            String query = "insert into tblBrand(BrandID,BrandName,BrandDescription,BrandLogo,BrandAddress, CTVID)\n"
                    + "values(?,?,?,?,?,?)";
            PreparedStatement preSt = con.prepareStatement(query);
            preSt = con.prepareStatement(query);
            preSt.setString(1, brandID);
            preSt.setString(2, brandName);
            preSt.setString(3, brandDescription);
            preSt.setString(4, brandLogo);
            preSt.setString(5, brandAddess);
            preSt.setString(6, newUserID);
            preSt.executeUpdate();
            con.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
     private static final Logger logger = Logger.getLogger(UserRepository.class.getName());
     public static boolean addBrand(String brandID, String brandName, String brandDescription, String brandAddress, String userID) throws SQLException, ClassNotFoundException {
        boolean result = false;
        String query = "INSERT INTO tblBrand (BrandID, BrandName, BrandDescription, BrandLogo, BrandAddress, CTVID, Status) VALUES (?, ?, ?, ?, ?, ?,?)";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, brandID);
            ps.setString(2, brandName);
            ps.setString(3, brandDescription);
            ps.setString(4, "avatar.png");
            ps.setString(5, brandAddress);
            ps.setString(6, userID);
            ps.setInt(7, 0);

            logger.info("Executing query: " + ps.toString());

            result = ps.executeUpdate() > 0;
            if (result) {
                logger.info("Brand added successfully.");
            } else {
                logger.warning("Failed to add brand.");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error adding brand", e);
        }
        return result;
    }
   
    public static boolean hasPendingRegistration(String userId) {
        boolean hasPending = false;
        try {
            String query = "SELECT Status FROM tblBrand WHERE CTVID = ? AND Status = 0";
            Connection con = DBConnect.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            hasPending = rs.next(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hasPending;
    }


     public static ArrayList<Brand> getListBrandsWithStatusZero() {
    ArrayList<Brand> listBrands;
    try {
        String query = "SELECT BrandID, BrandName, BrandDescription, BrandLogo, BrandAddress, CTVID, Status "
                     + "FROM tblBrand "
                     + "WHERE Status = 0";

        Connection con = DBConnect.getConnection();
        PreparedStatement preSt = con.prepareStatement(query);
        ResultSet rs = preSt.executeQuery();
        listBrands = new ArrayList<>();
        while (rs.next()) {
            String brandID = rs.getString(1);
            String brandName = rs.getString(2);
            String brandDescription = rs.getString(3);
            String brandLogo = rs.getString(4);
            String brandAddress = rs.getString(5);
            String ctvid = rs.getString(6);
            int status = rs.getInt(7);

            Brand newBrand = new Brand(brandID, brandName, brandDescription, brandLogo, brandAddress, ctvid, status);
            listBrands.add(newBrand);
        }
        con.close();
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
    return listBrands;
}

    public static ArrayList<Account> getListCTVAccount() {
        ArrayList<Account> listEmpAcc;
        try {

            String query = "select acc.Username,acc.UserID,acc.StatusAcc,e.FirstnameCTV as firstname\n"
                    + ",e.LastnameCTV as lastname,e.PhoneCTV as phone,acc.Email from tblAccount acc\n"
                    + "join tblCTV e on e.CTVID=acc.UserID";

            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            ResultSet rs = preSt.executeQuery();
            listEmpAcc = new ArrayList<>();
            while (rs.next()) {
                String username = rs.getString(1);
                String userID = rs.getString(2);
                int status = rs.getInt(3);

                String firstname = rs.getString(4);
                String lastname = rs.getString(5);
                String phone = rs.getString(6);
                String email = rs.getString(7);
                Account newAcc = new Account(username, userID, firstname, lastname, phone, email, status);
                listEmpAcc.add(newAcc);
            }
            con.close();
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
        return listEmpAcc;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        String userID = "U223";
//        String username = "abcd";
//        String password = "123456789";
//        String firstname = "Đặng";
//        String lastname = "Nam";
//        String email = "khoiptde170417@fpt.edu.vn";
//        String address = "abc";
//        String phone = "0795632854";
//
//    
//        UserRepository.addCustomer(userID, firstname, lastname, address, phone, username, password, email);
//        
//        String oldUserID = "U1185"; // 
//        boolean result = UserRepository.updateCustomerToCTV(oldUserID);
//        if (result) {
//            System.out.println("Customer updated to CTV successfully.");
//        } else {
//            System.out.println("Failed to update customer to CTV.");
//        }
//    String userEmail = "khoiptde170417@fpt.edu.vn"; // Thay đổi thành địa chỉ email muốn gửi mã xác minh
//    String verificationCode = "123456"; // Thay đổi thành mã xác minh muốn gửi
//    
//    sendCodeToEmail(userEmail, verificationCode);

//        String userID = "U0010";
//        String firstname = "Đặng";
//        String lastname = "N";
//        String address = "abc";
//        String phone = "0795632854";
//
//        boolean result = updateUser1(userID, firstname, lastname, address, phone);
//
//        if (result) {
//            System.out.println("Cập nhật người dùng thành công!");
//        } else {
//            System.out.println("Cập nhật người dùng thất bại.");
//        }
//
//        String avatar = "img/avatar.png"; // Đặt tên của avatar mới
//        
//        try {
//            UserRepository.updateAvatar(userID, avatar);
//            System.out.println("Avatar updated successfully for user " + userID);
//        } catch (SQLException | ClassNotFoundException e) {
//            System.err.println("Error updating avatar: " + e.getMessage());
//        }
//    System.out.println(MaHoa("1234567"));
//    for (Account ac: getListEmpAccount()
//             ) {
//            System.out.println(ac);
//        }
//        
//        String brandID = MyRandom.getRandomBrandID();
//        String brandName = "Example Brrand";
//        String brandDescription = "This is an example brand description.";
//        String brandLogo = "http://example.com/logo.png";
//        String brandAddress = "123 Example Street";
//        String CTVID = "b";
//
//        boolean result = UserRepository.addBrand(brandID, brandName, brandDescription, brandLogo, brandAddress,CTVID);
//        if (result) {
//            System.out.println("succsecss");
//        }else{
//            System.out.println("fail");
//        }
//        String oldUserID = "U3853";
//
//            boolean result = updateCustomerToCTV(oldUserID);
//
//            if (result) {
//                System.out.println("Customer to CTV update successful.");
//            } else {
//                System.out.println("Customer to CTV update failed.");
//            }
//    ArrayList<Brand> brands = getListBrandsWithStatusZero();
//            if (brands != null) {
//                for (Brand brand : brands) {
//                    System.out.println(brand);
//                }
//            } else {
//                System.out.println("No brands found or an error occurred.");
//            }

        boolean a = UserRepository.hasPendingRegistration("U6772");
        System.out.println(a);
        }

    
    
}

