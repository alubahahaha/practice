package com.hayward.demo.dubboapi.service;

import com.hayward.demo.dubboapi.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "user-service-provider",fallback = ServiceImpl.class)
public interface SentiService {

    @GetMapping("/getport/{id}")
    public Result getinf(@PathVariable("id") String id);

    @GetMapping("/nacos/myport")
    public String getport();


}
