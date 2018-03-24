package com.samadhaan4u.model.dao;

import com.samadhaan4u.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * Created by raghvendra.mishra on 01/02/18.
 */
public class UserDao extends AbstractDao{

    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

    public boolean insert(String email, String password, String emailKey){
        try{
            Connection con = createConnection();
            PreparedStatement pst = con.prepareStatement("insert into user_ " +
                    "(email,password,emailKey) values(?, ?, ?)");
            pst.setString(1, email);
            pst.setString(2, password);
            pst.setString(3, emailKey);
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
                        .password(rst.getString("password")).phoneNo(rst.getLong("phoneNo"))
                        .emailVerified(rst.getBoolean("emailVerified"))
                        .emailKey(rst.getString("emailKey"))
                        .status(rst.getBoolean("status")).build();
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
                        .password(rst.getString("password")).phoneNo(rst.getLong("phoneNo"))
                        .emailVerified(rst.getBoolean("emailVerified"))
                        .emailKey(rst.getString("emailKey"))
                        .status(rst.getBoolean("status")).build();
            }
            con.close();
        }catch(Exception e){
            logger.info("Exception caught");
            e.printStackTrace();
        }
        return user;
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
                    "set emailVerified = 1 where emailKey = ?");
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
            Connection con = createConnection();
            PreparedStatement pst = con.prepareStatement("update user_ " +
                    "set fname = ?, lname = ?, email = ?, password = ?, phoneNo = ?, " +
                    "email_verified = ?, emailKey = ?, status = ? where id = ?");
            pst.setString(1, user.getFname());
            pst.setString(2, user.getLname());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getPassword());
            pst.setLong(5, user.getPhoneNo());
            pst.setBoolean(6, user.isEmailVerified());
            pst.setString(7, user.getEmailKey());
            pst.setBoolean(8, user.isStatus());
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
