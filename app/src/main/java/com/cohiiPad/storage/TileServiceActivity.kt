package com.cohiiPad.storage


import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import android.widget.Toast


class TileServiceActivity : TileService() {

    // Define a function to handle click events on your switch button in your app
    override fun onTileAdded() {
        super.onTileAdded()
    } //添加磁贴时调用，复写onTileAdded()方法

    //移除磁贴时调用
    override fun onTileRemoved() {
        super.onTileRemoved()
    }

    //点击事件
    override fun onClick() {
        super.onClick()
        LampSwitch.switch(this)
        setQuickSettingColor()
    }//只有添加后才调用

    //通知栏下拉时调用
    override fun onStartListening() {
        super.onStartListening()
//        setQuickSettingColor()
//        if (qsTile.state == Tile.STATE_INACTIVE){
//            Toast.makeText(this, "未激活", Toast.LENGTH_SHORT).show()
//        }
//        else{
//            Toast.makeText(this, "激活", Toast.LENGTH_SHORT).show()
//        }
    }

    //通知栏关闭
    override fun onStopListening() {
        super.onStopListening()
    }
    private fun setQuickSettingColor() {
        if(qsTile.state == Tile.STATE_ACTIVE) {
            qsTile.state = Tile.STATE_INACTIVE
            qsTile.updateTile()
        }
        else{
            qsTile.state = Tile.STATE_ACTIVE
            qsTile.updateTile()
        }
    }
}