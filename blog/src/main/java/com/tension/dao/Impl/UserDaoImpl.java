package com.tension.dao.Impl;

import com.tension.dao.UserDao;
import com.tension.entity.User;
import com.tension.util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tension on 17-6-10.
 */
public class UserDaoImpl implements UserDao {

    public int insertUser(User user)   {

        String sql = "INSERT INTO user (`username`,`password`,`sex`,`telephone`) VALUES(?,?,?,?);";
        //影响的行数
        int num = 0;
        DBHelper dbHelper = new DBHelper();

        try (Connection conn = dbHelper.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setInt(3, user.getSex());
            pstmt.setLong(4, user.getTelephone());

            num = pstmt.executeUpdate();

        } catch (SQLException e) {
            //用户名重复异常，在业务层中处理
        }
        return num;
    }

    public int deleteUser(String username)   {

        String sql = "delete from user where username = '" + username + "';";
        DBHelper dbHelper = new DBHelper();
        int num = 0;
        try (Connection conn = dbHelper.getConnection()) {
            Statement stmt = conn.createStatement();
            num = stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    public int updateUserTelephone(String username, long telephone)   {

        String sql = "UPDATE user SET telephone = ? WHERE username = ?;";
        DBHelper dbHelper = new DBHelper();
        int num = 0;
        try (Connection conn = dbHelper.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setLong(1, telephone);
            pstmt.setString(2, username);

            num = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    public int updateUserSex(String username, int sex)   {
        String sql = "update user set sex = ? where username = ?;";
        DBHelper dbHelper = new DBHelper();
        int num = 0;
        try (Connection conn = dbHelper.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, sex);
            pstmt.setString(2, username);
            num = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    public List<User> queryByName(String username)   {

        String sql = "SELECT * FROM user WHERE username LIKE '%" + username + "%'";

        List<User> userList = new ArrayList<>();
        DBHelper dbHelper = new DBHelper();
        try (Connection conn = dbHelper.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setSex(rs.getInt("sex"));
                user.setTelephone(rs.getLong("telephone"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User checkUser(String username, String password) {
        String sql = "select * from user where username = ? and password = ?;";
        DBHelper dbHelper = new DBHelper();
        User user = new User();
        try (Connection conn = dbHelper.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setSex(rs.getInt("sex"));
                user.setTelephone(rs.getLong("telephone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int updateUserUsername(String oldUsername, String username) {
        String sql = "update user set username = ? where username = ?;";
        DBHelper dbHelper = new DBHelper();
        int num = 0;
        try (Connection conn = dbHelper.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2,oldUsername);
            num = pstmt.executeUpdate();
        } catch (SQLException e) {
            //用户名重复在业务中处理
        }
        return num;
    }
}
