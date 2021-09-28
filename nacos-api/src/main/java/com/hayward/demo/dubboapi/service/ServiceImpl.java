package com.hayward.demo.dubboapi.service;

import com.hayward.demo.dubboapi.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class ServiceImpl implements SentiService{

    @Override
    public Result getinf(@PathVariable("id") String id) {
        return Result.succ(200,"降级了:"+id,"error");
    }

    @Override
    public String getport() {
        return "remotesucc";
    }
}
