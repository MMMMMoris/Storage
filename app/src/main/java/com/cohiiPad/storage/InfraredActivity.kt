package com.cohiiPad.storage

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.BitmapFactory
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.cohiiPad.storage.databinding.ActivityInfraredBinding
import com.cohiiPad.storage.test.ServiceTest
import kotlin.concurrent.thread

class InfraredActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInfraredBinding

    lateinit var downloadBinder: ServiceTest.DownloadBinder
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            downloadBinder = service as ServiceTest.DownloadBinder
            downloadBinder.startDownload()
            downloadBinder.getProgress()
        }

        override fun onServiceDisconnected(name: ComponentName) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityInfraredBinding.inflate(layoutInflater)
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
                    "spy", "小红书侦听", NotificationManager.IMPORTANCE_HIGH
                    // NotificationManager.IMPORTANCE_DEFAULT 表示一般重要，不会显示横幅
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
        val updateText = 1
        val handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) { // 子线程修改 UI，异步消息处理，这样才不会引发崩溃
                when (msg.what) { // 在这里可以进行UI操作
                    //发现Message的what字段的值等于 updateText，就将TextView显示的内容改 成“Nice to meet you”
                    updateText -> binding.textView.text = "Nice to meet you"
                    // 不能够将这个 handler 移动到 onCreate 函数外面，因为 onCreate 之后，binding 才会被初始化
                }
            }
        }
        binding.changeTextBtn.setOnClickListener {
            thread {
                val msg = Message()
                msg.what = updateText
                handler.sendMessage(msg)
                // binding.textView.text = "Nice to meet you!" // 直接修改会导致崩溃
            }
        }



        binding.testButton.setOnClickListener {
            val intent = Intent(this, ServiceTest::class.java)
            startService(intent) // 启动Service
//            val intent = Intent(this, MqttService::class.java)
//            stopService(intent) // 停止Service
        }
        binding.bindServiceButton.setOnClickListener {
            val intent = Intent(this, ServiceTest::class.java)
            bindService(intent, connection, Context.BIND_AUTO_CREATE) // 绑定Service
        }
        binding.unbindServiceButton.setOnClickListener {
            unbindService(connection) // 解绑Service
        }
    }
}