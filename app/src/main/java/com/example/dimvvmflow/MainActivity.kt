package com.example.dimvvmflow

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dimvvmflow.model.ProcessedData
import com.example.dimvvmflow.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var adapter: PlayerListAdapter
    var recyclerView: RecyclerView? = null
    var progress: ProgressBar? = null

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progress = findViewById(R.id.progress_circular)
        setUpUI()
        val UserModel: UserViewModel by viewModels()
        lifecycleScope.launch() {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                UserModel.userListSTate.collect { processedData ->
                    when (processedData) {
                        is ProcessedData.Success -> {
                            adapter.setUpData(processedData.listUser)
                            adapter.notifyDataSetChanged()
                            progress!!.visibility = View.GONE
                            println("User Data processed : ${processedData.listUser.size}")
                        }
                        is ProcessedData.Error -> {
                            progress!!.visibility = View.GONE
                            println("User Data Error :${processedData.errorMsg}")
                        }
                        is ProcessedData.Loading -> {
                            progress!!.visibility = View.VISIBLE
                            println("User Data Loading : Loading....")
                        }
                    }
                }
            }
        }
    }

    fun setUpUI() {
        recyclerView = findViewById<RecyclerView>(R.id.recylerview)
        val linearLayoutManager = LinearLayoutManager(this)
        adapter.setUpData(mutableListOf())
        recyclerView!!.layoutManager = linearLayoutManager
        recyclerView!!.adapter = adapter
    }

}