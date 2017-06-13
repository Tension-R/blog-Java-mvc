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


    @Override
    public int insertArticle(String title, String content, Date date, String username) {

        String sql = "INSERT INTO article (`title`,`content`,`date`,`author_username`) VALUE (?,?,?,?);";
        int num = 0;
        DBHelper dbHelper = new DBHelper();
        try (Connection conn = dbHelper.getConnection()) {//更新博文表
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, content);
            pstmt.setDate(3, date);
            pstmt.setString(4, username);
            num = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return num;
    }

    @Override
    public int deleteArticle(int id) {
        String sql = "delete from article where id = " + id + ";";
        int num = 0;
        DBHelper dbHelper = new DBHelper();
        try (Connection conn = dbHelper.getConnection()) {
            Statement stmt = conn.createStatement();
            num = stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public int updateArticle(int id, String title, String content, Date date) {
        String sql = "UPDATE article SET title = ?, content = ?, date = ? WHERE id = ?;";
        DBHelper dbHelper = new DBHelper();
        int num = 0;
        try (Connection conn = dbHelper.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, content);
            pstmt.setDate(3, date);
            pstmt.setInt(4, id);

            num = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public List<Article> queryByTitle(String title) {

        String sql = "select * from article where title like '%" + title + "%';";
        return getArticleList(sql);
    }

    @Override
    public List<Article> queryByUsername(String username) {

        String sql = "select * from article where author_username = '" + username + "';";
        return getArticleList(sql);

    }

    /**
     * 抽取获得博文对象列表的方法
     *
     * @return
     */
    private List<Article> getArticleList(String sql) {
        List<Article> articleList = new ArrayList<>();
        DBHelper dbHelper = new DBHelper();
        try (Connection conn = dbHelper.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
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
    public List<Article> queryAll() {
        String sql = "select * from article;";
        return getArticleList(sql);
    }

    @Override
    public Article queryArticleById(int articleId) {
        String sql = "select * from article where id = '" + articleId + "'";
        DBHelper dbHelper = new DBHelper();
        Article article = new Article();
        try (Connection conn = dbHelper.getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
                article.setDate(rs.getDate("date"));
                article.setUsername(rs.getString("author_username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    @Override
    public int updateArticleAuthor(String oldUsername, String username) {
        String sql = "update article set author_username = ? where author_username = ?;";
        DBHelper dbHelper = new DBHelper();
        int x = 0;
        try (Connection conn = dbHelper.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(2,oldUsername);
            pstmt.setString(1,username);
            x = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x;
    }
}
