package com.hayward.demo.dubboconsumer.controller;

import com.alibaba.fastjson.JSON;
import com.hayward.demo.dubboapi.entity.User;
import com.hayward.demo.dubboapi.response.Result;
import com.hayward.demo.dubboapi.service.SentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;

@RestController
@RefreshScope
public class TestController {
//    @Reference(version = "${provider.service.version}")
//    TestService testService;

    @Resource
    private RestTemplate restTemplate;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @RequestMapping("/show")
    public String show(){
        return restTemplate.getForObject(serverURL+"/nacos/myport/",String.class);
    }

    @RequestMapping("/resql/{id}")
    public String resql(@PathVariable("id") String id){
        return restTemplate.getForObject(serverURL+"/advanced/resql/"+id,String.class);
    }

    /*redis start*/
    @RequestMapping("/redisdel")
    public String redisdel(String context){
        Set<String> keys = redisTemplate.keys(context+"*");
        redisTemplate.delete(keys);
        return "DelSuccess";
    }

    @RequestMapping("/redisadd")
    public String redisadd(String id,String name){

        for (int i = 0;i<20;i++) {
            redisTemplate.opsForValue().set("uid:" + id+i, name+i);
        }
        return "AddSuccess";
    }

//    @RequestMapping("/redisadd2")
//    public String redisadd2() throws ParseException {
//            User user = new User(11,"huangyiming","sznsyh","2020-02-02","shenzhen");
//            redisTemplate.opsForHash().put("yonghu","name",user.getName());
//            redisTemplate.opsForHash().put("yonghu","id",user.getId());
//            redisTemplate.opsForHash().put("yonghu","time",user.getTime());
//            redisTemplate.opsForHash().put("yonghu","district",user.getDes());
//            redisTemplate.opsForHash().put("yonghu","time",user.getTime());
//            Map<String,String> resultMap = new HashMap<>();
//        resultMap.put("id", (String) redisTemplate.opsForHash().get("yonghu","id"));
//        resultMap.put("name", (String) redisTemplate.opsForHash().get("yonghu","name"));
//        resultMap.put("data", (String) redisTemplate.opsForHash().get("yonghu","data"));
//        resultMap.put("time", (String) redisTemplate.opsForHash().get("yonghu","time"));
//        resultMap.put("des", (String) redisTemplate.opsForHash().get("yonghu","des"));
//        return JSON.toJSONString(resultMap);
//    }

    @GetMapping("/redisget")
    public String redisget(){
        Set<String> s = redisTemplate.keys("uid:*");
        if (s==null) return "error";
        Iterator<String> iterator = s.iterator();
        Map<String,String> resultMap = new HashMap<>();

        String key=null;
        for (int i=0;i<s.size();i++){
            key = iterator.next();
            String value = redisTemplate.opsForValue().get(key);
            resultMap.put(key,value);
        }
        String res_String = JSON.toJSONString(resultMap);
        return res_String;
    }
    /*redis end*/

    @Resource
    private SentiService sentiService;
    @GetMapping("/getinf/{id}")
    public Result getinf(@PathVariable("id") String id){
        return  sentiService.getinf(id);
    }
    @GetMapping("/getport")
    public String getinf(){
        return  sentiService.getport();
    }
}
