package com.tutorial.transportsystem.dto;

public class LoginDTO {
    private String login;
    private String password;

    public LoginDTO(String user, String wrong) {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
