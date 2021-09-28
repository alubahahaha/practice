package com.hayward.demo.dubboconsumer;

import redis.clients.jedis.Jedis;

import java.io.*;

public class SerialTest implements Serializable {

    private static final long serialVersionUID = 5817891682953072780L;

    private String string;
    public static void main(String[] args) throws Exception {
        Jedis jedis = new Jedis("192.168.120.128" , 7001 , 1000); // redis 的连接
        SerialTest serialTest = new SerialTest();
        serialTest.string="这是序列化和反序列化";
        //把Test3对象存入到Redis中，并从Redis获取Test3对象
        jedis.set("person".getBytes(), serialize(serialTest));
        byte[] byt=jedis.get("person".getBytes());
        SerialTest obj= (SerialTest) unserizlize(byt);
        System.out.println(obj.string);
    }
    //序列化
    public static byte [] serialize(Object obj) throws Exception {
        ByteArrayOutputStream bai = null;
        ObjectOutputStream obi = null;
        try {
            bai=new ByteArrayOutputStream();
            obi=new ObjectOutputStream(bai);
            obi.writeObject(obj);
            return bai.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(obi!=null){
                obi.close();
            }
            if(bai!=null){
                bai.close();
            }
        }
        return null;
    }

    //反序列化
    public static Object unserizlize(byte[] byt) throws Exception {
        ObjectInputStream oii=null;
        ByteArrayInputStream bis=null;
        try {
            bis=new ByteArrayInputStream(byt);
            oii=new ObjectInputStream(bis);
            return oii.readObject();
        } catch (Exception e) {

            e.printStackTrace();
        }finally {
            if(oii!=null){
                oii.close();
            }
            if(bis!=null){
                bis.close();
            }
        }
        return null;
    }
}