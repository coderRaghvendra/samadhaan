package com.samadhaan4u.service.request;

import com.samadhaan4u.model.dao.UserDao;
import com.samadhaan4u.model.entity.User;
import com.samadhaan4u.service.response.SignInResponse;
import com.samadhaan4u.service.response.SignUpResponse;

/**
 * Created by raghvendra.mishra on 01/02/18.
 */
public class SignInRequest extends AbstractRequest{

    private String email;
    private String password;

    public SignInRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SignInResponse process(){

        SignInResponse response = new SignInResponse();
        UserDao userDao = new UserDao();
        //check if email already exist
        if(!userDao.isEmailExist(email)){
            response.setMessage("Email do not exist");
        }else{
            response.setUser(userDao.select(email, password));
        }
        return response;
    }
}
