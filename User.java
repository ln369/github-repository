package com.cn.ln.Beans;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * company: www.abc.com
 * Author: ASUS
 * Create Data: 2019/3/11
 */
public class User {
    private int id;
    private String loginname;
    private String password;
    private Integer status;
    //@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdate;
    private String username;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", loginname='" + loginname + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", createdate=" + createdate +
                ", username='" + username + '\'' +
                '}';
    }
}
