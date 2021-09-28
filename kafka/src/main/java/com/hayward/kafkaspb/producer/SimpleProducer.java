package com.hayward.kafkaspb.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class SimpleProducer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @GetMapping("/simple/sendmsg/{message}")
    public void sendmsg(@PathVariable("message") String message){
        kafkaTemplate.send("topic2",message);
        System.out.println("simpletopic");
    }

    @GetMapping("/simple/senddata")
    public void senddata(){
        for(Integer i=0;i<10;i++) {
            String msg = i.toString();
            kafkaTemplate.send("topic2",msg);
        }
    }

    @GetMapping("/callback1/{message}")
    public void callback1(@PathVariable("message") String callbackMessage){
        kafkaTemplate.send("topic1",callbackMessage).addCallback(success ->{
            String topic = success.getRecordMetadata().topic();
            int partition = success.getRecordMetadata().partition();
            long offset = success.getRecordMetadata().offset();
            System.out.println("callback1:"+topic+"-"+partition+"-"+offset);
        },failure->{
            System.out.println("error!");
        });
    }

    @GetMapping("/callback2/{message}")
    public void callback2(@PathVariable("message") String callbackMessage){
        kafkaTemplate.send("topic1",callbackMessage).addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("error! "+throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                System.out.println("success:"+result.getRecordMetadata().topic()+"-"+result.getRecordMetadata().partition()+
                        "-"+result.getRecordMetadata().offset());
            }
        });
    }
}
