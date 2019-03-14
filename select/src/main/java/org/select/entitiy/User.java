package org.select.entitiy;

public class User {
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", realname='" + realname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public User(String username, String realname, String password) {
        this.username = username;
        this.realname = realname;
        this.password = password;
    }

    public User(Long userId, String username, String realname, String password) {
        this.userId = userId;
        this.username = username;
        this.realname = realname;
        this.password = password;
    }

    public User() {
    }

    private String username;

    private String realname;
    private String password;
}
