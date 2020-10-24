package uz.demo.app.demo.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import uz.demo.app.demo.model.User;
import java.util.Date;

public class UserDTO {

    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    private String firstName;

    private String lastName;

    private Date birthday;

    private String address;

    private String displayName;

    private Date createdDate;

    public UserDTO() {
    }

    public UserDTO(Long id, String username, String password, String firstName, String lastName, Date birthday, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.address = address;
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.birthday = user.getBirthday();
        this.address = user.getAddress();
        this.displayName = user.getDisplayName();
        this.createdDate = Date.from(user.getCreatedDate().toInstant());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
