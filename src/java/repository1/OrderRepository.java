package repository1;

import config.DBConnect;
import entity.*;
import service.Isvalid;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import service.MyRandom;

public class OrderRepository {

    public static String getOrderId() {
        try {
            String OrderId = MyRandom.generateRandomString();
            String query = "select BillID from tblBill";
            Connection con = DBConnect.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet results = stmt.executeQuery();
            ArrayList<String> listOrderID = new ArrayList<>();
            while (results.next()) {
                String OrderIdDB = results.getString(1);
                listOrderID.add(OrderIdDB);
            }
            for (String oDB : listOrderID) {
                if (oDB.equals(OrderId)) {
                    OrderId = MyRandom.generateRandomString();
                }
            }
            return OrderId;
        } catch (Exception e) {
            System.out.println("Loi method checkExistOrder(Cart cart ) trong OrderRepository.java ");
        }
        return null;
    }

//    public static String createOrder(Cart cart, User user, String CTVID) {
//        try {
//            Connection con = DBConnect.getConnection();
//            String query = "insert into tblBill (BillID,CTVID, CustomerID,AddressDelivery,DateCreate,PreferentialID,StatusBill) values (?,?,?,?,?,?,>)";
//            String orderID = getOrderId();
//            PreparedStatement stmt = con.prepareStatement(query);
//            stmt.setString(1, orderID);
//            stmt.setString(2, CTVID);
//            stmt.setString(3, user.getUserId());
//            stmt.setString(4, user.getAddress());
//            stmt.setString(5, Isvalid.getCurrentDate());
//            stmt.setString(6, cart.getDiscountCode());
//            if (cart.getPaymentType() == 0) {
//                stmt.setString(6, "Đang xử lý-COD");
//
//            } else {
//                stmt.setString(6, "Đang xử lý-CK");
//            }
//            stmt.executeUpdate();
//            con.close();
//            createOrderDetail(cart, orderID);
//            return orderID;
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Loi method createOrder(Cart cart, User user) trong OrderRepository.java ");
//        }
//        return null;
//    }
    public static String createOrder(List<Items> sellerItems, User user, String CTVID, String discountCode, int paymentType) {
        try {
            Connection con = DBConnect.getConnection();
            String query = "insert into tblBill (BillID, CTVID, CustomerID, AddressDelivery, DateCreate, PreferentialID, StatusBill) values (?,?,?,?,?,?,?)";
            String orderID = getOrderId();
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, orderID);
            stmt.setString(2, CTVID);
            stmt.setString(3, user.getUserId());
            stmt.setString(4, user.getAddress());
            stmt.setString(5, Isvalid.getCurrentDate());
            stmt.setString(6, discountCode);

            if (paymentType == 0) {
                stmt.setString(7, "Đang xử lý-COD");
            } else {
                stmt.setString(7, "Đang xử lý-CK");
            }

            stmt.executeUpdate();
            createOrderDetail(sellerItems, orderID, con);  // Pass the connection to keep it open for order details insertion
            con.close();
            return orderID;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi method createOrder(List<Items> sellerItems, User user, String CTVID, String discountCode, int paymentType) trong OrderRepository.java");
        }
        return null;
    }

    public static void createOrderDetail(List<Items> sellerItems, String orderID, Connection con) {
        // Implementation to add order details to the database
        try {
            String query = "insert into tblOrderDetails (BillID, ProductID, AmountProduct, PriceAtPuchase) values (?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(query);
            for (Items item : sellerItems) {
                stmt.setString(1, orderID);
                stmt.setString(2, item.getProduct().getProductId());
                stmt.setInt(3, item.getAmount());
                stmt.setDouble(4, item.getPrice());
                stmt.addBatch();
            }
            stmt.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi method createOrderDetail trong OrderRepository.java");
        }
    }

//    public static boolean createOrderDetail(Cart cart, String orderId) {
//        System.out.println("=>>>>....>>>>>>>>>>>>>>>>>>>>>>>" + orderId);
//        System.out.println(cart.getCart());
//        System.out.println(cart.getCart().get(0).getProduct().getProductId());
//        System.out.println(cart.getCart().get(0).getProduct().getProductAmount());
//        System.out.println(cart.getCart().get(0).getProduct().getProductPrice());
//        for (Items i : cart.getCart()) {
//            try {
//                Connection con = DBConnect.getConnection();
//                String query = "insert into tblOrderDetails values (?,?,?,?)";
//                PreparedStatement stmt = con.prepareStatement(query);
//                stmt.setString(1, orderId);
//                stmt.setString(2, i.getProduct().getProductId());
//                stmt.setInt(3, i.getAmount());
//                stmt.setDouble(4, i.getPrice());
//                stmt.executeUpdate();
//                con.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.out.println("Loi method createOrderDetail(Cart cart,String orderId) trong OrderRepository.java ");
//
//            }
//        }
//        return true;
//    }
    public static ArrayList<Items> getOrder(String OrderId) {
        try {
            ArrayList<Items> orderedList = new ArrayList<>();
            Connection con = DBConnect.getConnection();
            String query = "select * from tblOrderDetails where BillID = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, OrderId);
            ResultSet results = stmt.executeQuery();
            while (results.next()) {
                Items item = new Items();
                item.setAmount(results.getInt(3));
                item.setProduct(getProductById(results.getString(2)));
                //lay id product
                String productID = results.getString(2);
                item.getProduct().getImg();
                orderedList.add(item);
            }
            con.close();
            return orderedList;
        } catch (Exception e) {
            System.out.println("=============>ERROR :  ArrayList<Items> getOrder(String OrderId) <==============");
        }
        return null;
    }

    public static Product getProductById(String productID) {
        try {
            Connection con = DBConnect.getConnection();
            String query = "select * from tblProduct where ProductID=?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, productID);
            ResultSet results = stmt.executeQuery();
            if (results.next()) {
                String id = results.getString(1);
                String name = results.getString(2);
                double price = results.getDouble(4);
                con.close();
                return new Product(id, name, price);
            }
            con.close();
            return null;

        } catch (Exception e) {
            System.out.println("=========>ERROR :getProductById(String orderdetailId) <===========");
        }
        return null;
    }

    public static String getOrderStatus(String orderId) {
        String id = null;
        try {
            Connection con = DBConnect.getConnection();
            String query = "select StatusBill from tblBill where BillID = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, orderId);
            ResultSet results = stmt.executeQuery();
            if (results.next()) {
                id = results.getString(1);
            }
            con.close();
        } catch (Exception e) {
            System.out.println("==========>ERROR : getOrderStatus()<=============");
        }
        return id;
    }
    
    public static List<Items> getOrdersByBillId(String billId) {
        try {
            List<Items> orderItems = new ArrayList<>();
            Connection con = DBConnect.getConnection();
            String query = "SELECT * FROM tblOrderDetails WHERE BillID = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, billId);
            ResultSet results = stmt.executeQuery();
            while (results.next()) {
                String productId = results.getString("ProductID");
                int amount = results.getInt("AmountProduct");
                // Get product information
                Product product = getProductById(productId);
                if (product != null) {
                    Items item = new Items(product, amount);
                    orderItems.add(item);
                }
            }
            con.close();
            return orderItems;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi trong phương thức getOrdersByBillId trong OrderRepository.java");
        }
        return null;
    }

    public static ArrayList<String> getOrderIdList(String userId) {
        ArrayList<String> listOrderId = new ArrayList<>();
        try {
            Connection con = DBConnect.getConnection();
            String query = "select BillID from tblBill where CustomerID = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, userId);
            ResultSet results = stmt.executeQuery();
            while (results.next()) {
                listOrderId.add(results.getString(1));
            }
            con.close();
        } catch (Exception e) {
            System.out.println("==========>ERROR : getOrderIdList(String userId)<=============");
        }
        return listOrderId;
    }

    public static String getOrderDate(String orderId) {
        String date = null;
        try {
            Connection con = DBConnect.getConnection();
            String query = "select DateCreate from tblBill where BillID= ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, orderId);
            ResultSet results = stmt.executeQuery();
            if (results.next()) {
                date = results.getString(1);
            }
            con.close();
        } catch (Exception e) {
            System.out.println("==========>ERROR : getOrderStatus()<=============");
        }
        return date;
    }

    public static boolean acceptOrder(String orderId, String CTVID) {

        try {
            Connection con = DBConnect.getConnection();
            String query = "update tblBill set StatusBill=N'Đã xác nhận',CTVID=? where BillID=?";
            PreparedStatement stmt = con.prepareStatement(query);
            String accept = "Đã xác nhận";
            stmt.setString(1, CTVID);

            stmt.setString(2, orderId);
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println("==========>ERROR : acceptOrder()<=============");
            return false;
        }
        return true;
    }

    public static boolean cancelOrder(String orderId, String CTVID) {

        try {
            Connection con = DBConnect.getConnection();
            String query = "update tblBill set StatusBill=N'Đã hủy',CTVID=?  where BillID=?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, CTVID);
            stmt.setString(2, orderId);
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println("==========>ERROR : cancelOrder()<=============");
            return false;
        }
        return true;
    }
    
     public static boolean updateProductByCancelOrder(String orderId, String userId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            connection = DBConnect.getConnection();
            
            // Lấy danh sách sản phẩm và số lượng từ đơn hàng
            String querySelect = "SELECT ProductID, AmountProduct FROM tblOrderDetails WHERE BillID = ?";
            statement = connection.prepareStatement(querySelect);
            statement.setString(1, orderId);
            resultSet = statement.executeQuery();
            
            List<Items> itemsList = new ArrayList<>();
            
            while (resultSet.next()) {
                Items item = new Items();
                item.setProduct(new Product(resultSet.getString("ProductID")));
                item.setAmount(resultSet.getInt("AmountProduct"));
                itemsList.add(item);
            }
            
            // Cộng lại số lượng sản phẩm vào kho
            for (Items item : itemsList) {
                String queryUpdate = "UPDATE tblProduct SET Amount = Amount + ? WHERE ProductID = ?";
                PreparedStatement updateStatement = connection.prepareStatement(queryUpdate);
                updateStatement.setInt(1, item.getAmount());
                updateStatement.setString(2, item.getProduct().getProductId());
                updateStatement.executeUpdate();
            }
  
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    public static boolean acceptOrder1(String orderId, String CTVID) {
    try {
        Connection con = DBConnect.getConnection();

        // Lấy trạng thái hiện tại của đơn hàng
        String selectQuery = "SELECT StatusBill FROM tblBill WHERE BillID=?";
        PreparedStatement selectStmt = con.prepareStatement(selectQuery);
        selectStmt.setString(1, orderId);
        ResultSet rs = selectStmt.executeQuery();

        if (rs.next()) {
            String currentStatus = rs.getString("StatusBill");

            // Nếu trạng thái hiện tại là "Đang xử lý-CK", set trạng thái thành "Đã thanh toán"
            String newStatus;
            if ("Đang xử lý-CK".equalsIgnoreCase(currentStatus)) {
                newStatus = "Đã thanh toán";
            } else {
                newStatus = "Đã xác nhận";
            }

            // Cập nhật trạng thái đơn hàng
            String updateQuery = "UPDATE tblBill SET StatusBill=?, CTVID=? WHERE BillID=?";
            PreparedStatement updateStmt = con.prepareStatement(updateQuery);
            updateStmt.setString(1, newStatus);
            updateStmt.setString(2, CTVID);
            updateStmt.setString(3, orderId);
            updateStmt.executeUpdate();
        }

        con.close();
    } catch (Exception e) {
        System.out.println("==========>ERROR : acceptOrder()<=============");
        e.printStackTrace();
        return false;
    }
    return true;
}


//    public static ArrayList<OrderAccept> getAllOrder() {
//        ArrayList<OrderAccept> listOrder;
//        try {
//            listOrder = new ArrayList<>();
//            Connection con = DBConnect.getConnection();
//            String query = "select * from tblBill where StatusBill like N'Đang%'\n"
//                    + "order by DateCreate    ";
//            PreparedStatement stmt = con.prepareStatement(query);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                String orderID = rs.getString(1);
//                String ctvId = rs.getString(2);
//                String username = rs.getString(3);
//                String address = rs.getString(4);
//                String date = rs.getString(5);
//                String preferentialId = rs.getString(6);
//                String status = rs.getString(7);
//                OrderAccept orderAccept = new OrderAccept();
//                orderAccept.setIdOrder(orderID);
//                orderAccept.setUsername(username);
//                orderAccept.setAddress(address);
//                orderAccept.setDate(date);
//                orderAccept.setCTVID(ctvId);
//                orderAccept.setOrderStatus(status);
//                orderAccept.setDiscountId(preferentialId);
//                listOrder.add(orderAccept);
//            }
//
//            con.close();
//        } catch (Exception e) {
//            System.out.println("==========>ERROR : getAllOrder()<=============");
//            return null;
//        }
//        return listOrder;
//    }
    public static ArrayList<OrderAccept> getAllOrderByCTVId(String ctvId) {
        ArrayList<OrderAccept> listOrder = new ArrayList<>();
        try {
            Connection con = DBConnect.getConnection();
            String query = "SELECT * FROM tblBill WHERE CTVID = ? AND StatusBill LIKE N'Đang%' ORDER BY DateCreate";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, ctvId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String orderID = rs.getString(1);
                String username = rs.getString(3);
                String address = rs.getString(4);
                String date = rs.getString(5);
                String preferentialId = rs.getString(6);
                String status = rs.getString(7);
                
                OrderAccept orderAccept = new OrderAccept();
                orderAccept.setIdOrder(orderID);
                orderAccept.setUsername(username);
                orderAccept.setAddress(address);
                orderAccept.setDate(date);
                orderAccept.setCTVID(ctvId);
                orderAccept.setOrderStatus(status);
                orderAccept.setDiscountId(preferentialId);
                
                listOrder.add(orderAccept);
            }
            con.close();
        } catch (Exception e) {
            System.out.println("==========>ERROR : getAllOrderByCTVId()<=============");
            e.printStackTrace();
            return null;
        }
        return listOrder;
    }

    public static ArrayList<OrderAccept> getAllOrderPaidByCTVId(String CTVID) {
        ArrayList<OrderAccept> listOrder;
        try {
            listOrder = new ArrayList<>();
            Connection con = DBConnect.getConnection();
            String query = "select * from tblBill WHERE CTVID = ? AND StatusBill=N'Đã thanh toán'\n"
                    + "order by DateCreate desc";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, CTVID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String orderID = rs.getString(1);
                String username = rs.getString(3);
                String address = rs.getString(4);
                String date = rs.getString(5);
                String preferentialId = rs.getString(6);
                String status = rs.getString(7);
                OrderAccept orderAccept = new OrderAccept();
                orderAccept.setIdOrder(orderID);
                orderAccept.setUsername(username);
                orderAccept.setAddress(address);
                orderAccept.setDate(date);
                orderAccept.setCTVID(CTVID);
                orderAccept.setOrderStatus(status);
                orderAccept.setDiscountId(preferentialId);
                listOrder.add(orderAccept);
            }

            con.close();
        } catch (Exception e) {
            System.out.println("==========>ERROR : getAllOrderPaid()<=============");
            return null;
        }
        return listOrder;
    }

    public static ArrayList<OrderAccept> getAllOrderAcceptedByCTVId(String CTVID) {
        ArrayList<OrderAccept> listOrder;
        try {
            listOrder = new ArrayList<>();
            Connection con = DBConnect.getConnection();
            String query = "select * from tblBill WHERE CTVID = ? AND StatusBill=N'Đã xác nhận'\n"
                    + "order by DateCreate";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, CTVID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String orderID = rs.getString(1);
                String username = rs.getString(3);
                String address = rs.getString(4);
                String date = rs.getString(5);
                String preferentialId = rs.getString(6);
                String status = rs.getString(7);
                OrderAccept orderAccept = new OrderAccept();
                orderAccept.setIdOrder(orderID);
                orderAccept.setUsername(username);
                orderAccept.setAddress(address);
                orderAccept.setDate(date);
                orderAccept.setCTVID(CTVID);
                orderAccept.setOrderStatus(status);
                orderAccept.setDiscountId(preferentialId);
                listOrder.add(orderAccept);
            }

            con.close();
        } catch (Exception e) {
            System.out.println("==========>ERROR : cancelOrder()<=============");
            return null;
        }
        return listOrder;
    }

    public static ArrayList<OrderAccept> getAllOrderCancel(String CTVID) {
        ArrayList<OrderAccept> listOrder;
        try {
            listOrder = new ArrayList<>();
            Connection con = DBConnect.getConnection();
            String query = "select * from tblBill where StatusBill=N'Đã hủy'\n"
                    + "order by DateCreate desc";
            PreparedStatement stmt = con.prepareStatement(query);
              stmt.setString(1, CTVID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String orderID = rs.getString(1);
              
                String username = rs.getString(3);
                String address = rs.getString(4);
                String date = rs.getString(5);
                String preferentialId = rs.getString(6);
                String status = rs.getString(7);
                OrderAccept orderAccept = new OrderAccept();
                orderAccept.setIdOrder(orderID);
                orderAccept.setUsername(username);
                orderAccept.setAddress(address);
                orderAccept.setDate(date);
                orderAccept.setCTVID(CTVID);
                orderAccept.setOrderStatus(status);
                orderAccept.setDiscountId(preferentialId);
                listOrder.add(orderAccept);
            }

            con.close();
        } catch (Exception e) {
            System.out.println("==========>ERROR : cancelOrder()<=============");
            return null;
        }
        return listOrder;
    }

    public static double getPriceOrdered(String orderId, String productId) {

        double price = 0;
        try {
            Connection con = DBConnect.getConnection();
            String query = "select PriceAtPuchase from tblOrderDetails where ProductID=? and BillID =?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, productId);
            stmt.setString(2, orderId);
            ResultSet results = stmt.executeQuery();
            if (results.next()) {
                price = results.getDouble(1);
                System.out.println("=>>>>>>>>>>>>>>>>>>.." + price);
            }
            con.close();
        } catch (Exception e) {
            System.out.println("==========>ERROR : getPriceOrdered()<=============");
        }
        return price;
    }

    public static double getDiscountPercent(String discountID) {
        double quantity = 0f;
        try {
            Connection con = DBConnect.getConnection();
            String query = "select Quantity from tblPreferential where Preferential =?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, discountID);
            ResultSet results = stmt.executeQuery();
            if (results.next()) {
                quantity = results.getDouble(1);
                System.out.println("=>>>>>>>>>>>>>>>>>>.." + quantity);
            }
            con.close();
        } catch (Exception e) {
            System.out.println("==========>ERROR : getDiscountPercent()<=============");
        }
        return quantity;
    }

    public static String getDiscountCodeByOrderID(String orderid) {
        String discountCode = null;
        try {
            Connection con = DBConnect.getConnection();
            String query = "select PreferentialID from tblBill where BillID =?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, orderid);
            ResultSet results = stmt.executeQuery();
            if (results.next()) {
                discountCode = results.getString(1);
                System.out.println("=>>>>>>>>>>>>>>>>>>.." + discountCode);
            }
            con.close();
        } catch (Exception e) {
            System.out.println("==========>ERROR : getDiscountCodeByOrderID()<=============");
        }
        return discountCode;
    }

    public static boolean paidOrder(String orderId, String CTVID) {

        try {
            Connection con = DBConnect.getConnection();
            String query = "update tblBill set StatusBill=N'Đã thanh toán', CTVID=?  where BillID=?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, CTVID);
            stmt.setString(2, orderId);
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println("==========>ERROR : cancelOrder()<=============");
            return false;
        }
        return true;
    }

    public static boolean checkValidStatusOfPet(String orderId) {

        try {
            Connection con = DBConnect.getConnection();
            String query = "select b.BillID,p.PetID,p.StatusPet from tblBill b\n"
                    + "join tblOrderDetails o on o.BillID=b.BillID\n"
                    + "join tblPet p on o.ProductID=p.PetID\n"
                    + "where b.BillID=?";
            PreparedStatement stmt = con.prepareStatement(query);

            stmt.setString(1, orderId);
            ResultSet results = stmt.executeQuery();
            if (results.next()) {
                if (results.getInt(3) == 0) {
                    return false;
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println("==========>ERROR : checkValidStatusOfPet()<=============");
            return false;
        }
        return true;
    }

    public static boolean acceptedPet(String billID) {
        try {
            Connection con = DBConnect.getConnection();
            String query = "update tblPet \n"
                    + "set StatusPet=0\n"
                    + "where PetID in\n"
                    + "(\n"
                    + "select ProductID\n"
                    + "from tblOrderDetails\n"
                    + "where BillID=? and ProductID like 'P%'\n"
                    + ")";
            PreparedStatement stmt = con.prepareStatement(query);

            stmt.setString(1, billID);
            stmt.executeUpdate();

            con.close();
        } catch (Exception e) {
            System.out.println("==========>ERROR : acceptedPet()<=============");
            return false;
        }
        return true;
    }

    public static int getRemainingAmount(String foodID) {
        int amount = 0;
        try {
            Connection con = DBConnect.getConnection();
            String query = "select y.FoodID, y.SumAmount,z.SellAmount from \n"
                    + "(\n"
                    + "select imp.FoodID ,Sum(imp.Amount) as SumAmount  from tblImported imp\n"
                    + "group by imp.FoodID\n"
                    + ") y\n"
                    + "left join (\n"
                    + "select odr.ProductID,sum(odr.AmountProduct) as SellAmount from tblOrderDetails odr\n"
                    + "join tblBill on tblBill.BillID=odr.BillID\n"
                    + "where (StatusBill=N'Đã xác nhận' or StatusBill=N'Đã thanh toán')\n"
                    + "group by odr.ProductID\n"
                    + "\n"
                    + ")z on y.FoodID=z.ProductID \n"
                    + "where FoodID=?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, foodID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int sumAmount = rs.getInt(2);
                int sellAmount = rs.getInt(3);
                amount = sumAmount - sellAmount;
            }

            con.close();
        } catch (Exception e) {
            System.out.println("==========>ERROR : getRemainingAmount()<=============");
            return amount;
        }
        return amount;
    }

    public static boolean checkValidAmountOfPet(String billID) {

        try {
            Connection con = DBConnect.getConnection();
            String query = "select b.BillID,f.FoodID,od.AmountProduct from tblBill b \n"
                    + "join tblOrderDetails od on od.BillID=b.BillID \n"
                    + "join tblFood f on f.FoodID= od.ProductID\n"
                    + "where b.BillID=?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, billID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int remainingAmount = getRemainingAmount(rs.getString(2));
                int amount = rs.getInt(3);
                if (remainingAmount - amount < 0) {
                    return false;
                }
            }

            con.close();
        } catch (Exception e) {
            System.out.println("==========>ERROR : checkValidAmountOfPet()<=============");
            return false;
        }
        return true;

    }

//    public static ArrayList<Preferential> getListDiscount() {
//
//        ArrayList<Preferential> listP;
//        try {
//            Connection con = DBConnect.getConnection();
//            String query = "select * from tblPreferential";
//            PreparedStatement stmt = con.prepareStatement(query);
//            ResultSet rs = stmt.executeQuery();
//            listP = new ArrayList<Preferential>();
//            while (rs.next()) {
//                String id = rs.getString(1);
//                String preferentialName = rs.getString(2);
//                String startDay = rs.getString(3);
//                String endDay = rs.getString(4);
//                double rate = rs.getDouble(5);
//                Preferential newP = new Preferential(id, preferentialName, startDay, endDay, rate);
//                listP.add(newP);
//            }
//
//            con.close();
//        } catch (Exception e) {
//            System.out.println("==========>ERROR : getListDiscount()<=============");
//            return null;
//        }
//        return listP;
//
//    }

//    public static boolean createDiscount(Preferential p) {
//        try {
//            Connection con = DBConnect.getConnection();
//            String query = "insert into tblPreferential\n"
//                    + "(Preferential,PreferentialName,StartDay,EndDay,Quantity)\n"
//                    + "values (?,?,?,?,?)";
//            PreparedStatement stmt = con.prepareStatement(query);
//            stmt.setString(1, p.getId());
//            stmt.setString(2, p.getPreferentialName());
//            stmt.setString(3, p.getStartDay());
//            stmt.setString(4, p.getEndDay());
//            stmt.setDouble(5, p.getRate() / 100);
//            stmt.executeUpdate();
//
//            con.close();
//        } catch (Exception e) {
//            System.out.println("==========>ERROR : checkValidAmountOfPet()<=============");
//            return false;
//        }
//        return true;
//
//    }

    public static User getUserByBillID(String billID) {
        User user = null;

        try {
            Connection con = DBConnect.getConnection();
            String query = "select tblCustomer.* from tblBill\n"
                    + "join tblCustomer on tblBill.CustomerID=tblCustomer.CustomerID\n"
                    + "where BillID=?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, billID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(5));
            }

            con.close();
        } catch (Exception e) {
            System.out.println("==========>ERROR :()<=============");
            return null;
        }
        return user;

    }

    public static User getUserByBillID1(String billID) {
        User user = null;

        try {
            Connection con = DBConnect.getConnection();

            // Query to check CustomerID
            String customerQuery = "SELECT tblCustomer.* "
                    + "FROM tblBill "
                    + "JOIN tblCustomer ON tblBill.CustomerID = tblCustomer.CustomerID "
                    + "WHERE tblBill.BillID = ?";
            PreparedStatement stmt = con.prepareStatement(customerQuery);
            stmt.setString(1, billID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
            } else {
                // If no result from customerQuery, check CTVID
                String ctvQuery = "SELECT tblCTV.*"
                        + "FROM tblBill "
                        + "JOIN tblCTV ON tblBill.CTVID = tblCTV.CTVID "
                        + "WHERE tblBill.BillID = ?";
                stmt = con.prepareStatement(ctvQuery);
                stmt.setString(1, billID);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    user = new User(rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6));
                }
            }

            con.close();
        } catch (Exception e) {
            System.out.println("==========>ERROR :()<=============");
            return null;
        }
        return user;
    }

    public static void main(String[] args) {
//        String productId = "P9003";
//
//        // Appeler la méthode getProductById et afficher les résultats
//        Product product = getProductById(productId);
//        if (product != null) {
//            System.out.println("Product ID: " + product.getProductId());
//            System.out.println("Product Name: " + product.getProductName());
//            System.out.println("Product Price: " + product.getProductPrice());
//        } else {
//            System.out.println("No product found with Product ID: " + productId);
//        }
//
//        String orderId = "4qbcZKnZbY";
//
//        // Appeler la méthode getOrder et afficher les résultats
//        ArrayList<Items> orderedItems = OrderRepository.getOrder(orderId);
//        if (orderedItems != null) {
//            for (Items item : orderedItems) {
//                System.out.println("Product ID: " + item.getProduct().getProductId());
//                System.out.println("Product Name: " + item.getProduct().getProductName());
//                System.out.println("Product Price: " + item.getProduct().getProductPrice());
//                System.out.println("Amount: " + item.getAmount());
//                System.out.println();
//            }
//        } else {
//            System.out.println("No items found for Order ID: " + orderId);
//        }
//        String billID = "WqjXQDInPn";
//        User user = getUserByBillID1(billID);
//        if (user != null) {
//            System.out.println(user);
//        } else {
//            System.out.println("No user found for the given bill ID.");
//        }

  // Giả lập đối tượng User
//        User user = new User();
//        user.setUserId("U8516");
//        user.setAddress("123 Example St, City");
//        
//        // Giả lập đối tượng Product
//        Product product1 = new Product();
//        product1.setProductId("P001");
//        product1.setProductName("Product 1");
//        product1.setProductPrice(100.0);
//
//        Product product2 = new Product();
//        product2.setProductId("P002");
//        product2.setProductName("Product 2");
//        product2.setProductPrice(200.0);
//
//        // Giả lập đối tượng Items
//        Items item1 = new Items();
//        item1.setProduct(product1);
//        item1.setAmount(2);
//
//        Items item2 = new Items();
//        item2.setProduct(product2);
//        item2.setAmount(1);
//        
//
//        List<Items> sellerItems = new ArrayList<>();
//        sellerItems.add(item1);
//        sellerItems.add(item2);
//
//        // Giả lập CTVID, discountCode, và paymentType
//        String CTVID = "C7344";
//        String discountCode = "DISCOUNT10";
//        int paymentType = 1;  // 0 for COD, 1 for CK
//
//        // Gọi phương thức createOrder
//        String orderID = OrderRepository.createOrder(sellerItems, user, CTVID, discountCode, paymentType);
//        
//        if (orderID != null) {
//            System.out.println("Order created successfully with ID: " + orderID);
//        } else {
//            System.out.println("Failed to create order.");
//        }
//        ArrayList<OrderAccept> orders = getAllOrderByCTVId("C7344");
//        if (orders != null) {
//            for (OrderAccept order : orders) {
//                System.out.println(order);
//            }
//        } else {
//            System.out.println("No orders found for the given CTVId.");
//        }
//        
//        boolean result = OrderRepository.acceptOrder1("1uN6Un8I2n", "C7344");
//        if (result) {
//            System.out.println("succsess");
//        }else{
//            System.out.println("fail");
//        }

String orderId = "R5Gk4HEmfG";
        String userId = "U8516";

        boolean result = updateProductByCancelOrder(orderId, userId);
        if (result) {
            System.out.println("Order cancellation and product update were successful.");
        } else {
            System.out.println("Order cancellation or product update failed.");
        }
    }

}
