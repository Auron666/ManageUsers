package ru.home.model;

import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "users")
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;
    private String birthday;
    private String login;
    private String password;
    private String about;
    private String address;

    public User(){
        super();
    }

    public User(String firstname, String lastname, String birthday, String login, String password, String about, String address) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.login = login;
        this.password = password;
        this.about = about;
        this.address = address;
    }


    public Long getId() {
        return id;
    }
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public String getBirthday() {
        return birthday;
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
    public String getAbout() {
        return about;
    }
    public String getAddress() {
        return address;
    }

    @Override
    public String toString(){
        return String.format("User: [id=%d, firstName='%s', lastName='%s', birthday='%s', login='%s', password='%s', about='%s', address='%s']", id, firstname, lastname, birthday, login, password,
                about, address);
    }

}
