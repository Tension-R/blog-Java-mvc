package com.tension.dao.Impl;

import com.tension.dao.UserArticleDao;
import com.tension.entity.Article;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

/**
 * Created by tension on 17-6-10.
 */
public class UserArticleDaoImpl implements UserArticleDao{

    private String sql = null;
    private PreparedStatement pstmt = null;
    private Statement stmt = null;


    @Override
    public int insertArticleAndUser(String username, int articleId) {
        return 0;
    }
}
