package com.hayward.demo.dubboapi.response;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class Result implements Serializable {
    private int code;
    private String msg;
    private Object data;

    private Boolean success;

    private String message;

    private Integer codes;


    private Map<String,Object> datas = new HashMap<>();

    /**
     * 构造方法私有化,里面的方法都是静态方法
     * 达到保护属性的作用
     */
    private Result(){

    }
    /**
     * 这里是使用链式编程
     */


    public static Result succ(Object data){
        return succ(200,"success!",data);
    }
    public static Result succ(int code,String msg,Object data){
        Result r = new Result();
        r.setCode(code);
        r.setData(data);
        r.setMsg(msg);
        return r;
    }
    public static Result fail(String msg){
        return fail(400,msg,null);
    }
    public static Result fail(String msg,Object data){
        return fail(400,msg,data);
    }
    public static Result fail(int code,String msg,Object data){
        Result r = new Result();
        r.setCode(code);
        r.setData(data);
        r.setMsg(msg);
        return r;
    }

    public Result code(Integer codes){
        this.setCode(codes);
        return this;
    }
}