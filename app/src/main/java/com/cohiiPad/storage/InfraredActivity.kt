package com.cohiiPad.storage

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class InfraredActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        supportActionBar?.apply {
//            title = "My Activity Title"
//            setBackgroundDrawable(ColorDrawable(Color.parseColor("#FFFFFF")))
//            setDisplayHomeAsUpEnabled(true)
//        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infrared)
    }
}