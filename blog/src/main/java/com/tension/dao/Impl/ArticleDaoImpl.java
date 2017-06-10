package com.tension.dao.Impl;

import com.tension.dao.ArticleDao;
import com.tension.entity.Article;
import com.tension.util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tension on 17-6-10.
 */
public class ArticleDaoImpl implements ArticleDao {

    private PreparedStatement pstmt = null;
    private Statement stmt = null;
    private String sql = null;
    private int num = 0;


    @Override
    public int insertArticle(String title, String content, Date date, String username) {

        sql = "insert into article (`title`,`content`,`date`,`author_username`) value (?,?,?,?);";
        try (Connection conn = DBHelper.getConnection()){

            //更新博文表
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,title);
            pstmt.setString(2,content);
            pstmt.setDate(3,date);
            pstmt.setString(4,username);
            num = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return num;
    }

    @Override
    public int deleteArticle(int id) {
        sql = "delete from article where id = " + id + ";";
        try (Connection conn = DBHelper.getConnection()){

            stmt = conn.createStatement();
            num = stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public int updateArticle(int id, String title, String content, Date date) {
        sql = "update article set title = ?, content = ?, date = ? WHERE id = ?;";
        try (Connection conn = DBHelper.getConnection()){

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,title);
            pstmt.setString(2,content);
            pstmt.setDate(3,date);
            pstmt.setInt(4,id);

            num = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public List<Article> queryByTitle(String title) {

        sql = "select * from article where title like '%" + title + "%';";

        List<Article> articleList = new ArrayList<>();

        try (Connection conn = DBHelper.getConnection()){

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                Article article = new Article();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
                article.setDate(rs.getDate("date"));
                article.setUsername(rs.getString("author_username"));
                articleList.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articleList;
    }

    @Override
    public List<Article> queryAllArticleByUsername(String username) {

        sql = "select * from article where author_username = " + username + ";";

        try (Connection conn = DBHelper.getConnection()){
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
