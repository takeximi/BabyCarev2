package entity;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Product {
    DecimalFormat formatter = new DecimalFormat("#,###,###.##");

    protected String productId;
    protected String productName;
    protected String productType;
    protected String origin;
    protected double productPrice;
    protected int productAmount;
    protected String img;
    protected String CTVID;

    protected int status;
    public Product() {
    }

   

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Product(String productId, String productName, double productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public Product(String productId, String productName, String productType, String origin, double productPrice, int productAmount, String img, int status) {
        this.productId = productId;
        this.productName = productName;
        this.origin = origin;
        this.productType = productType;
        this.productPrice = productPrice;
        this.productAmount = productAmount;
        this.img = img;
        this.status = status;
    }
    public Product(String productId, String productName, String productType, String origin, double productPrice, int productAmount,String img, int status,String CTVID) {
        this.productId = productId;
        this.productName = productName;
        this.origin = origin;
        this.productType = productType;
        this.productPrice = productPrice;
        this.productAmount = productAmount;
        this.status = status;
        this.img = img;
        this.CTVID = CTVID;
    }

    public Product(String productId, String productName, String productType, double productPrice, int productAmount) {
        this.productId = productId;
        this.productName = productName;
        this.productType = productType;
        this.productPrice = productPrice;
        this.productAmount = productAmount;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCTVID() {
        return CTVID;
    }

    public void setCTVID(String CTVID) {
        this.CTVID = CTVID;
    }
    
    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public String getPriceString() {
        return formatter.format(productPrice);

    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
    public String getPriceToDecimal(){
        DecimalFormat decimalFormat = new DecimalFormat("#,###,###.##");
        String formattedValue = decimalFormat.format(productPrice);
       formattedValue= formattedValue.replace(".","");
       formattedValue= formattedValue.replace(",",".");
        return  formattedValue;
    }

    @Override
    public String toString() {
        return "Product{" + "formatter=" + formatter + ", productId=" + productId + ", productName=" + productName + ", productType=" + productType + ", origin=" + origin + ", productPrice=" + productPrice + ", productAmount=" + productAmount + ", img=" + img + ", CTVID=" + CTVID + ", status=" + status + '}';
    }
    
}
