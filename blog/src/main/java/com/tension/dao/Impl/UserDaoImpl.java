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

    private PreparedStatement pstmt = null;
    private Statement stmt = null;
    private String sql = null;
    private int num = 0;

    public int insertUser(String username, String password, int sex, long telephone) {

        String sql = "INSERT INTO user (`username`,`password`,`sex`,`telephone`) VALUES(?,?,?,?);";
        //影响的行数
        int num = 0;

        //try-with-source自动关闭连接对象
        try (Connection conn = DBHelper.getConnection()) {

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setInt(3, sex);
            pstmt.setLong(4, telephone);

            num = pstmt.executeUpdate();

        } catch (SQLException e) {
            //TODO
        }
        return num;
    }

    public int deleteUser(String username) {

        String sql = "delete from user where username = '" + username + "';";

        try (Connection conn = DBHelper.getConnection()) {

            stmt = conn.createStatement();
            num = stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    public int updateUserTelephone(String username, long telephone) {

        sql = "UPDATE user SET telephone = ? WHERE username = ?;";

        try (Connection conn = DBHelper.getConnection()) {

            pstmt = conn.prepareStatement(sql);

            pstmt.setLong(1, telephone);
            pstmt.setString(2, username);

            num = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    public int updateUserSex(String username, int sex) {
        sql = "update user set sex = ? where username = ?;";
        try(Connection conn = DBHelper.getConnection()){

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,sex);
            pstmt.setString(2,username);
            num = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    public List<User> queryByName(String username) {

        sql = "SELECT * FROM user WHERE username LIKE '%" + username + "%'";

        List<User> userList = new ArrayList<>();

        try (Connection conn = DBHelper.getConnection()) {

            stmt = conn.createStatement();
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
