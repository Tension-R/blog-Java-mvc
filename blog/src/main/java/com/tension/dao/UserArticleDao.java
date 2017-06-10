package com.tension.dao;

import com.tension.entity.Article;

import java.util.List;

/**
 * 作者博文对应类
 * Created by tension on 17-6-10.
 */
public interface UserArticleDao {



    /**
     * 添加作者博文对应数据
     * @param username
     * @param articleId
     * @return
     */
    int insertArticleAndUser(String username, int articleId);
}
