package com.tension.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by tension on 17-6-9.
 */
public class DBHelper {
    //数据库信息
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://127.0.0.1/blog?useUnicode=true&characterEncoding=utf8";
    private static final String usename = "root";
    private static final String password = "root";
    //数据库连接
    private static Connection conn = null;
    //加载驱动
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        if (conn == null){
            conn = DriverManager.getConnection(url,usename,password);
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
