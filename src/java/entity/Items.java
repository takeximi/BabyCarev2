package entity;


import java.text.DecimalFormat;
import repository1.OrderRepository;

public class Items {
    private Product product;
    private int amount;
    DecimalFormat formatter = new DecimalFormat("#,###,###");
    private boolean selected;  // Thêm thuộc tính này
    private String OrderId;

    

    public String getOrderID() {
        return OrderId;
    }

    public void setOrderID(String OrderId) {
        this.OrderId = OrderId;
    }
    

    public Items() {
    }

    public Items(Product product, int ammout) {
        this.product = product;
        this.amount = ammout;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int ammout) {
        this.amount = ammout;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    

    public double getPrice() {
        return product.getProductPrice() * amount;
    }
    public double getPriceAfterPurchase(String OrderId) {
        for (int i = 0 ; i < 10 ; i ++) {
            System.out.println(OrderId);
            System.out.println(product.getProductId());
            System.out.println("me");
            System.out.println(OrderRepository.getPriceOrdered(OrderId,product.getProductId()));
        }
        return OrderRepository.getPriceOrdered(OrderId,product.getProductId());
    }
    public String getPriceString() {
        return formatter.format(getPrice());

    }

    public boolean equal(Items item) {
        if (this.product.productId.compareToIgnoreCase(item.product.productId) == 0) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Items{" +
                "product=" + product +
                ", ammout=" + amount +
                ", formatter=" + formatter +
                '}';
    }
}
