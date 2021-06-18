package com.example.xometestassignmentapp

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xometestassignmentapp.adapter.PhotosAdapter
import com.example.xometestassignmentapp.viewmodel.PhotosViewModel
import com.example.xometestassignmentapp.viewmodel.PhotosViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var photosViewModel: PhotosViewModel
    private val photosAdapter = PhotosAdapter()
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val searchView: EditText = findViewById(R.id.et_search_text)
        val buttonSearch: Button = findViewById(R.id.btn_search)
        recyclerView = findViewById(R.id.rv_search_item)

        buttonSearch.setOnClickListener{
            if (checkNetwork()){
                searchImage(searchView.text.toString())
            }else if (!checkNetwork()) {
                Toast.makeText(this, "Network connection is not available", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun searchImage(searchText : String){
        photosViewModel = ViewModelProviders.of(this, PhotosViewModelFactory(searchText)).get(
            PhotosViewModel::class.java)
        recyclerView.adapter = photosAdapter
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, true)

        photosViewModel.photosLiveData.observe(this,
            Observer { list ->
                with(photosAdapter) {
                    photos.clear()
                    photos.addAll(list)
                    notifyDataSetChanged()
                }
            })
    }

    private fun checkNetwork(): Boolean {
        val connManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        Log.d("Network type is ",""+networkInfo)
        return true
    }
}