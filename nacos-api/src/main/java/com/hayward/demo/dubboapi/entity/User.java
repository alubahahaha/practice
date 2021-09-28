package com.hayward.demo.dubboapi.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

@Data
public class User implements Serializable {

    Integer userId;
    String userName;
    String userSex;
    String userState;
    String userText;

    public User() {
    }

    public User(Integer id, String name, String sex, String state, String text) {
        this.userId = id;
        this.userName = name;
        this.userSex = sex;
        this.userState = state;
        this.userText = text;
    }

}
