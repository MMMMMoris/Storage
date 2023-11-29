package com.cohiiPad.storage

import android.app.NotificationChannel
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cohiiPad.storage.databinding.ActivityInfraredBinding
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat

class InfraredActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInfraredBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfraredBinding.inflate(layoutInflater)
        val view = binding.root
        val context: Context = this
        setContentView(view)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        binding.finishButton.setOnClickListener {
            finish()
        }
        binding.urlButton.setOnClickListener {
//            val intent = Intent(Intent.ACTION_VIEW)
//            intent.data = Uri.parse("https://cohii.com")
//            startActivity(intent)
            // 后面可以用这个部分直接 打开链接，不要删除这些
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    "spy", "小红书侦听", NotificationManager.IMPORTANCE_DEFAULT
                )
                manager.createNotificationChannel(channel)
            }
            val intent = Intent(this, InfraredActivity::class.java)
            // 表示本 intent，点击消息就打开这个 Activity
            val pi =
                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE)
            val notification = NotificationCompat.Builder(this, "spy")
                .setContentTitle("来自 Galileo").setContentText("她的小红书更新了")
                .setSmallIcon(R.drawable.storage2)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.storage2))
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build()
            manager.notify(1, notification)
        }
    }
}