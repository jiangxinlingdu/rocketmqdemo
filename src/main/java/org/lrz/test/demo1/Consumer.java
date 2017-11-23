
package org.lrz.test.demo1;

import java.io.UnsupportedEncodingException;
import java.util.List;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * Consumer，订阅消息
 */
public class Consumer {

    public static void main(String[] args) throws InterruptedException, MQClientException {

        /**
         * Consumer Group,非常重要的概念，后续会慢慢补充
         */
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer_demo");
        //指定NameServer地址，多个地址以 ; 隔开
        consumer.setNamesrvAddr("192.168.116.115:9876;192.168.116.116:9876"); //修改为自己的

        /**
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
         * 如果非第一次启动，那么按照上次消费的位置继续消费
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        consumer.subscribe("TopicTest", "*");

        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                ConsumeConcurrentlyContext context) {
                try {
                    for(MessageExt msg:msgs){
                        String msgbody = new String(msg.getBody(), "utf-8");
                        System.out.printf(Thread.currentThread().getName()+"  MessageBody: "+ msgbody + " Receive New Messages: " + msg + "%n");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER; //稍后再试
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; //消费成功
            }
        });


        consumer.start();

        System.out.printf("Consumer Started.%n");
    }
}
