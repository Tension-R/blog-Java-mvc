package com.tension.dao;

import com.tension.entity.Article;
import com.tension.entity.User;

import java.sql.Date;
import java.util.List;

/**
 * Created by tension on 17-6-10.
 */
public interface ArticleDao {

    /**
     * 添加博文
     *
     * @param title
     * @param content
     * @param date
     * @return
     */
    int insertArticle(String title, String content, Date date, String username);

    /**
     * 删除博文
     *
     * @param id
     * @return
     */
    int deleteArticle(int id);

    /**
     * 修改博文
     *
     * @param id
     * @param title
     * @return
     */
    int updateArticle(int id, String title, String content, Date date);

    /**
     * 根据标题模糊查询博文
     *
     * @param title
     * @return
     */
    List<Article> queryByTitle(String title);

    /**
     * 按用户名查询对应作者的所有博文
     * @param username
     * @return
     */
    List<Article> queryAllArticleByUsername(String username);
}
