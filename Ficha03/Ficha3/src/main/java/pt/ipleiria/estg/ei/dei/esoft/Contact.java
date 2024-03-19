package pt.ipleiria.estg.ei.dei.esoft;

import java.util.Date;
import java.util.Objects;

public class Contact {
    private String firstName;
    private String lastName;
    private Date birthday;
    private String phone;
    private String email;

    public Contact(String firstName, String lastName, String phone, String email, Date birthday){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.birthday = birthday;
    }

    public Contact(String firstName,String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Contact(String firstName, String lastName, String phone){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public Contact(String firstName, String lastName, String phone, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Contact
                && (
                Objects.equals(this.phone, ((Contact) o).phone)
                        || Objects.equals(this.email, ((Contact) o).email)
        );
    }
    @Override
    public int hashCode() {
        return Objects.hash(phone, email);
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
