package org.cyberscam.services;
import org.cyberscam.dao.UserDA0;
import org.cyberscam.models.User;
public class UserServices {
    private UserDA0 userDA0 = new UserDA0();

    public boolean RegisterUser(User user){
        return userDA0.RegisterUser(user);
    }

    public User LoginUser(String Email,String Password){
        return userDA0.LoginUser(Email,Password);
    }

    public boolean UpdatePassword(String Email,String newPassword){
        return userDA0.UpdatePassword(Email,newPassword);
    }
}
