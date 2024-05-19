package entity;

public class Customer extends User{
    public Customer() {
    }

    public Customer(String userId, String firstname, String lastname, String address,String avatar, String phone) {
        super(userId, firstname, lastname, address, avatar,  phone);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "userId='" + userId + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", avatar='" + avatar + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
