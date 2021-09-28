package com.hayward.demo.dubboprovider.controller;


import com.hayward.demo.dubboapi.response.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nacos")
public class servercontroller {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "myport")
    public String getPayment()
    {
        return "serverPort: "+ serverPort;
    }

    @GetMapping(value = "getport/{id}")
    public Result getport(@PathVariable("id") String id)
    {
        String ret = serverPort+" "+id;
        return Result.succ(ret);
    }
}
