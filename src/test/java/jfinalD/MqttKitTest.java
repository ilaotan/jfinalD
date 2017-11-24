package jfinalD;

import java.util.concurrent.ConcurrentHashMap;

import com.ilaotan.framework.mqtt.MqttKit;
import com.ilaotan.framework.mqtt.MqttPlugin;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class MqttKitTest {

    public static void main(String[] args) throws MqttException {
        final String clientId = "client000";
        MqttPlugin plugin = new MqttPlugin("tcp://172.16.100.126:1883", clientId);
        final ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<String, Long>();
        plugin.setAutomaticReconnection(true);
        plugin.setCleanSession(false);
        plugin.setConnectionTimeout(10);
        plugin.setKeepAliveInterval(10);
        plugin.setManualAcks(true);
        plugin.setMaxConnections(20);
        plugin.setUserName("admin");
        plugin.setPassword("password");
        plugin.setVersion("3.1.1");
        plugin.start();

        System.out.println("===============start===================");
        final String topic = "/mqtt_plugin/test";
        final int qos = 1;
        MqttKit.sub(topic, qos, new IMqttMessageListener() {
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                String msg = new String(message.getPayload());
                if (map.containsKey(msg)) {
                    System.err.println("重复消息\t" + msg + "\t距离第一次收到消息的时间间隔 " + (System.currentTimeMillis() - map.get
                            (msg)) + " 毫秒");
                }
                else {
                    map.put(msg, System.currentTimeMillis());
                    System.out.println(clientId + "收到消息\t" + msg);
                }
            }
        });
        (new Thread(new Runnable() {

            public void run() {
                int i = 0;
                for (; ; ) {
                    try {
                        MqttKit.pub(topic, ((i + 1) + "").getBytes(), qos, false);
                        System.out.println("发送消息\t" + (i + 1));
                        i++;
                    }
                    catch (MqttException e1) {
                        System.out.println("发送消息\t" + (i + 1) + "\t失败");
                        //e1.printStackTrace();

                    }
                    try {
                        Thread.sleep(1 * 1000);
                    }
                    catch (InterruptedException e) {
                        // TODO Auto-generated catch block

                        e.printStackTrace();
                    }
                    finally {
                    }
                }
            }
        })).start();
    }

}