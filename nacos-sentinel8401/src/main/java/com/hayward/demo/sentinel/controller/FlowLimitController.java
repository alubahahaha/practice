package com.hayward.demo.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {
        @GetMapping("/test1")
        public String test1(){
            return "test1";
        }
    @GetMapping("/test2")
    public String test2(){
        return "test2";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testhotkey(@RequestParam(value = "p1" ,required = false) String p1,
                             @RequestParam(value = "p2" ,required = false) String p2){
            return "testHotKey";
    }

    public String deal_testHotKey (String p1, String p2, BlockException exception){

            return "testHotKeyHandler...........";
    }
}
