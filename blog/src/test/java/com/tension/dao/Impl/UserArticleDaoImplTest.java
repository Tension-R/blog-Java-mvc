package com.tension.dao.Impl;

import com.tension.dao.UserArticleDao;
import junit.framework.TestCase;

/**
 * Created by tension on 17-6-11.
 */
public class UserArticleDaoImplTest extends TestCase {

    private UserArticleDao userArticleDao = new UserArticleDaoImpl();

    public void testInsertArticleAndUser() throws Exception {
        String username = "zhang";
        int articleId = 11;
        int x = userArticleDao.insertArticleAndUser(username,articleId);
        System.out.println(x);
    }

}