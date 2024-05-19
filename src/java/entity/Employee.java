package entity;

public class Employee extends User{
    public Employee() {
    }

    public Employee(String userId, String firstname, String lastname, String address,String avatar, String phone) {
        super(userId, firstname, lastname, address, avatar , phone);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "userId='" + userId + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", avatar='" + avatar + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
