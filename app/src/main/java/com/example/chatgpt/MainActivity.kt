package com.example.chatgpt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.chatgpt.databinding.ActivityChatBinding
import com.example.chatgpt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.generateimage.setOnClickListener{
            startActivity(Intent(this,ImageGenerationActivity::class.java))
        }
        binding.generatetext.setOnClickListener{
            startActivity(Intent(this,ChatActivity::class.java))
        }
    }
}