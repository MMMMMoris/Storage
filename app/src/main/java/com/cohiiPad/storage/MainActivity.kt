package com.cohiiPad.storage

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.hardware.ConsumerIrManager
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val irManager = getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager
        val switchButton: Button = findViewById(R.id.switchButton)
        val lighterButton: Button = findViewById(R.id.lighterButton)
        val dimmerButton: Button = findViewById(R.id.dimmerButton)
        val secondIntent: Button = findViewById(R.id.toSecondIntent)
        fun switch() {
            irManager.transmit(LampSwitch.frequency, LampSwitch.switchIRCode)
        }
        fun lighter() {
            irManager.transmit(LampSwitch.frequency, LampSwitch.lighterIRCode)
        }
        fun dimmer() {
            irManager.transmit(LampSwitch.frequency, LampSwitch.dimmerIRCode)
        }
        switchButton.setOnClickListener {
            switch()
        }
        lighterButton.setOnClickListener {
            lighter()
        }
        dimmerButton.setOnClickListener {
            dimmer()
        }
        secondIntent.setOnClickListener {
            val intent = Intent(this, InfraredActivity::class.java)
            startActivity(intent)
        }
    }
}
//public class InfraredMethod: AppCompatActivity(){
//    private val irManager = getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager
//    fun switch() {
//        irManager.transmit(LampSwitch.frequency, LampSwitch.switchIRCode)
//    }
//    fun lighter() {
//        irManager.transmit(LampSwitch.frequency, LampSwitch.lighterIRCode)
//    }
//    fun dimmer() {
//        irManager.transmit(LampSwitch.frequency, LampSwitch.dimmerIRCode)
//    }
//}
