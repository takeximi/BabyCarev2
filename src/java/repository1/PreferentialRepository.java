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

   public static void addPreferential(String Preferential, String PreferentialName, String StartDay, String EndDay, int Quantity, String PreferentiaDescription, String PreferentiaImg,String EmployeeID) throws SQLException, ClassNotFoundException {
    String sql = "INSERT INTO tblPreferential ( Preferential,  PreferentialName,  StartDay,  EndDay,  Quantity,  PreferentiaDescription,  PreferentiaImg, EmployeeID) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?,?)";
    
    try (Connection con = DBConnect.getConnection();
         PreparedStatement stmt = con.prepareStatement(sql)) {
        
        stmt.setString(1, Preferential);
        stmt.setString(2, PreferentialName);
        stmt.setString(3, StartDay);
        stmt.setString(4, EndDay);
        stmt.setInt(5, Quantity);
        stmt.setString(6, PreferentiaDescription);
        stmt.setString(7,PreferentiaImg); // Assuming StatusProduct is set to 1 as default
        stmt.setString(8,EmployeeID);

        stmt.executeUpdate();
        System.out.println("Preferential added successfully.");
        
    } catch (SQLException e) {
        System.out.println("Error in addPreferential");
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        System.out.println("Error in addPreferential");
        e.printStackTrace();
        throw e; // Ném lại ngoại lệ để servlet có thể bắt và xử lý
    }
}

     public static ArrayList<Preferential> getListPreferentialByEmployeeID(String EmployeeID) {
        ArrayList<Preferential> listPreferential = new ArrayList<>();
        try {
            String query = "SELECT * FROM tblPreferential WHERE EmployeeID = ?";
            Connection con = DBConnect.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, EmployeeID);
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                String preferential = results.getString(1);
                String preferentialName = results.getString(2);
                String startDay = results.getString(3);
                String endDay = results.getString(4);
                int quantity = results.getInt(5);
                String preferentiaDescription = results.getString(6);
                String preferentiaImg = results.getString(7);
                Preferential preferentials = new Preferential(preferential, preferentialName, startDay, endDay, quantity, preferentiaDescription, preferentiaImg, EmployeeID);
                listPreferential.add(preferentials);
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy danh sách sản phẩm theo EmployeeID: " + e.getMessage());
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
                int quantity = results.getInt(5);
                String preferentiaDescription = results.getString(6);
                String preferentiaImg = results.getString(7);
                String EmployeeID = results.getString(8);
                Preferential preferentials = new Preferential( preferential,  preferentialName,  startDay,  endDay,  quantity,  preferentiaDescription,  preferentiaImg,  EmployeeID);
                listPreferential.add(preferentials);

            }
        } catch (Exception e) {
            System.err.println("Loi database method listPreferential class Preferential");
        }
        return listPreferential;
    }
 
   public static void main(String[] args) {
    try {
        String preferential = "PREF00121";
        String preferentialName = "Discount 10%";
        String startDay = "2022-01-01";
        String endDay = "2022-12-31";
        int quantity = 100;
        String description = "10% off on all products";
        String imgPath = "image.jpg";
        String EmployeeID = "E0622";

        // Gọi hàm addPreferential để kiểm thử
        addPreferential(preferential, preferentialName, startDay, endDay, quantity, description, imgPath, EmployeeID);

        // Kiểm tra xem liệu dữ liệu có được thêm thành công hay không
        System.out.println("Preferential added successfully.");
    } catch (SQLException | ClassNotFoundException ex) {
        System.err.println("Error occurred: " + ex.getMessage());
        ex.printStackTrace();
    }
}
    public static void addOrUpdatePreferential(Preferential preferential) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

}

