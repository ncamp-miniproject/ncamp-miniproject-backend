package com.model2.mvc.common.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Arrays;

public class DBUtil {

    private final static String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private final static String JDBC_URL = "jdbc:oracle:thin:scott/tiger@localhost:1521:xe";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(JDBC_URL);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        close(rs);
        close(stmt);
        close(conn);
    }
    
    private static void close(AutoCloseable closeable) {
        try {
            closeable.close();
        } catch (NullPointerException e) {
            System.out.println("Result is null");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Date parseIntoSqlDate(String dateInStr) {
        if (dateInStr == null || dateInStr.isEmpty()) {
            return new Date(System.currentTimeMillis());
        }
        int[] parsed = Arrays.stream(dateInStr.split("-")).mapToInt(Integer::parseInt).toArray();
        LocalDate inLocalDate = LocalDate.of(parsed[0], parsed[1], parsed[2]);
        return Date.valueOf(inLocalDate);
    }
}