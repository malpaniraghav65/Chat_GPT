package com.example.chatgpt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatgpt.adapter.adapter
import com.example.chatgpt.api.ApiUtilities
import com.example.chatgpt.databinding.ActivityImageGenerationBinding
import com.example.chatgpt.models.request.Chatrequest
import com.example.chatgpt.models.request.ImagegenerateRequest
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.RequestBody
import java.lang.Exception

class ImageGenerationActivity : AppCompatActivity() {
    var list = ArrayList<MessageModel>()
    private lateinit var mLayoutManager : LinearLayoutManager
    private lateinit var adapter: adapter
    private lateinit var binding: ActivityImageGenerationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageGenerationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backBtn.setOnClickListener{finish()}
        mLayoutManager = LinearLayoutManager(this)
        mLayoutManager.stackFromEnd = true
        adapter = adapter(list)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = mLayoutManager


        binding.sendbtn.setOnClickListener{
            if(binding.userMsg.text!!.isEmpty()){
                Toast.makeText(this,"Input Field is Empty", Toast.LENGTH_SHORT).show()
            }else{
                callApi()
            }
        }
    }

    private fun callApi() {

        list.add(MessageModel(true,false,binding.userMsg.text.toString()))
        adapter.notifyItemInserted(list.size-1)
        binding.recyclerView.recycledViewPool.clear()
        binding.recyclerView.smoothScrollToPosition(list.size-1)
        val apiInterface = ApiUtilities.getApiInterface()
        val requestBody = RequestBody.create(
            MediaType.parse("application/json"),
            Gson().toJson(
                ImagegenerateRequest(
                    "1024x1024",
                    binding.userMsg.text.toString(),
                    2

                    )
            )
        )
        val contentType = "application/json"
        val authorization = "Bearer ${utilis.API_KEY}"
        lifecycleScope.launch ( Dispatchers.IO ){
            try {
                val response = apiInterface.generateImage(
                    contentType,authorization,requestBody
                )
                val textResponse= response.data!!.first().url
                list.add(MessageModel(false,false,textResponse))
                withContext(Dispatchers.Main){
                    adapter.notifyItemInserted(list.size-1)
                    binding.recyclerView.recycledViewPool.clear()
                    binding.recyclerView.smoothScrollToPosition(list.size-1)
                }
                binding.userMsg.text!!.clear()
            }
            catch (e: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@ImageGenerationActivity,e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}