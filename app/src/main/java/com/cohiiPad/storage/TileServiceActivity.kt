package com.cohiiPad.storage

import android.appwidget.AppWidgetManager
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import android.content.Context
import android.widget.RemoteViews


class TileServiceActivity : TileService() {

     fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {
        // Get the state from the broadcast intent
        val state = AppWidgetManager.getInstance(context).getAppWidgetOptions(appWidgetIds?.get(0) ?: 0).getBoolean("state")

        // Update the tile with a RemoteViews object
        val views = RemoteViews(context?.packageName, R.id.my_tile)
        views.setTextViewText(R.id.tile_text_view, if (state) "ON" else "OFF")

        // Apply the update to the widget
         appWidgetIds?.get(0)?.let { appWidgetManager?.updateAppWidget(it, views) }

    }

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
    }

    //通知栏关闭
    override fun onStopListening() {
        super.onStopListening()
    }

    private fun setQuickSettingColor() {
        if( LampSwitch.lighted) {
            qsTile.state = Tile.STATE_INACTIVE
            qsTile.updateTile()
            LampSwitch.lighted = false
        }
        else{
            qsTile.state = Tile.STATE_ACTIVE
            qsTile.updateTile()
            LampSwitch.lighted = true
        }
    }
}