package uz.demo.app.demo.service.vm;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Valid
public class UserVM {

    @NotNull(message = "First name should not be empty") //Bu habarni localization code sifatida yuborsa ham bo'ladi, front tarafda o'zlari tarjima qilib oladi.
    @Size(min = 2, max = 50, message = "Length should be between 2 and 50")
    private String firstName;

    @NotNull(message = "Last name should not be empty") //Bu habarni localization code sifatida yuborsa ham bo'ladi, front tarafda o'zlari tarjima qilib oladi.
    @Size(min = 2, max = 50, message = "Length should be between 2 and 50")
    private String lastName;

    private Date birthday;

    @Size(max = 255)
    private String address;

    @NotNull(message = "Username should not be empty")
    @Size(min = 5, max = 50, message = "Length should be between 5 and 50")
    private String username;

    @NotNull
    @Size(min = 4, max = 100)
    private String password;

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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserVM{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
