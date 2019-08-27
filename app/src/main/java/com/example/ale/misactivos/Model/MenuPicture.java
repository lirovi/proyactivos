package com.example.ale.misactivos.Model;

public class MenuPicture {
    private String picture;
    private String username;

    public MenuPicture(String picture, String username) {
        this.picture = picture;
        this.username = username;

    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
