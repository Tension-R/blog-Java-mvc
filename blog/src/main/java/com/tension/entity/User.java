package com.tension.entity;

/**
 * 用户实体类
 * Created by tension on 17-6-9.
 */
public class User {

    private String username;
    private String password;
    private int sex;
    private long telephone;

    public User() {
    }

    public User(String username, String password, int sex, long telephone) {
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.telephone = telephone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public long getTelephone() {
        return telephone;
    }

    public void setTelephone(long telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", telephone=" + telephone +
                '}';
    }
}