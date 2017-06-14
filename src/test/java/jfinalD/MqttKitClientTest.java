package jfinalD;

import java.util.concurrent.ConcurrentHashMap;

import com.jfinalD.framework.mqtt.MqttKit;
import com.jfinalD.framework.mqtt.MqttPlugin;
import org.eclipse.paho.client.mqttv3.MqttException;


public class MqttKitClientTest {

    public static void main(String[] args) throws MqttException {
        final String clientId = "client001";
        MqttPlugin plugin = new MqttPlugin("tcp://172.16.100.126:1883", clientId);
        final ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<String, Long>();
        plugin.setAutomaticReconnection(true);
        plugin.setCleanSession(true);
        plugin.setConnectionTimeout(10);
        plugin.setKeepAliveInterval(10);
        plugin.setManualAcks(false);
        plugin.setMaxConnections(20);
        plugin.setUserName("admin");
        plugin.setPassword("password");
        plugin.setVersion("3.1.1");
        plugin.start();

        System.out.println("===============start===================");
        final String topic = "/mqtt_plugin/test";
        final int qos = 2;

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
                        Thread.sleep(1000);
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