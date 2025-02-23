package org.cyberscam.models;

public class User {
    private int UserID;
    private String UserName;
    private String Email;
    private String Password;
    private String Role;

    public User(){}
    public User(String UserName,String Email,String Password){
        this.UserName=UserName;
        this.Email=Email;
        this.Password=Password;
        this.Role="User";
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRole(){
        return Role;
    }

    public void setRole(String role) {
        this.Role=Role;
    }
}
