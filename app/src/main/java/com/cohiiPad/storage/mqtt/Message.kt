package com.cohiiPad.storage.mqtt

import org.eclipse.paho.client.mqttv3.MqttMessage

class Message(var topic: String, var message: MqttMessage)