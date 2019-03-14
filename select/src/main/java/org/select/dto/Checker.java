package org.select.dto;

public class Checker {
    private Boolean checked;
    private String username;
    private Long userId;

    public Checker(Boolean checked) {
        this.checked = checked;
    }

    public Checker(Boolean checked, String username, Long userId) {
        this.checked = checked;
        this.username = username;
        this.userId = userId;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Checker{" +
                "checked=" + checked +
                ", username='" + username + '\'' +
                ", userId=" + userId +
                '}';
    }
}
