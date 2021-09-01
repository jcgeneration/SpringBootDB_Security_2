package org.generation.demodb.users;
import org.generation.utils.SHAUtil;

import javax.persistence.*;
@Entity
@Table(name="user")
public class user { // Plan Old Java Object - POJO
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="id", unique = true, nullable = false)
    private Long id;
    @Column(name="username", nullable = false)
    private String username;
    @Column(name="password", nullable = false)
    private String password;
    public user(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password =  SHAUtil.createHash(password);
    }//constructor

    public user(){}//default constructor

    public Long getId() {
        return id;
    }//getId

    public void setId(Long id) {
        this.id = id;
    }//setId

    public String getUsername() {
        return username;
    }//getUserName

    public void setUsername(String username) {

        this.username =username;
    }//setUserName

    public String getPassword() {
        return password;
    }//getPassword

    public void setPassword(String password) {
        this.password =  SHAUtil.createHash(password);
    }//setPassword

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }//toString
}//class
