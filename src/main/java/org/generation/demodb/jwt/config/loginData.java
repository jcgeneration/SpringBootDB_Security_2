package org.generation.demodb.jwt.config;

public class loginData {
    private final String username;
    private final String password;

    public loginData(String username, String password) {
        this.username = username;
        this.password = password;
    }//constructor

    public String getUsername() {
        return username;
    }//getUsername

    public String getPassword() {
        return password;
    }//getPassword
}//class loginData
