package com.samadhaan4u.model.dao;

import com.samadhaan4u.dto.constant.UserType;
import com.samadhaan4u.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by raghvendra.mishra on 01/02/18.
 */
public class UserDao extends AbstractDao{

    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

    public boolean add(User user){
        try{
            if(user == null){
                logger.info("user is null...returning false");
                return false;
            }
            logger.info("inserting new user {} in user_ table" , user.getEmail());
            Connection con = createConnection();
            PreparedStatement pst = con.prepareStatement("insert into user_ " +
                    "(email,password,email_key,type) values(?, ?, ?, ?)");
            pst.setString(1, user.getEmail());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getEmailKey());
            pst.setInt(4, user.getType().id());
            boolean userInserted = pst.execute();
            con.close();
            if(userInserted){
                logger.info(" user {} inserted successfully", user.getEmail());
            }else{
                logger.info(" user {} could not be inserted", user.getEmail());
            }
            return userInserted;
        }catch(Exception e){
            logger.info("exception occurred while inserting user {}", user.getEmail());
            e.printStackTrace();
        }
        return false;
    }

    public Optional<User> get(String email){
        if(email == null){
            logger.info("email is null...returning null");
            return null;
        }
        logger.info("fetching user {} from user_ table" , email);
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
            if(user != null){
                logger.info(" user {} fetched successfully", email);
            }else{
                logger.info(" user {} could not be fetched", email);
            }
        }catch(Exception e){
            logger.info("exception occurred while fetching user {}", user.getEmail());
            e.printStackTrace();
        }
        return Optional.of(user);
    }

    public Optional<User> get(long id){
        if(id <= 0){
            logger.info("wrong user id = {}, returning null", id);
            return null;
        }
        logger.info("fetching user id {} from user_ table" , id);
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
                        .creationTime(rst.getTimestamp("creation_time"))
                        .build();
            }
            con.close();
            if(user != null){
                logger.info(" user id {} fetched successfully", id);
            }else{
                logger.info(" user id {} could not be fetched", id);
            }
        }catch(Exception e){
            logger.info("exception occurred while inserting user id {}", id);
            e.printStackTrace();
        }
        return Optional.of(user);
    }

    public Optional<List<User>> get(){
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
        return Optional.of(userList);
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
            if(pst.executeUpdate() == 1)
                return true;
        }catch(Exception e){

            logger.info("Exception caught");
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(User user){
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
