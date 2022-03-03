package com.theredeemed.myactivityexpensetrackerservice.model.sql;

public class RoleSQL {
    private RoleSQL() {}

    public static String INSERT_ROLE_SQL = "INSERT INTO ROLES " +
            "(ROLE_NAME, DESCRIPTION, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

    public static String FIND_ALL_ROLES_SQL = "SELECT ID, ROLE_NAME, DESCRIPTION, " +
            "CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE " +
            "FROM ROLES";

    public static String FIND_ROLE_BY_ID_SQL = "SELECT ID, ROLE_NAME, DESCRIPTION, " +
            "CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE " +
            "FROM ROLES " +
            "WHERE ID = ?";

    public static String UPDATE_ROLE_SQL = "UPDATE ROLES " +
            "SET DESCRIPTION = ?, UPDATED_BY = ?, UPDATED_DATE = ? " +
            "WHERE ID = ?";

    public static String DELETE_ROLE_SQL = "DELETE FROM ROLES WHERE ID = ?";
}
