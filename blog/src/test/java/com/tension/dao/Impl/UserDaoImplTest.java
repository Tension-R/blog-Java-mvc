package com.tension.dao.Impl;

import com.tension.dao.UserDao;
import com.tension.entity.User;
import junit.framework.TestCase;

import java.util.List;

/**
 * Created by tension on 17-6-10.
 */
public class UserDaoImplTest extends TestCase {
    private UserDao userDao = new UserDaoImpl();
    public void testInsertUser() throws Exception {
        String username = "ali";
        String password = "188375";
        int sex = 1;
        long telephone = 18716235324l;
        int x = userDao.insertUser(username,password,sex,telephone);
        System.out.println(x);
    }

    public void testDeleteUser() throws Exception {
        String username = "li";
        int x = userDao.deleteUser(username);
        System.out.println(x);
    }

    public void testUpdateUserTelephone() throws Exception {
        String username = "li";
        long telephone = 13824758237l;
        int x = userDao.updateUserTelephone(username,telephone);
        System.out.println(x);
    }

    public void testUpdateUserSex() throws Exception {
        String username = "li";
        int sex = 0;
        int x = userDao.updateUserSex(username,sex);
        System.out.println(x);
    }

    public void testQueryByName() throws Exception {
        String username = "li";
        List<User> userList = userDao.queryByName(username);
        System.out.println(userList);
    }

}