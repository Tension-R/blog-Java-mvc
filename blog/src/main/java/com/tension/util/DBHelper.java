package com.tension.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by tension on 17-6-9.
 */
public class DBHelper {
    //数据库信息
    private final String driver = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://127.0.0.1/blog?useUnicode=true&characterEncoding=utf8";
    private final String username = "root";
    private final String password = "root";
    //数据库连接
    private Connection conn = null;

    /**
     * 获取数据库连接
     *
     * @return
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (conn == null) {
            conn = DriverManager.getConnection(url, username, password);
            return conn;
        }
        return conn;
    }

//    /**
//     * 测试数据库连接
//     * @param args
//     * @throws SQLException
//     */
//    public static void main(String[] args) throws SQLException {
//        Connection conn = getConnection();
//        if (conn != null){
//            System.out.println(true);
//        }else {
//            System.out.println(false);
//        }
//    }
}
