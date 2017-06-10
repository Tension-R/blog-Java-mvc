package com.tension.dao.Impl;

import com.tension.dao.ArticleDao;
import com.tension.entity.Article;
import junit.framework.TestCase;

import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;

/**
 * Created by tension on 17-6-10.
 */
public class ArticleDaoImplTest extends TestCase {

    private ArticleDao articleDao = new ArticleDaoImpl();

    public void testInsertArticle() throws Exception {
        String title = "lpl夏季赛";
        String content = "哪三只战队能代表lpl出征S7？";
        Date date = new Date();
        String username = "zhang";
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        int num = articleDao.insertArticle(title,content,sqlDate, username);
        System.out.println(num);
    }

    public void testDeleteArticle() throws Exception {
        int id = 7;
        int x = articleDao.deleteArticle(id);
        System.out.println(x);
    }

    public void testUpdateArticle() throws Exception {
        String title = "世界杯";
        String content = "中国队能否打进世界杯？";
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        int id = 2;
        int x = articleDao.updateArticle(id,title,content,sqlDate);
        System.out.println(x);
    }

    public void testQueryByTitle() throws Exception {

        String name = "java";
        List<Article> articleList = articleDao.queryByTitle(name);
        System.out.println(articleList);
    }

}