package com.theredeemed.myactivityexpensetrackerservice.model.sql;

/**
 * Utility class that contains queries related to the Action entity
 * The use of the private constructor prevents an instance of the class to be created
 */
public class ActionSQL {

    private ActionSQL() {
    }

    public static String INSERT_ACTION_SQL = "INSERT INTO ACTIONS " +
            "(ACTION_CODE, DESCRIPTION, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE) " +
            "VALUES(?, ?, ?, ?, ?, ?)";

    public static String FIND_ACTION_BY_ID_SQL = "SELECT ID, ACTION_CODE, DESCRIPTION, " +
            "CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE " +
            "FROM ACTIONS " +
            "WHERE ID = ? ";

    public static String FIND_ALL_ACTIONS_SQL = "SELECT ID, ACTION_CODE, DESCRIPTION, " +
            "CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE " +
            "FROM ACTIONS ";

    public static String UPDATE_ACTION_SQL = "UPDATE ACTIONS " +
            "SET DESCRIPTION = ?, UPDATED_BY = ?, UPDATED_DATE = ? " +
            "WHERE ID = ? ";

    public static String DELETE_ACTION_SQL = "DELETE FROM ACTIONS WHERE ID = ? ";
}
