package com.samadhaan4u.model.dao;

import com.samadhaan4u.model.entity.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by raghvendra.mishra on 01/02/18.
 */
public class DocumentDao extends AbstractDao{

    private static final Logger logger = LoggerFactory.getLogger(DocumentDao.class);

    public boolean insert(Document document){
        try{
            Connection con = createConnection();
            PreparedStatement pst = con.prepareStatement("insert into document_ " +
                    "(name,userId,uploadDate,description,path) values(?, ?, ?, ?, ?)");
            pst.setString(1, document.getName());
            pst.setLong(2, document.getUserId());
            pst.setTimestamp(3, document.getUploadDate());
            pst.setString(4, document.getDescription());
            pst.setString(5, document.getPath());
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

    public List<Document> select(long userId){

        List<Document> documentList = new ArrayList<>();
        try{

            Connection con = createConnection();
            PreparedStatement pst = con.prepareStatement("select * from document_ " +
                    " where userId = ?");
            pst.setLong(1, userId);
            ResultSet rst = pst.executeQuery();
            while(rst.next()){
                Document.Builder dBuilder = new Document.Builder();
                Document document = dBuilder.id(rst.getLong("id")).name(rst.getString("name"))
                        .userId(rst.getLong("userId")).uploadDate(rst.getTimestamp("uploaddate"))
                        .description(rst.getString("description")).path(rst.getString("path"))
                        .build();
                documentList.add(document);
            }
            con.close();
        }catch(Exception e){

            logger.info("Exception caught");
            e.printStackTrace();
        }
        return documentList;
    }

    public static void main(String[] args) {
        DocumentDao dao = new DocumentDao();
        List<Document> dlist = dao.select(0);

        System.out.println(dlist.size());
    }
}
