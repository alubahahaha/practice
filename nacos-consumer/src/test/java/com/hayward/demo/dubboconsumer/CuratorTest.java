package com.hayward.demo.dubboconsumer;

import com.hayward.demo.dubboapi.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

@SpringBootTest
class CuratorTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void redisTemplate() throws Exception {
        redisTemplate.opsForValue().set("author", "Damein_xym");

    }

    @Test
    public static byte[] testSerial(){
        Date date = new Date();
        User user = new User(12,"NAME","datas","date","shenzhen");

        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(user);

            byte[] bytes = baos.toByteArray();
            System.out.println(baos);
            System.out.println(oos);
            System.out.println(bytes);
            return bytes;
        } catch (Exception e) {
        }
        return null;
    }

    @Test
    public  Object unserialize( byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
        }
        return null;
    }
}

