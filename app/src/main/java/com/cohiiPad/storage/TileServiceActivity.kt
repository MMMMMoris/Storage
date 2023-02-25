package com.cohiiPad.storage

import android.service.quicksettings.Tile
import android.service.quicksettings.TileService

class TileServiceActivity : TileService() {
    //添加磁贴时调用
    override fun onTileAdded() {
        super.onTileAdded()
    }

    //移除磁贴时调用
    override fun onTileRemoved() {
        super.onTileRemoved()
    }

    //点击事件
    override fun onClick() {
        super.onClick()
        LampSwitch.switch(applicationContext)
        setQuickSettingColor()
    }

    //只有添加后才调用
    //通知栏下拉时调用
    override fun onStartListening() {
        super.onStartListening()
//        setQuickSettingColor()
    }

    //通知栏关闭
    override fun onStopListening() {
        super.onStopListening()
    }

    private fun setQuickSettingColor() {
        if( ! LampSwitch.lighted) {
            qsTile.state = Tile.STATE_ACTIVE
            qsTile.updateTile()
            LampSwitch.lighted = true
        }
        else{
            qsTile.state = Tile.STATE_INACTIVE
            qsTile.updateTile()
            LampSwitch.lighted = false
        }
    }
}