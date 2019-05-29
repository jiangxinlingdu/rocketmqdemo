
package org.mq.test.demo1;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * Producer，发送消息
 *
 */
public class Producer {
    public static void main(String[] args) throws MQClientException, InterruptedException {


        DefaultMQProducer producer = new DefaultMQProducer("producer_demo");
       //指定NameServer地址
        producer.setNamesrvAddr("192.168.116.115:9876;192.168.116.116:9876"); //修改为自己的

        /**
         * Producer对象在使用之前必须要调用start初始化，初始化一次即可
         * 注意：切记不可以在每次发送消息时，都调用start方法
         */
        producer.start();

        for (int i = 0; i < 10; i++) {
            try {
                //构建消息
                Message msg = new Message("TopicTest" /* Topic */,
                    "TagA" /* Tag */,
                    ("测试RocketMQ" + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
                );

                //发送同步消息
                SendResult sendResult = producer.send(msg);

                System.out.printf("%s%n", sendResult);
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }


        producer.shutdown();
    }
}
