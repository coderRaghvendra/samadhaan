package com.samadhaan4u.model.dao;

/**
 * Created by raghvendra.mishra on 26/02/18.
 */
public abstract class DaoManager {

    private static final UserDao USER_DAO = new UserDao();
    private static final DocumentDao DOCUMENT_DAO = new DocumentDao();

    public static UserDao userDao() {
        return USER_DAO;
    }

    public static DocumentDao documentDao() {
        return DOCUMENT_DAO;
    }
}
