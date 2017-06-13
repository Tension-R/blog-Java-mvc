package com.tension.web;

import com.tension.dao.ArticleDao;
import com.tension.dao.Impl.ArticleDaoImpl;
import com.tension.entity.Article;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by tension on 17-6-12.
 */
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8");

        ArticleDao articleDao = new ArticleDaoImpl();
        //访问home页面时带的参数
        String action = req.getParameter("action");
        //判断是否有action参数，没有则是第一次访问
        if (action != null) {
            //带有action参数，执行参数对应的if语句
            if (action.trim().equals("login")) {
                //转发到登录界面
                RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/jsps/login.jsp");
                view.forward(req, resp);
            } else if (action.trim().equals("query")) {
                //根据标题和作者调用查询方法
                String title = req.getParameter("title");
                String username = req.getParameter("author");
                //TODO
            } else if (action.trim().equals("detail")) {
                //进入博文详情页面
                //获取博文id
                String articleId = req.getParameter("articleId");
                //获取博文
                Article article = articleDao.queryArticleById(Integer.parseInt(articleId));
                //设置请求属性article，在details页面获取
                req.setAttribute("article", article);
                //转发到details页面
                RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/jsps/details.jsp");
                view.forward(req, resp);
            }
        } else {
            //第一次访问home页面
            //获取所有博文列表
            List<Article> articleList = articleDao.queryAll();
            //设置请求属性list，在home页面获取
            req.setAttribute("list", articleList);
            //转发到home页面
            RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/jsps/home.jsp");
            view.forward(req, resp);
        }
    }

}
