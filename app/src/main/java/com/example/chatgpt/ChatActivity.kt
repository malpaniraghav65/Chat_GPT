package com.example.chatgpt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Adapter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatgpt.adapter.adapter
import com.example.chatgpt.api.ApiInterface
import com.example.chatgpt.api.ApiUtilities
import com.example.chatgpt.databinding.ActivityChatBinding
import com.example.chatgpt.models.request.Chatrequest
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.RequestBody
import java.lang.Exception

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    var list = ArrayList<MessageModel>()
    private lateinit var mLayoutManager : LinearLayoutManager
    private lateinit var adapter: adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backBtn.setOnClickListener{finish()}
        mLayoutManager = LinearLayoutManager(this)
        mLayoutManager.stackFromEnd = true
        adapter = adapter(list)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = mLayoutManager


        binding.sendbtn.setOnClickListener{
            if(binding.userMsg.text!!.isEmpty()){
              Toast.makeText(this,"Input Field is Empty",Toast.LENGTH_SHORT).show()
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
        val requestBody = RequestBody.create(MediaType.parse("application/json"),
        Gson().toJson(
            Chatrequest(
                    250,
                0.7,
                     "text-davinci-003",
                     binding.userMsg.text.toString(),

            ))
            )
        val contentType = "application/json"
        val authorization = "Bearer ${utilis.API_KEY}"
        lifecycleScope.launch ( Dispatchers.IO ){
            try {
            val response = apiInterface.getChat(
                contentType,authorization,requestBody
            )
            val textResponse= response.choices!!.first().text.toString()
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
               Toast.makeText(this@ChatActivity,e.message,Toast.LENGTH_SHORT).show()
            }}
        }
    }
}