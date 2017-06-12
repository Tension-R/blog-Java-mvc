package com.tension.dao.Impl;

import com.tension.dao.UserArticleDao;
import com.tension.util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by tension on 17-6-10.
 */
public class UserArticleDaoImpl implements UserArticleDao {


    @Override
    public int insertArticleAndUser(String username, int articleId) throws SQLException {

        String sql = "INSERT INTO user_article (`user_username`, `article_id`) VALUE (?,?);";
        DBHelper dbHelper = new DBHelper();
        int num = 0;
        try (Connection conn = dbHelper.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setInt(2, articleId);
            num = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }
}
