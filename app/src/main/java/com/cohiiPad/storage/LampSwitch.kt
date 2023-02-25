package com.cohiiPad.storage

import android.content.Context
import android.hardware.ConsumerIrManager
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService

object LampSwitch{

        private val switchIRCode = intArrayOf( // The pattern of the infrared signal
            // On, off, on, off, on, off, on, off, on
            // (Each number represents a duration in microseconds)
            9059, 4543, 526, 636, 498, 1742, 584, 1708, 614,
            522, 588, 562, 510, 628, 518, 644, 490, 621, 528,
            1740, 651, 1618, 575, 573, 531, 639, 579, 586, 489,
            1739, 615, 533, 583, 1710, 617, 1647, 562, 1691,
            576, 1691, 604, 544, 530, 1733, 620, 529, 618, 575,
            506, 611, 562, 595, 620, 523, 552, 616, 567, 1658,
            531, 652, 493, 1733, 649, 1621, 532, 1738, 558,
            41719, 9072, 2281, 585, 9280
        )

        private val lighterIRCode = intArrayOf(
            9113, 4486, 658, 479, 658, 1602, 667, 1603, 664, 475,
            654, 523, 632, 513, 632, 483, 662, 515, 634, 1595, 653,
            1609, 664, 514, 634, 481, 651, 512, 634, 1604, 618, 528,
            570, 1692, 665, 1605, 665, 1605, 663, 482, 665, 481, 665,
            1604, 665, 482, 662, 516, 633, 513, 633, 483, 664, 505,
            633, 1605, 665, 1605, 664, 476, 663, 1604, 665, 1605,
            662, 1604, 659, 9280
        )

        private val dimmerIRCode = intArrayOf(
            9071, 4517, 636, 506, 576, 1691, 720, 1548, 642, 507,
            577, 570, 577, 568, 577, 569, 576, 563, 576, 1692, 641,
            1628, 640, 508, 576, 570, 577, 569, 576, 1690, 641, 508,
            576, 1690, 757, 392, 576, 1690, 643, 507, 575, 571, 577,
            570, 575, 571, 576, 563, 576, 569, 570, 1692, 641, 507,
            689, 1575, 636, 1628, 577, 1682, 643, 1627, 579, 1682,
            805, 1466, 570, 9280
        )
        private lateinit var irManager: ConsumerIrManager
        fun switch(context: Context) {
            if (!::irManager.isInitialized) {
                irManager = context.getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager
            }
            irManager.transmit(38000, switchIRCode)
//            TileServiceActivity.setTileStatus()
        }
        fun lighter(context: Context) {
            if (!::irManager.isInitialized) {
                irManager = context.getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager
            }
            irManager.transmit(38000, lighterIRCode)
        }
        fun dimmer(context: Context) {
            if (!::irManager.isInitialized) {
                irManager = context.getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager
            }
            irManager.transmit(38000, dimmerIRCode)
        }
}

//class UpdateTileBroadcastReceiver : BroadcastReceiver() {
//    companion object {
//        const val ACTION_UPDATE_TILE = "com.example.myapp.UPDATE_TILE"
//    }
//
//    override fun onReceive(context: Context?, intent: Intent?) {
//        if (intent?.action == ACTION_UPDATE_TILE) {
//            // 更新磁贴状态
//            // ...
//        }
//
//    }
//}

