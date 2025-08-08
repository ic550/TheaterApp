package com.example.app.login;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Data;

@Data
@Component
@SessionScope
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LoginStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private boolean isAdmin;
    private boolean loggedIn;
    private String role;

    // ゲッター・セッター
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isLoggedIn() {
        return id != null;
    }

    public void clear() {
        this.id = null;
        this.name = null;
        this.isAdmin = false;
    }
}
