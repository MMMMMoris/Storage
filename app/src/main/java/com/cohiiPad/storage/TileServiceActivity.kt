package com.cohiiPad.storage

import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import android.widget.Toast
//import com.cohiiPad.storage.MainActivity

//abstract class TileServiceActivity {
////    abstract val ACTION_QS_TILE: String
////    TileService()
//}
//@SuppressLint("NewApi")
//class TileServiceActivity : TileService() {
//
//    companion object {
//        const val TAG = "QuickStartTileService"
//    }
//
//    //当用户从Edit栏添加到快速设置中调用
//    override fun onTileAdded() {
//        super.onTileAdded()
//        showDebugToast(context = applicationContext, msg = "onTileAdded")
//        log("onTileAdded", TAG)
//    }
//
//    //当用户从快速设置栏中移除的时候调用
//    override fun onTileRemoved() {
//        super.onTileRemoved()
//        log("onTileRemoved", TAG)
//    }
//    // 点击的时候
//    /*  我们可以通过getQsTile来获得Tile对象，通过getState() 来获得Tile当前状态。
//      - STATE_ACTIVE 开启状态
//      - STATE_INACTIVE 关闭状态
//      - STATE_UNAVAILABLE 非可点击状态
//      最后必须调用updateTile() 来触发刷新*/
//
//    override fun onClick() {
//        super.onClick()
//        log("onClick", TAG)
//        val state = qsTile.state
//        log(tag = TAG, msg = "onClick state = " + qsTile.state.toString())
//        var icon: Icon = Icon.createWithResource(applicationContext, R.drawable.ic_message)
//
//        when (state) {
//            Tile.STATE_INACTIVE -> {
//                //                icon = Icon.createWithResource(applicationContext, R.drawable.logo_qq)
//                qsTile.state = Tile.STATE_ACTIVE// 更改成活跃状态
//                showDebugToast(applicationContext, "STATE_INACTIVE to STATE_ACTIVE")
//                startOrShowNotification()
//            }
//            Tile.STATE_ACTIVE -> {
//                //                icon = Icon.createWithResource(applicationContext, R.drawable.logo_wechat)
//                qsTile.state = Tile.STATE_INACTIVE//更改成非活跃状态
//                showDebugToast(applicationContext, "STATE_ACTIVE to STATE_INACTIVE")
//                cancelNotification()
//            }
//            Tile.STATE_UNAVAILABLE -> {
//                //                icon = Icon.createWithResource(applicationContext, R.drawable.ic_noti_action_cancel)
//            }
//        }
//        qsTile.icon = icon//设置图标
//        qsTile.updateTile()//更新Tile
//    }
//
//    private fun cancelNotification() {
//        if (NotificationService.isStart) {
//            NotificationService.sendAction(
//                applicationContext,
//                NotificationService.ACTION_SERVICE_NOTI_HIDE
//            )
//        }
//    }
//
//    private fun startOrShowNotification() {
//        if (!NotificationService.isStart) {
//            NotificationService.startService(applicationContext)
//        } else {
//            if (!NotificationService.isShowed) {
//                NotificationService.sendAction(
//                    applicationContext,
//                    NotificationService.ACTION_SERVICE_NOTI_SHOW
//                )
//            }
//        }
//
//    }
//
//    // 打开下拉菜单的时候调用,当快速设置按钮并没有在编辑栏拖到设置栏中不会调用
//    //在TleAdded之后会调用一次
//    override fun onStartListening() {
//        super.onStartListening()
//        log("onStartListening", TAG)
//    }
//
//    // 关闭下拉菜单的时候调用,当快速设置按钮并没有在编辑栏拖到设置栏中不会调用
//    // 在onTileRemoved移除之前也会调用移除
//    override fun onStopListening() {
//        super.onStopListening()
//        log("onStopListening", TAG)
//
//    }
//}
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
        Toast.makeText(this, "淦！", Toast.LENGTH_SHORT).show()
//        InfraredMethod().switch()
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

    //设置磁贴颜色
    fun setQuickSettingColor() {
        //更改成非活跃状态(灰色)
        qsTile.state = Tile.STATE_ACTIVE
        //更改成活跃状态(白色)
        //getQsTile().setState(Tile.STATE_INACTIVE);
        qsTile.updateTile() //更新Tile
    }
}