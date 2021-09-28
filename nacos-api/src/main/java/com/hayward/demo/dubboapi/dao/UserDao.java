package com.hayward.demo.dubboapi.dao;

import com.hayward.demo.dubboapi.entity.User;

public interface UserDao {
    public User findById(String id);
}
