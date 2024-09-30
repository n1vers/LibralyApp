package ee.ivkhkdev.model;

import java.util.UUID;

public class Customer {
    private UUID id;
    private String Firstname;
    private String Lastname;
    private String phone;

    public Customer() {
        this.id = UUID.randomUUID();
    }

    public Customer( String firstname, String lastname, String phone) {
        this.id = UUID.randomUUID();
        Firstname = firstname;
        Lastname = lastname;
        this.phone = phone;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;
        return id.equals(customer.id) && Firstname.equals(customer.Firstname) && Lastname.equals(customer.Lastname) && phone.equals(customer.phone);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + Firstname.hashCode();
        result = 31 * result + Lastname.hashCode();
        result = 31 * result + phone.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", Firstname='" + Firstname + '\'' +
                ", Lastname='" + Lastname + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
