package com.hayward.demo.dubboprovider.service;

import com.hayward.demo.dubboapi.entity.User;
import com.hayward.demo.dubboapi.response.Result;
import com.hayward.demo.dubboapi.service.TestService;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service(version = "${provider.service.version}")
public class TestServiceImpl implements TestService {

    @Override
    public User userinf(Integer id, String name) {
        User td = new User();
        td.setUserId(id);
        td.setUserName(name);
        if(Math.random()>0.5){
            throw new RuntimeException();
        }
        return td;

    }

//    @Override
//    public Result getinfo(@PathVariable("id") String id){
//        return Result.succ(id);
//    }
}
