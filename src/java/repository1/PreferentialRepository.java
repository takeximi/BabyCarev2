    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository1;

import config.DBConnect;
import entity.Preferential;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */


public class PreferentialRepository {

    public static void addPreferential(String Preferential, String PreferentialName, String StartDay, String EndDay, double Quantity, String PreferentiaDescription, String PreferentiaImg,String CTVID) throws SQLException, ClassNotFoundException {
    String sql = "INSERT INTO tblPreferential ( Preferential,  PreferentialName,  StartDay,  EndDay,  Quantity,  PreferentiaDescription,  PreferentiaImg) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ,?,?)";
    
    try (Connection con = DBConnect.getConnection();
         PreparedStatement stmt = con.prepareStatement(sql)) {
        
        stmt.setString(1, Preferential);
        stmt.setString(2, PreferentialName);
        stmt.setString(3, StartDay);
        stmt.setString(4, EndDay);
        stmt.setDouble(5, Quantity);
        stmt.setString(6, PreferentiaDescription);
        stmt.setString(7,PreferentiaImg); // Assuming StatusProduct is set to 1 as default
        stmt.setString(8,CTVID);

        stmt.executeUpdate();
        System.out.println("Preferential added successfully.");
        
    } catch (SQLException e) {
        System.out.println("Error in addPreferential");
        e.printStackTrace();
    }
}

    
    public static boolean checkExistPreferential(String Preferential) {
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT ProductID FROM tblProduct WHERE Preferential = ?");
            stmt.setString(1, Preferential);
            ResultSet resultSet = stmt.executeQuery();
            return resultSet.next();

        } catch (Exception e) {
            System.out.println("Error in checkExistProductID(String Preferential)");
            e.printStackTrace();
        }
        return false;
    }
     public static ArrayList<Preferential> getListPreferentialByCTVID(String CTVID) {
        ArrayList<Preferential> listPreferential = new ArrayList<>();
        try {
            String query = "SELECT * FROM tblPreferential WHERE CTVID = ?";
            Connection con = DBConnect.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, CTVID);
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                String preferential = results.getString(1);
                String preferentialName = results.getString(2);
                String startDay = results.getString(3);
                String endDay = results.getString(4);
                double quantity = results.getDouble(5);
                String preferentiaDescription = results.getString(6);
                String preferentiaImg = results.getString(7);
                Preferential preferentials = new Preferential(preferential, preferentialName, startDay, endDay, quantity, preferentiaDescription, preferentiaImg, CTVID);
                listPreferential.add(preferentials);
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy danh sách sản phẩm theo CTVID: " + e.getMessage());
        }
        return listPreferential;
    }
    
 public static ArrayList<Preferential> getListPreferential() {
        ArrayList<Preferential> listPreferential = new ArrayList<>();
        try {
            String query = "select * from tblPreferential";
            Connection con = DBConnect.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet results = stmt.executeQuery();
            while (results.next()) {
                String preferential = results.getString(1);
                String preferentialName = results.getString(2);
                String startDay = results.getString(3);
                String endDay = results.getString(4);
                double quantity = results.getDouble(5);
                String preferentiaDescription = results.getString(6);
                String preferentiaImg = results.getString(7);
                String CTVID = results.getString(8);
                Preferential preferentials = new Preferential( preferential,  preferentialName,  startDay,  endDay,  quantity,  preferentiaDescription,  preferentiaImg,  CTVID);
                listPreferential.add(preferentials);

            }
        } catch (Exception e) {
            System.err.println("Loi database method listPreferential class Preferential");
        }
        return listPreferential;
    }
 
    public static void main(String[] args) {
        try {
            // Fetch the list of Preferential
            ArrayList<Preferential> listPreferential = getListPreferential();

            // Check if the list is not empty
            if (listPreferential.isEmpty()) {
                System.out.println("No preferential data found.");
            } else {
                // Loop through the list and print each Preferential
                for (Preferential preferential : listPreferential) {
                    System.out.println("Preferential: " + preferential.getPreferential());
                    System.out.println("Preferential Name: " + preferential.getPreferentialName());
                    System.out.println("Start Day: " + preferential.getStartDay());
                    System.out.println("End Day: " + preferential.getEndDay());
                    System.out.println("Quantity: " + preferential.getQuantity());
                    System.out.println("Description: " + preferential.getPreferentiaDescription());
                    System.out.println("Image: " + preferential.getPreferentiaImg());
                    System.out.println("CTV ID: " + preferential.getCTVID());
                    System.out.println("-------------------------------------");
                }
            }
        } catch (Exception e) {
            System.err.println("Error while retrieving preferential data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void addOrUpdatePreferential(Preferential preferential) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
z

   

}
