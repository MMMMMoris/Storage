package com.cohiiPad.storage

import android.app.StatusBarManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
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
            LampSwitch.switch(applicationContext)
            val intent = Intent("com.cohii.storage.MY_BROADCAST")
            intent.setPackage(packageName)
            sendBroadcast(intent)
        }
        binding.lighterButton.setOnClickListener {
            LampSwitch.lighter(applicationContext)
//            lighter(applicationContext)
        }
        binding.dimmerButton.setOnClickListener {
            LampSwitch.dimmer(applicationContext)
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

//    class TileServiceActivity : TileService() {
//        //添加磁贴时调用
//        override fun onTileAdded() {
//            super.onTileAdded()
//        }
//
//        //移除磁贴时调用
//        override fun onTileRemoved() {
//            super.onTileRemoved()
//        }
//
//        //点击事件
//        override fun onClick() {
//            super.onClick()
//            LampSwitch.switch(applicationContext)
//            setQuickSettingColor()
//        }
//
//        //只有添加后才调用
//        //通知栏下拉时调用
//        override fun onStartListening() {
//            super.onStartListening()
////        setQuickSettingColor()
//        }
//
//        //通知栏关闭
//        override fun onStopListening() {
//            super.onStopListening()
//        }
//
//        private fun setQuickSettingColor() {
//            if( ! LampSwitch.lighted) {
//                qsTile.state = Tile.STATE_ACTIVE
//                qsTile.updateTile()
//                LampSwitch.lighted = true
//            }
//            else{
//                qsTile.state = Tile.STATE_INACTIVE
//                qsTile.updateTile()
//                LampSwitch.lighted = false
//            }
//        }
//    }
//        private var isTileActive = false
//        private fun toggleTileService() {
//            isTileActive = !isTileActive // 切换状态
//            val tileServiceIntent = Intent(this, TileService::class.java)
//            tileServiceIntent.putExtra("active", isTileActive)
//            startService(tileServiceIntent)
//            // 更新通知栏上的 Tile Service 图标
//            val qsTile = getSystemService(StatusBarManager::class.java)?.run {
//                getTile(StatusBarManager.TILE_ID)
//            }
//            qsTile?.state = if (isTileActive) Tile.STATE_ACTIVE else Tile.STATE_INACTIVE
//            qsTile?.updateTile()
//}
}