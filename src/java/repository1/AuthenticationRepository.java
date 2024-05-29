package repository1;


import config.DBConnect;
import entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthenticationRepository {
    public static User Verify(String username, String password) {
        try {
            String query = "Select * from tblAccount where username=? and passwordAcc=?";
            Connection con = DBConnect.getConnection();
            PreparedStatement preSt = con.prepareStatement(query);
            preSt.setString(1, username);
            preSt.setString(2, password);
            ResultSet rs = preSt.executeQuery();
            if (rs.next()) {
                //Check getID of username to select role
                String userID = rs.getString(3);

                if (userID.startsWith("A")) {
                    query = "SELECT * from tblAdmin where AdminID=?";
                    preSt = con.prepareStatement(query);
                    preSt.setString(1, userID);
                    rs = preSt.executeQuery();
                    System.out.println(rs.next());
                    String firstname = rs.getString(2),
                            lastname = rs.getString(3),
                            address = rs.getString(4),
                            avatar = rs.getString(5),
                            phone = rs.getString(6);
                    con.close();
                    Admin admin = new Admin(userID, firstname, lastname, address,avatar, phone);
                    return admin;

                } else if (userID.startsWith("E")) {
                    query = "SELECT * from tblEmployee where EmployeeID=?";
                    preSt = con.prepareStatement(query);
                    preSt.setString(1, userID);
                    rs = preSt.executeQuery();
                    rs.next();
                    String firstname = rs.getString(2),
                            lastname = rs.getString(3),
                            address = rs.getString(4),
                            avatar = rs.getString(5),
                            phone = rs.getString(6);
                    con.close();
                    return new Employee(userID, firstname, lastname, address,avatar, phone);
                } else if (userID.startsWith("U")) {
                    query = "SELECT * from tblCustomer where CustomerID=?";
                    preSt = con.prepareStatement(query);
                    preSt.setString(1, userID);
                    rs = preSt.executeQuery();
                    rs.next();

                   String firstname = rs.getString(2),
                            lastname = rs.getString(3),
                            address = rs.getString(4),
                            avatar = rs.getString(5),
                            phone = rs.getString(6);
                    con.close();
                    return new Customer(userID, firstname, lastname, address,avatar, phone);
                } else if (userID.startsWith("C")) {
                    query = "SELECT * from tblCTV where CTVID=?";
                    preSt = con.prepareStatement(query);
                    preSt.setString(1, userID);
                    rs = preSt.executeQuery();
                    rs.next();

                   String firstname = rs.getString(2),
                            lastname = rs.getString(3),
                            address = rs.getString(4),
                            avatar = rs.getString(5),
                            phone = rs.getString(6);
                    con.close();
                    return new Customer(userID, firstname, lastname, address,avatar, phone);
                }


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ok");
        return null;
    }


    


    public static  int getStatusAcc(String username){
        try {
            Connection con = DBConnect.getConnection();
            String query = "select StatusAcc from tblAccount\n" +
                    "where  Username=?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, username);

            ResultSet rs=stmt.executeQuery();
            if(rs.next()){
                return  rs.getInt(1);
            }


            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Loi method getStatusAcc(Cart cart,String orderId) trong OrderRepository.java ");
           return 0;
        }

        return 0;
    }
    
}
