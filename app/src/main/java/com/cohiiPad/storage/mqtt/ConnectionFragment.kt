package com.cohiiPad.storage.mqtt

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import com.cohiiPad.storage.MqttActivity
import com.cohiiPad.storage.R
import org.eclipse.paho.client.mqttv3.IMqttActionListener
import org.eclipse.paho.client.mqttv3.IMqttToken

class ConnectionFragment : BaseFragment() {
    private lateinit var mHost: EditText
    private lateinit var mPort: EditText
    private lateinit var mClientId: EditText
    private lateinit var mUsername: EditText
    private lateinit var mPassword: EditText
    private lateinit var mTlsButton: RadioButton
    private lateinit var mButton: Button
    override val layoutResId: Int
        get() = R.layout.fragment_connection

    @SuppressLint("SetTextI18n")
    override fun setUpView(view: View) {
        mHost = view.findViewById(R.id.host)
        mHost.setText("galileo.cohii.com")
        mPort = view.findViewById(R.id.port)
        mClientId = view.findViewById(R.id.clientid)
        // 生成隨機 client ID
        /*mClientId.setText(MqttAsyncClient.generateClientId())*/
        mClientId.setText("Android Client")
        mUsername = view.findViewById(R.id.username)
        mPassword = view.findViewById(R.id.password)
        mTlsButton = view.findViewById(R.id.tls_true)
        mButton = view.findViewById(R.id.btn_connect)
        mButton.setOnClickListener {
            if (mButton.text.toString() == getString(R.string.connect)) {
                val connection = Connection(
                    fragmentActivity!!,
                    mHost.text.toString(),
                    mPort.text.toString().toInt(),
                    mClientId.text.toString(),
                    mUsername.text.toString(),
                    mPassword.text.toString(),
                    mTlsButton.isChecked
                )
                (fragmentActivity as MqttActivity).connect(
                    connection,
                    object : IMqttActionListener {
                        override fun onSuccess(asyncActionToken: IMqttToken) {
                            Log.d(
                                "ConnectionFragment",
                                "Connected to: " + asyncActionToken.client.serverURI
                            )
                            updateButtonText()
                        }

                        override fun onFailure(asyncActionToken: IMqttToken, exception: Throwable) {
                            Toast.makeText(
                                fragmentActivity,
                                exception.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                            exception.printStackTrace()
                        }
                    })
            } else {
                (fragmentActivity as MqttActivity).disconnect()
            }
        }
    }

    fun updateButtonText() {
        fragmentActivity?.runOnUiThread {
            if ((fragmentActivity as MqttActivity).notConnected(false)) {
                mButton.text = getText(R.string.connect)
            } else {
                mButton.text = getString(R.string.disconnect)
            }
        }
    }

    companion object {
        fun newInstance(): ConnectionFragment {
            return ConnectionFragment()
        }
    }
}