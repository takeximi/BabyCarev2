package entity;

public class Admin extends User{
    public Admin() {
    }

    public Admin(String userId, String firstname, String lastname, String address,String avatar, String phone) {
        super(userId, firstname, lastname, address,avatar, phone);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "userId='" + userId + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", avatar='" + avatar + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

}
