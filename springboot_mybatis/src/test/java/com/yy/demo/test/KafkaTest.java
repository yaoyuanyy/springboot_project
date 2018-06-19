package com.yy.demo.test;

import com.yy.demo.kafka.MsgProducer;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Description:
 * <p></p>
 * <pre></pre>
 * NB.
 * Created by skyler on 2018/6/19 at 下午2:16
 */
public class KafkaTest extends AppBaseTest{

    @Resource
    private MsgProducer producer;

    @Test
    public void sendMessage(){
        producer.sendMessage("topic-1", "i am coming");
        producer.sendMessage("topic-2", "hello world");
    }
}
