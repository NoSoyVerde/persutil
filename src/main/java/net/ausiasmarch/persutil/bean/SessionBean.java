package net.ausiasmarch.persutil.bean;

public class SessionBean {
    
    private String username;
    private String password;

    public SessionBean() {
    }

    public SessionBean(String username, String password) {
        this.username = username;
        this.password = password;
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
}
