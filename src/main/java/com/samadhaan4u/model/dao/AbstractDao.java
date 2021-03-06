package com.samadhaan4u.model.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by raghvendra.mishra on 26/02/18.
 */
public abstract class AbstractDao implements Dao{

    private static final Logger logger = LoggerFactory.getLogger(AbstractDao.class);

    @Override
    public Connection createConnection(){
        Connection con = null;
        try{

            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/samadhaanDB",
                    "root","root");
        }catch(Exception e)
        {
            logger.info("Exception ");
            e.printStackTrace();
        }
        return con;
    }
}
