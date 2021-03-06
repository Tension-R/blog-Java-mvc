package com.tension.dao;

import com.tension.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * 用户逻辑
 * Created by tension on 17-6-10.
 */
public interface UserDao {

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 删除用户
     *
     * @param username
     * @return
     */
    int deleteUser(String username);

    /**
     * 修改用户手机号
     *
     * @param username
     * @param telephone
     * @return
     */
    int updateUserTelephone(String username, long telephone);

    /**
     * 修改用户名
     * @param oldUsername
     * @param username
     * @return
     */
    int updateUserUsername(String oldUsername, String username);

    /**
     * 修改用户性别
     *
     * @param username
     * @param sex
     * @return
     */
    int updateUserSex(String username, int sex);

    /**
     * 按用户名模糊查询用户
     *
     * @param username
     * @return
     */
    List<User> queryByName(String username);

    /**
     * 登录时判断用户名密码
     *
     * @param username
     * @param password
     * @return
     */
    User checkUser(String username, String password);
}
