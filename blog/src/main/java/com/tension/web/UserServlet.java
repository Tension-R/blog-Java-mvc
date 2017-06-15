package com.tension.web;

import com.tension.dao.ArticleDao;
import com.tension.dao.Impl.ArticleDaoImpl;
import com.tension.dao.Impl.UserDaoImpl;
import com.tension.dao.UserDao;
import com.tension.entity.Article;
import com.tension.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.interfaces.RSAKey;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tension on 17-6-12.
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");


        UserDao userDao = new UserDaoImpl();
        ArticleDao articleDao = new ArticleDaoImpl();
        //访问user时带的参数action
        String action = req.getParameter("action");

        HttpSession session = req.getSession();
        //获取当前会话的user属性
        User user = (User) session.getAttribute("user");
        //判断是否带有action参数
        if (action != null) {
            //带有action参数，执行对应的if语句
            if (action.trim().equals("logOn")) {
                //登录
                //如果当前会话存在user属性，转发到个人信息页面user.jsp
                if (user != null) {
                    //根据用户名获得博文
                    List<Article> myArticles = articleDao.queryByUsername(user.getUsername());
                    //会话范围设置myArticles属性
                    session.setAttribute("myArticles", myArticles);
                    req.getRequestDispatcher("WEB-INF/jsps/user.jsp").forward(req, resp);
                } else {
                    //当前会话不存在user属性，获取登录页面的参数
                    String username = req.getParameter("username");
                    String password = req.getParameter("password");
                    //检验数据库中是否存在对应的用户名密码
                    user = userDao.checkUser(username, password);
                    //检验是否获取到了user对象
                    if (user.getUsername() == null) {
                        //没获取到
                        //设置标识，前端提示用户名密码错误
                        req.setAttribute("log", false);
                        //转发回登录界面
                        req.getRequestDispatcher("/WEB-INF/jsps/login.jsp").forward(req, resp);
                    } else {
                        //获取到了user对象
                        //会话作用域内设置user属性
                        session.setAttribute("user", user);
                        //根据用户名获得博文
                        List<Article> myArticles = articleDao.queryByUsername(user.getUsername());
                        //会话范围设置myArticles属性
                        session.setAttribute("myArticles", myArticles);
                        //转发到用户个人信息页面user.jsp
                        req.getRequestDispatcher("/WEB-INF/jsps/user.jsp").forward(req, resp);
                    }
                }
            } else if (action.trim().equals("register")) {
                //注册
                //转发到注册界面
                req.getRequestDispatcher("/WEB-INF/jsps/register.jsp").forward(req, resp);
            } else if (action.trim().equals("quit")) {
                //退出
                //会话销毁
                session.invalidate();
                //重定向到主页
                resp.sendRedirect("/home");
            } else if (action.trim().equals("add")) {
                //注册添加用户
                //获取参数
                String username = req.getParameter("username");
                String password = req.getParameter("password");
                String sex = req.getParameter("sex");
                String telephone = req.getParameter("telephone");
                User newUser = new User(username, password, Integer.parseInt(sex), Long.parseLong(telephone));
                //执行数据库添加操作，返回影响的行数
                int x = userDao.insertUser(newUser);
                if (x > 0) {
                    //添加成功
                    session.setAttribute("user", newUser);
                    //转发到用户个人信息页面user.jsp
//                    req.getRequestDispatcher("/WEB-INF/jsps/user.jsp").forward(req, resp);
                    resp.sendRedirect("/user");
                } else {
                    //添加失败
                    //用户名已存在，抛出异常
                    req.setAttribute("repeat", true);
                    //转发到注册界面
                    req.getRequestDispatcher("/WEB-INF/jsps/register.jsp").forward(req, resp);
                }

            } else if (action.trim().equals("change")) {
                //修改个人信息
                if (user == null) {
                    //如果未登录，重定向到登录界面
                    resp.sendRedirect("/home?action=login");
                } else {
                    //已登录，转发到修改用户信息页面changeUser.jsp
                    req.getRequestDispatcher("/WEB-INF/jsps/changeUser.jsp").forward(req, resp);
                }
            } else if (action.trim().equals("update")) {
                //更新用户信息
                if (user == null) {
                    //未登录，重定向到登录界面
                    resp.sendRedirect("/user?action=logOn");
                } else {
                    //已登录
                    //获取原user各属性
                    String oldUsername = user.getUsername();
                    int oldSex = user.getSex();
                    long oldTelephone = user.getTelephone();
                    //获取页面要修改的属性
                    String username = req.getParameter("username");
                    String sex = req.getParameter("sex");
                    String telephone = req.getParameter("telephone");
                    //判断是否修改手机号，不修改则与原来相同
                    if ("".equals(telephone.trim())) {
                        telephone = String.valueOf(oldTelephone);
                    }
                    //判断是否修改用户名，不修改则与原来相同
                    if ("".equals(username)) {
                        username = oldUsername;
                    }
                    //判断是否修改性别
                    if (Integer.parseInt(sex) != oldSex) {
                        //执行数据库修改性别方法
                        int x = userDao.updateUserSex(oldUsername, Integer.parseInt(sex));
                        if (x > 0) {
                            //更新当前user属性的性别
                            user.setSex(Integer.parseInt(sex));
                        }
                    }
                    //判断是否修改手机号
                    if (Long.parseLong(telephone) != oldTelephone) {
                        //执行数据库修改手机号方法
                        int x = userDao.updateUserTelephone(oldUsername, Long.parseLong(telephone));
                        if (x > 0) {
                            //更新当前user属性的手机号
                            user.setTelephone(Long.parseLong(telephone));
                        }
                    }
                    //判断是否修改用户名
                    if (!username.equals(oldUsername)) {
                        //执行修改用户名方法
                        int x = userDao.updateUserUsername(oldUsername, username);
                        if (x > 0) {
                            //更新数据库博文表对应博文的作者用户名
                            int y = articleDao.updateArticleAuthor(oldUsername, username);
                            if (y > 0) {
                                //根据用户名获得博文
                                List<Article> myArticles = articleDao.queryByUsername(username);
                                //会话范围设置myArticles属性
                                session.setAttribute("myArticles", myArticles);
                            }
                            //更新当前user属性的用户名
                            user.setUsername(username);
                        } else {
                            //修改失败
                            //用户名已存在，抛出异常
                            req.setAttribute("repeat", true);
                            req.getRequestDispatcher("WEB-INF/jsps/changeUser.jsp").forward(req, resp);
                        }
                    }
                    //转发到用户个人信息页面user.jsp
                    req.getRequestDispatcher("/WEB-INF/jsps/user.jsp").forward(req, resp);
                }
            }
        } else {
            //直接访问/user
            if (user != null) {
                //已登录
                //根据用户名获得博文
                List<Article> myArticles = articleDao.queryByUsername(user.getUsername());
                //会话范围设置myArticles属性
                session.setAttribute("myArticles", myArticles);
                //转发到个人信息页面
                req.getRequestDispatcher("/WEB-INF/jsps/user.jsp").forward(req, resp);
            } else {
                //未登录
                //转发到登录页面
                resp.sendRedirect("/home?action=login");
            }
        }
    }
}
