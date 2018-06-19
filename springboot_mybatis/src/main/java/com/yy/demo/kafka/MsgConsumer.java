package com.yy.demo.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Description:
 * <p></p>
 * <pre></pre>
 * NB.
 * Created by skyler on 2018/6/19 at 下午2:08
 */
@Component
public class MsgConsumer {

    @KafkaListener(topics = {"topic-1","topic-2"})
    public void processMessage(String content) {

        System.out.println("消息被消费："+content);
    }

}
