package com.cohiiPad.storage.mqtt

import android.content.Context
import com.cohiiPad.storage.R
import info.mqtt.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions

class Connection(
    private val context: Context,
    private var host: String,
    private var port: Int,
    private var clientId: String,
    private var username: String,
    private var password: String,
    private val tls: Boolean
) {
    fun getMqttAndroidClient(context: Context): MqttAndroidClient {
        val uri: String = if (tls) {
            "ssl://$host:$port"
        } else {
            "tcp://$host:$port"
        }
        return MqttAndroidClient(context, uri, clientId)
    }

    val mqttConnectOptions: MqttConnectOptions
        get() {
            val options = MqttConnectOptions()
            options.isCleanSession = false
            if (tls) {
                try {
                    options.socketFactory =
                        SSLUtils.getSingleSocketFactory(context.resources.openRawResource(R.raw.cacert))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            if (username.isNotEmpty()) {
                options.userName = username
            }
            if (password.isNotEmpty()) {
                options.password = password.toCharArray()
            }
            return options
        }
}