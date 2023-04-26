package com.cohiiPad.storage

import android.appwidget.AppWidgetManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
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
        setSupportActionBar(binding.toolbar)
        binding.switchButton.setOnClickListener {
            LampSwitch.switch(this)
//            val intent = Intent("com.cohii.storage.MY_BROADCAST")
//            intent.setPackage(packageName)
//            sendBroadcast(intent)
//            updateTile(true)
        }
        binding.lighterButton.setOnClickListener {
            LampSwitch.lighter(this)
//            lighter(applicationContext)
        }
        binding.dimmerButton.setOnClickListener {
            LampSwitch.dimmer(this)
//            dimmer(applicationContext)
        }
        binding.toSecondIntent.setOnClickListener {
//            val intent = Intent(this, InfraredActivity::class.java)
//            startActivity(intent)
            val intent = Intent("com.cohiiPad.storage.ACTION_START")
            intent.addCategory("com.cohiiPad.storage.MY_CATEGORY")
            startActivity(intent)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.backup -> Toast.makeText(this, "You clicked Backup",
                Toast.LENGTH_SHORT).show()
            R.id.delete -> Toast.makeText(this, "You clicked Delete",
                Toast.LENGTH_SHORT).show()
            R.id.settings -> Toast.makeText(this, "You clicked Settings",
                Toast.LENGTH_SHORT).show()
            R.id.add_item -> Toast.makeText(this, "You clicked Add",
                Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "You clicked Remove",
                Toast.LENGTH_SHORT).show()
        }
        return true
    }
    private fun updateTile(state: Boolean) {
        val intent = Intent(this, TileServiceActivity::class.java)
        intent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, intArrayOf(R.id.my_tile))
        intent.putExtra("state", state)
        sendBroadcast(intent)
    }
//    }
}

//我现在有一个用 kotlin 写的台灯遥控器 android app，我注册了一个tile service，可以通过点击通知栏就能够控制台灯的开关，同时更新通知栏上面的磁贴的激活与否的状态。现在我想要通过点击app MainActivity的按钮，同时也能实现状态栏磁贴状态的更新，这该怎么做到？