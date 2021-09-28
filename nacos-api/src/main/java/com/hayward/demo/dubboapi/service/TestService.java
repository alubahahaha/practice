package com.hayward.demo.dubboapi.service;

import com.hayward.demo.dubboapi.entity.User;
import com.hayward.demo.dubboapi.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


public interface TestService {
    User userinf(Integer id,String name);

}
