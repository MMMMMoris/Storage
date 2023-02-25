package com.cohiiPad.storage

import android.service.quicksettings.Tile
import android.service.quicksettings.TileService

class TileServiceActivity : TileService() {
    private var lighted = true
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
        if( ! lighted) {
            qsTile.state = Tile.STATE_ACTIVE
            qsTile.updateTile()
            lighted = true
        }
        else{
            qsTile.state = Tile.STATE_INACTIVE
            qsTile.updateTile()
            lighted = false
        }
    }

    //只有添加后才调用
    //通知栏下拉
    override fun onStartListening() {
        super.onStartListening()
    }

    //通知栏关闭
    override fun onStopListening() {
        super.onStopListening()
    }

    fun setQuickSettingColor() {
        //更改成非活跃状态(灰色)
        qsTile.state = Tile.STATE_ACTIVE
        //更改成活跃状态(白色)
        //getQsTile().setState(Tile.STATE_INACTIVE);
        qsTile.updateTile() //更新Tile
    }

//    companion object {
//
//        fun setTileStatus(){
//            if( ! lighted) {
//                qsTile.state = Tile.STATE_ACTIVE
//                lighted = true
//            }
//            else{
//                qsTile.state = Tile.STATE_INACTIVE
//                lighted = false
//            }
//        }
//    }
}