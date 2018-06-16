package com.samadhaan4u.model.dao;

import com.samadhaan4u.model.entity.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by raghvendra.mishra on 01/02/18.
 */
public class DocumentDao extends AbstractDao{

    private static final Logger logger = LoggerFactory.getLogger(DocumentDao.class);

    public boolean add(Document document){
        try{
            Connection con = createConnection();
            PreparedStatement pst = con.prepareStatement("insert into document_ " +
                    "(name,userId,description) values(?, ?, ?)");
            pst.setString(1, document.getName());
            pst.setLong(2, document.getUserId());
            pst.setTimestamp(3, document.getUploadDate());
            pst.setString(4, document.getDescription());
            if(pst.executeUpdate() == 1){
                logger.info("New document {} inserted successfully.", document.getName());
                return true;
            }
        }catch(Exception e){
            logger.info("Exception occurred while inserting document {} for user id = {}.", document.getName(),
                    document.getUserId());
            e.printStackTrace();
        }
        return false;
    }

    public Optional<List<Document>> getByUserId(long userId){
        List<Document> documentList = new ArrayList<>();
        try{
            logger.info("Fetching documents for user id = {}.", userId);
            Connection con = createConnection();
            PreparedStatement pst = con.prepareStatement("select * from document_ " +
                    " where userId = ?");
            pst.setLong(1, userId);
            ResultSet rst = pst.executeQuery();
            while(rst.next()){
                Document.Builder dBuilder = new Document.Builder();
                Document document = dBuilder.id(rst.getLong("id"))
                        .name(rst.getString("name"))
                        .userId(rst.getLong("userId"))
                        .uploadDate(rst.getTimestamp("uploadDate"))
                        .description(rst.getString("description"))
                        .build();
                documentList.add(document);
            }
            con.close();
            logger.info("All documents for userId = {} fetched successfully.", userId);
        }catch(Exception e){
            logger.info("Exception occurred while fetching documents for user id = {}.", userId);
            e.printStackTrace();
        }
        return Optional.of(documentList);
    }

    public Optional<Document> get(long id){
        logger.info("Fetching document for document id = {}.", id);
        Document document = null;
        try{
            Connection con = createConnection();
            PreparedStatement pst = con.prepareStatement("select * from document_ " +
                    "where id = ?");
            pst.setLong(1, id);
            ResultSet rst = pst.executeQuery();
            while(rst.next()){
                Document.Builder dBuilder = new Document.Builder();
                document = dBuilder.id(rst.getLong("id"))
                        .name(rst.getString("name"))
                        .userId(rst.getLong("userId"))
                        .uploadDate(rst.getTimestamp("uploadDate"))
                        .description(rst.getString("description"))
                        .build();
            }
            con.close();
            logger.info("Document for id = {} fetched successfully.", id);
        }catch(Exception e){
            logger.info("Exception occurred while fetching document for document id = {}.", id);
            e.printStackTrace();
        }
        return Optional.of(document);
    }

    public boolean delete(long documentId){
        try{
            Connection con = createConnection();
            PreparedStatement pst = con.prepareStatement("update document_ set status = ? " +
                    "where email = ?");
            pst.setString(1, "");
//            pst.setString(2, email);
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


}
