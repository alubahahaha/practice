package com.hayward.demo.dubboprovider.controller;

import com.alibaba.fastjson.JSON;
import com.hayward.demo.dubboapi.dao.UserDao;
import com.hayward.demo.dubboapi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/advanced")
public class AdvancedController {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/findbyid/{id}")
    public String findbyid(@PathVariable("id") String id){

        User s = userDao.findById(id);
        System.out.println(s);
        String ret = JSON.toJSONString(s);
        return ret;
    }

    @GetMapping("/resql/{id}")
    public String resql(@PathVariable("id") String id) throws ParseException, InterruptedException {
        /*先查redis 再查数据库*/
        if (redisTemplate.opsForHash().get("user:" + id, "id") == null) {
            System.out.println("缓存中没有，从数据库中加载");
            User user = userDao.findById(id);
            addtoCache(user);
            Thread.sleep(200);
        }
        return getFromCache(id);
    }
    public void addtoCache(User user){
        String id = user.getUserId().toString();
        redisTemplate.opsForHash().put("user:"+id,"id", id);
        redisTemplate.opsForHash().put("user:"+id,"name", user.getUserName());
        redisTemplate.opsForHash().put("user:"+id,"sex", user.getUserSex());
        redisTemplate.opsForHash().put("user:"+id,"state", user.getUserState());
        redisTemplate.opsForHash().put("user:"+id,"text", user.getUserText());
        System.out.println("已加载到缓存");
    }

        public String getFromCache(String id){
        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("id", (String) redisTemplate.opsForHash().get("user:"+id,"id"));
        resultMap.put("name", (String) redisTemplate.opsForHash().get("user:"+id,"name"));
        resultMap.put("sex", (String) redisTemplate.opsForHash().get("user:"+id,"sex"));
        resultMap.put("state", (String) redisTemplate.opsForHash().get("user:"+id,"state"));
        resultMap.put("text", (String) redisTemplate.opsForHash().get("user:"+id,"text"));
        return JSON.toJSONString(resultMap);
        }
    }

