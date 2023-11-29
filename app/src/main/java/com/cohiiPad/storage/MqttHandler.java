/*package com.example.mqtt

import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttException
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence

class MqttHandler {
    private val client: MqttClient? = null
    fun connect(brokerUrl: String?, clientId: String?) {
        try {
            // Set up the persistence layer
            val persistence = MemoryPersistence()
            // Initialize the MQTT client
            val client = MqttClient(brokerUrl, clientId, persistence)
            // Set up the connection options
            val connectOptions = MqttConnectOptions()
            connectOptions.userName = "moris"
            connectOptions.password = "justtest".toCharArray()
            connectOptions.isCleanSession = true
            // Connect to the broker
            client.connect(connectOptions)
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    fun disconnect() {
        try {
            client!!.disconnect()
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    fun publish(topic: String?, message: String) {
        try {
            val mqttMessage = MqttMessage(message.toByteArray())
            client!!.publish(topic, mqttMessage)
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    fun subscribe(topic: String?) {
        try {
            client!!.subscribe(topic)
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }
}*/

//JAVA VERSION
package com.cohiiPad.storage;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.jetbrains.annotations.NotNull;

public class MqttHandler {
    private MqttClient client;

    public void connect(String brokerUrl, String clientId, String userName, @NotNull String password) {
        try {
            // Set up the persistence layer
            MemoryPersistence persistence = new MemoryPersistence();
            // Initialize the MQTT client
            client = new MqttClient(brokerUrl, clientId, persistence);
            // Set up the connection options
            MqttConnectOptions connectOptions = new MqttConnectOptions();
            connectOptions.setUserName(userName);
            connectOptions.setPassword(password.toCharArray());
            connectOptions.setCleanSession(true);
            // Connect to the broker
            client.connect(connectOptions);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void publish(String topic, @NotNull String message) {
        try {
            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            client.publish(topic, mqttMessage);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void subscribe(String topic) {
        try {
            client.subscribe(topic);
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {

                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    System.out.println("Message received:");
                    System.out.println("Topic: " + topic);
                    System.out.println("Message: " + new String(message.getPayload()));
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
