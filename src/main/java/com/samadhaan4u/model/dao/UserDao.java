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

    public boolean insert(User user){

        if(user.getEmail() == null || user.getPassword() == null){

            logger.info("Either email or password is null.");
            logger.info("User cannot be added. Returning false.");
            return false;
        }

        try{

            Connection con = createConnection();
            PreparedStatement pst = con.prepareStatement("insert into user_" +
                    "(email,password) values(?, ?)");
            pst.setString(1, user.getEmail());
            pst.setString(2, user.getPassword());
            int nor = pst.executeUpdate();
            logger.info(nor + " row inserted in table user_.");
            con.close();
            return nor > 0;
        }
        catch(Exception e){

            logger.info("Exception caught while adding user to table user_.");
            e.printStackTrace();
        }
        return false;
    }

    public User select(String email, String password){

        if(email == null || password == null){

            logger.info("Either email or password is null.");
            return null;
        }

        User user = null;
        try{

            Connection con = createConnection();
            PreparedStatement pst = con.prepareStatement("select * from user_ " +
                    "where email = ? and password = ?");
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet rst = pst.executeQuery();
            while(rst.next()){

                User.Builder builder = new User.Builder();
                user = builder.id(rst.getLong("id")).fname(rst.getString("fname"))
                        .lname(rst.getString("lname")).email(rst.getString("email"))
                        .password(rst.getString("password")).phoneNo(rst.getLong("phoneNo")).build();
            }
            con.close();
        }
        catch(Exception e){

            logger.info("Exception caught getting user from db.");
            e.printStackTrace();
        }
        return user;
    }

    public boolean update(User user){

        if(user == null){

            logger.info("User found null.");
            return false;
        }

        try{

            Connection con = createConnection();
            boolean flag = false;
            StringBuffer query = new StringBuffer("update user_ set ");

            if(user.getFname() != null){
                query.append(" fname = ? ");
                flag = true;
            }

            if(user.getLname() != null){
                if(flag) query.append(", ");
                else flag = true;
                query.append(" lname = ? ");
            }

            if(user.getEmail() != null){
                if(flag) query.append(", ");
                else flag = true;
                query.append(" email = ? ");
            }

            if(user.getPassword() != null){
                if(flag) query.append(", ");
                else flag = true;
                query.append(" password = ? ");
            }

            if(user.getPhoneNo() != 0){
                if(flag) query.append(", ");
                query.append(" phoneNo = ? ");
            }
            PreparedStatement pst = con.prepareStatement(query.toString());

            int index = 1;
            if(user.getFname() != null)
                pst.setString(index++, user.getFname());
            if(user.getLname() != null)
                pst.setString(index++, user.getLname());
            if(user.getEmail() != null)
                pst.setString(index++, user.getEmail());
            if(user.getPassword() != null)
                pst.setString(index++, user.getPassword());
            if(user.getPhoneNo() != 0)
                pst.setLong(index++, user.getPhoneNo());

            int nor = pst.executeUpdate();
            logger.info(nor + " row updated in table user_.");
            con.close();
            return nor > 0;
        }
        catch(Exception e){

            logger.info("Exception caught while updating user to table user_.");
            e.printStackTrace();
        }
        return false;
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

    public boolean updateStatus(String email, boolean status){

        try{

            Connection con = createConnection();
            PreparedStatement pst = con.prepareStatement("update user_ " +
                    "set status = ? where email = ?");
            pst.setBoolean(1, status);
            pst.setString(2, email);
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
