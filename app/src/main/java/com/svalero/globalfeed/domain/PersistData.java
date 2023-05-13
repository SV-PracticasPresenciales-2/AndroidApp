package com.svalero.globalfeed.domain;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices ={@Index(value = {"id"}, unique = true)})
public class PersistData {
    @PrimaryKey
    private long id;
    private String username;
    private String password;
    private String token;
    private boolean rememberMe;
    private boolean centerOnMe;
    private boolean favDefault;

    public PersistData(long id, String username, String password, String token, boolean rememberMe, boolean centerOnMe, boolean favDefault) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.token = token;
        this.rememberMe = rememberMe;
        this.centerOnMe = centerOnMe;
        this.favDefault = favDefault;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public boolean isCenterOnMe() {
        return centerOnMe;
    }

    public void setCenterOnMe(boolean centerOnMe) {
        this.centerOnMe = centerOnMe;
    }

    public boolean isFavDefault() {
        return favDefault;
    }

    public void setFavDefault(boolean favDefault) {
        this.favDefault = favDefault;
    }

    @Override
    public String toString() {
        return "PersistData{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", rememberMe=" + rememberMe +
                ", centerOnMe=" + centerOnMe +
                ", favDefault=" + favDefault +
                '}';
    }
}
