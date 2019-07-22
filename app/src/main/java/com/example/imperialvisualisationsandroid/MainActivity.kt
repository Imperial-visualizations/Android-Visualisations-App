package com.example.imperialvisualisationsandroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        recyclerView_main.layoutManager = LinearLayoutManager(this)

        decodeJSON()

    }

    //TABLE SETUP



    fun decodeJSON() {

        val jsonURL = "https://raw.githubusercontent.com/VedantVarshney/VisualisationsPersonal/master/DataModel"

        val request = Request.Builder().url(jsonURL).build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {

            override fun onResponse(call: Call, response: Response) {
                val jsonBody = response?.body?.string()
                //println(jsonBody)

                val gson = GsonBuilder().create()

                //class.java??
                val data = gson.fromJson(jsonBody, DataModel::class.java)
                println("JSON FETCH SUCCESSFUL")

                //cannot return data here as return needs to be unit (cannot specify model to DataModel)
                //Have to update UI on UI thread
                runOnUiThread {
                    recyclerView_main.adapter = MainAdapter(data)
                }

            }

            override fun onFailure(call: Call, e: IOException) {
                println("JSON FETCH FAILED")
                println(e)
            }
        })
    }
}
