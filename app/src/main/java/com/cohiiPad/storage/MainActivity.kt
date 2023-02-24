package com.cohiiPad.storage

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.hardware.ConsumerIrManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.cohiiPad.storage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val irManager = getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager
        fun switch() {
            irManager.transmit(38000, LampSwitch.switchIRCode)
        }
        fun lighter() {
            irManager.transmit(38000, LampSwitch.lighterIRCode)
        }
        fun dimmer() {
            irManager.transmit(38000, LampSwitch.dimmerIRCode)
        }
        binding.switchButton.setOnClickListener {
            switch()
        }
        binding.lighterButton.setOnClickListener {
            lighter()
        }
        binding.dimmerButton.setOnClickListener {
            dimmer()
        }
        binding.toSecondIntent.setOnClickListener {
            val intent = Intent(this, InfraredActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> Toast.makeText(this, "You clicked Add",
                Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "You clicked Remove",
                Toast.LENGTH_SHORT).show()
        }
        return true }
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
