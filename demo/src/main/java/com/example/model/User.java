package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    @JsonIgnore
    private boolean loggedin;
//    private int age;
//    private String address;
//    private String gender;

    public User( String username, String password) {
        this.username = username;
        this.password = password;
        this.loggedin = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password,
                loggedin);
    }
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username='" + username + '\'' + ", password='" + password + '\'' +
                ", loggedIn=" + loggedin + '}';
    }
}
