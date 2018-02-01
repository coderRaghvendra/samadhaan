package com.samadhaan4u.service.user;

import com.samadhaan4u.dto.UserDataDto;
import com.samadhaan4u.model.dao.UserDao;

/**
 * Created by raghvendra.mishra on 01/02/18.
 */
public class UserService {

    public UserDataDto getUserDataDto(String email, String password){

        UserDao userDao = new UserDao();
        userDao.getUser(email, password);
        return new UserDataDto();
    }
}
