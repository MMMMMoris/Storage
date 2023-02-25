package com.cohiiPad.storage

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cohiiPad.storage.databinding.ActivityInfraredBinding

class InfraredActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInfraredBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfraredBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.finishButton.setOnClickListener{
            finish()
        }
        binding.urlButton.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://cohii.com")
            startActivity(intent)
        }

    }
}