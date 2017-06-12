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

    public int insertUser(User user) throws SQLException {

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
            //TODO
        }
        return num;
    }

    public int deleteUser(String username) throws SQLException {

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

    public int updateUserTelephone(String username, long telephone) throws SQLException {

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

    public int updateUserSex(String username, int sex) throws SQLException {
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

    public List<User> queryByName(String username) throws SQLException {

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
}
