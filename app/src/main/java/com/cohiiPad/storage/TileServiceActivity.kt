package com.cohiiPad.storage


import android.service.quicksettings.Tile
import android.service.quicksettings.TileService


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
    } // 03a572d 版本的语法糖会导致错误？
}