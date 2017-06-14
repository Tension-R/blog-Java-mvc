package com.tension.web;

import com.tension.dao.ArticleDao;
import com.tension.dao.Impl.ArticleDaoImpl;
import com.tension.entity.Article;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by tension on 17-6-14.
 */
public class ArticleServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        ArticleDao articleDao = new ArticleDaoImpl();

        String action = req.getParameter("action");

        //TODO
    }
}
