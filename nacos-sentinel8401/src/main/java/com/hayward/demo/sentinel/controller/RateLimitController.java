package com.hayward.demo.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.hayward.demo.sentinel.handler.CustomBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {
        @GetMapping("/byResource")
        @SentinelResource(value = "byResource" ,blockHandler = "handlerException")
        public String byresource(){
            return "byResourse";
        }

        public String handlerException(BlockException e){
            return "byResourceException"+e.getClass();
        }
    @GetMapping("/customHandler")
    @SentinelResource(value = "byResource" ,blockHandlerClass = CustomBlockHandler.class,blockHandler = "handlerException2")
    public String customHandler(){
        return "byHandler";
    }
}
