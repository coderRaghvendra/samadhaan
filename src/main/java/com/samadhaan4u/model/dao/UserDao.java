package com.samadhaan4u.model.dao;

import com.samadhaan4u.dto.constant.UserType;
import com.samadhaan4u.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by raghvendra.mishra on 01/02/18.
 */
public class UserDao extends AbstractDao{

    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

    public boolean insert(String email, String password, String emailKey){
        try{
            Connection con = createConnection();
            PreparedStatement pst = con.prepareStatement("insert into user_ " +
                    "(email,password,email_key,type) values(?, ?, ?, ?)");
            pst.setString(1, email);
            pst.setString(2, password);
            pst.setString(3, emailKey);
            pst.setInt(4, UserType.USER.id());
            int nor = pst.executeUpdate();
            logger.info(nor + " row inserted");
            con.close();
            return nor > 0;
        }catch(Exception e){

            logger.info("Exception caught");
            e.printStackTrace();
        }
        return false;
    }

    public User select(String email){

        User user = null;
        try{

            Connection con = createConnection();
            PreparedStatement pst = con.prepareStatement("select * from user_ " +
                    "where email = ?");
            pst.setString(1, email);
            ResultSet rst = pst.executeQuery();
            while(rst.next()){
                User.Builder uBuilder = new User.Builder();
                user = uBuilder.id(rst.getLong("id")).fname(rst.getString("fname"))
                        .lname(rst.getString("lname")).email(rst.getString("email"))
                        .password(rst.getString("password")).phoneNo(rst.getLong("phone_no"))
                        .emailVerified(rst.getBoolean("email_verified"))
                        .emailKey(rst.getString("email_key"))
                        .status(rst.getBoolean("status"))
                        .type(UserType.valueOf(rst.getInt("type")))
                        .creationTime(rst.getTimestamp("creation_time"))
                        .build();
            }
            con.close();
        }catch(Exception e){
            logger.info("Exception caught");
            e.printStackTrace();
        }
        return user;
    }

    public User select(long id){

        User user = null;
        try{

            Connection con = createConnection();
            PreparedStatement pst = con.prepareStatement("select * from user_ " +
                    "where id = ?");
            pst.setLong(1, id);
            ResultSet rst = pst.executeQuery();
            while(rst.next()){
                User.Builder uBuilder = new User.Builder();
                user = uBuilder.id(rst.getLong("id")).fname(rst.getString("fname"))
                        .lname(rst.getString("lname")).email(rst.getString("email"))
                        .password(rst.getString("password")).phoneNo(rst.getLong("phone_no"))
                        .emailVerified(rst.getBoolean("email_verified"))
                        .emailKey(rst.getString("email_key"))
                        .status(rst.getBoolean("status"))
                        .type(UserType.valueOf(rst.getInt("type")))
                        .creationTime(rst.getTimestamp("creation_time")).build();
            }
            con.close();
        }catch(Exception e){
            logger.info("Exception caught");
            e.printStackTrace();
        }
        return user;
    }

    public List<User> selectMultiple(){

        List<User> userList = null;
        try{
            Connection con = createConnection();
            PreparedStatement pst = con.prepareStatement("select * from user_ ");
            ResultSet rst = pst.executeQuery();
            userList = new ArrayList<>();
            while(rst.next()){
                User.Builder uBuilder = new User.Builder();
                User user = uBuilder.id(rst.getLong("id")).fname(rst.getString("fname"))
                        .lname(rst.getString("lname")).email(rst.getString("email"))
                        .password(rst.getString("password")).phoneNo(rst.getLong("phone_no"))
                        .emailVerified(rst.getBoolean("email_verified"))
                        .emailKey(rst.getString("email_key"))
                        .status(rst.getBoolean("status"))
                        .type(UserType.valueOf(rst.getInt("type")))
                        .creationTime(rst.getTimestamp("creation_time")).build();
                userList.add(user);
            }
            con.close();
        }catch(Exception e){
            logger.info("Exception caught");
            e.printStackTrace();
        }
        return userList;
    }

    public boolean updatePassword(String email, String password){
        try{
            Connection con = createConnection();
            PreparedStatement pst = con.prepareStatement("update user_ set password = ? " +
                    "where email = ?");
            pst.setString(1, password);
            pst.setString(2, email);
            int nor = pst.executeUpdate();
            logger.info(nor + " row updated");
            con.close();
            return nor > 0;
        }
        catch(Exception e){
            logger.info("Exception caught");
            e.printStackTrace();
            return false;
        }
    }

    public boolean isEmailExist(String email){
        try{
            Connection con = createConnection();
            PreparedStatement pst = con.prepareStatement("select * from user_ " +
                    "where email = ?");
            pst.setString(1, email);
            ResultSet rst = pst.executeQuery();
            while(rst.next()){
                con.close();
                return true;
            }
            return false;
        }catch(Exception e){
            logger.info("Exception caught");
            e.printStackTrace();
        }
        return false;
    }

    public boolean verifyEmail(String emailVerificationKey){
        try{

            Connection con = createConnection();
            PreparedStatement pst = con.prepareStatement("update user_ " +
                    "set email_verified = 1 where email_key = ?");
            pst.setString(1, emailVerificationKey);
            int nor = pst.executeUpdate();
            if(nor > 0)
                return true;
        }catch(Exception e){

            logger.info("Exception caught");
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateUser(User user){
        try{
            logger.info("user phone no in dao = " + user.getPhoneNo());
            Connection con = createConnection();
            PreparedStatement pst = con.prepareStatement("update user_ " +
                    "set fname = ?, lname = ?, email = ?, password = ?, phone_no = ?, " +
                    "email_verified = ?, email_key = ?, status = ? where id = ?");
            pst.setString(1, user.getFname());
            pst.setString(2, user.getLname());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getPassword());
            pst.setLong(5, user.getPhoneNo());
            pst.setBoolean(6, user.isEmailVerified());
            pst.setString(7, user.getEmailKey());
            pst.setBoolean(8, user.isStatus());
            pst.setLong(9, user.getId());
            int nor = pst.executeUpdate();
            if(nor > 0)
                return true;
        }catch(Exception e){
            logger.info("Exception caught");
            e.printStackTrace();
        }
        return false;
    }
//    public static void main(String[] args) {
//
//        User user = new User("xyz@gmail.com", "1234567890");
//        UserDao userDao = new UserDao();
//        userDao.insert(user);
//    }
}
