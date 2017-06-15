package com.tension.web;

import com.tension.dao.ArticleDao;
import com.tension.dao.Impl.ArticleDaoImpl;
import com.tension.entity.Article;
import com.tension.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

/**
 * 博文操作
 * Created by tension on 17-6-14.
 */
public class ArticleServlet extends HttpServlet {
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

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        //判断是否登录
        if (user != null) {
            //已登录
            if (action.equals("add")) {
                //新建博文，转发到新建博文页面addBlog.jsp
                req.getRequestDispatcher("/WEB-INF/jsps/addBlog.jsp").forward(req, resp);
            } else if (action.equals("insert")) {
                //新建博文
                String title = req.getParameter("title");
                String content = req.getParameter("content");
                java.util.Date date = new java.util.Date();
                Date sqlDate = new Date(date.getTime());
                String username = user.getUsername();
                //执行数据库新建操作
                int x = articleDao.insertArticle(title, content, sqlDate, username);
                if (x > 0) {
                    //执行成功
                    //根据用户名获取所有博文
                    List<Article> myArticles = articleDao.queryByUsername(username);
                    session.setAttribute("myArticles", myArticles);
                    //重定向到个人信息页面
                    resp.sendRedirect("/user");
                }
            } else if (action.equals("change")) {
                //编辑博文
                String articleId = req.getParameter("articleId");
                Article article = articleDao.queryArticleById(Integer.parseInt(articleId));
                req.setAttribute("article", article);
                //转发到博文编辑页面
                req.getRequestDispatcher("/WEB-INF/jsps/changeBlog.jsp").forward(req, resp);
            } else if (action.equals("delete")) {
                //删除博文
                //获取要删除的博文id
                String articleId = req.getParameter("articleId");
                //执行数据库删除操作
                int x = articleDao.deleteArticle(Integer.parseInt(articleId));
                if (x > 0){
                    //执行成功
                    //重定向到个人信息页面
                    resp.sendRedirect("/user");
                }

            } else if (action.equals("update")) {
                //编辑博文
                //获得博文id
                String articleId = req.getParameter("articleId");
                //通过博文id获取博文
                Article article = articleDao.queryArticleById(Integer.parseInt(articleId));
                //获取属性
                String oldTitle = article.getTitle();
                String oldContent = article.getContent();
                java.util.Date date = new java.util.Date();
                Date sqlDate = new Date(date.getTime());
                //获取参数
                String title = req.getParameter("title");
                String content = req.getParameter("content");
                //如果新标题是空字符串，新标题等于旧标题
                if (title.trim().equals("")) {
                    title = oldTitle;
                }
                //如果新内容是空字符串，新内容等于旧内容
                if (content.trim().equals("")) {
                    content = oldContent;
                }
                //执行数据库编辑操作
                int x = articleDao.updateArticle(Integer.parseInt(articleId), title, content, sqlDate);

                if (x > 0){
                    //操作成功
                    //重定向到博文详情页面
                    resp.sendRedirect("/home?action=detail&articleId=" + Integer.parseInt(articleId));
                }
            }
        } else {
            //未登录
            resp.sendRedirect("/home?action=login");
        }
    }
}
