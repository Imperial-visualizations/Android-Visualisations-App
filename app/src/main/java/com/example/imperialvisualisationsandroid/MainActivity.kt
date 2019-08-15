package com.example.imperialvisualisationsandroid

import android.app.ActionBar
import android.app.SearchManager
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.widget.Filterable
import com.google.gson.GsonBuilder
import com.klinker.android.peekview.PeekViewActivity
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

public class MainActivity : AppCompatActivity() {

    // data variable - changed only once during data decode
    var data: DataModel = DataModel(Visualisations = emptyList())

    private var searchView: SearchView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTitle("Visualisations")

        setContentView(R.layout.activity_main)

        recyclerView_main.layoutManager = LinearLayoutManager(this)

        decodeJSON()

    }

    //TABLE SETUP



    fun decodeJSON() {

        //var data: DataModel?

        val jsonURL = "https://raw.githubusercontent.com/VedantVarshney/VisualisationsPersonal/master/DataModel"

        val request = Request.Builder().url(jsonURL).build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {

            override fun onResponse(call: Call, response: Response) {
                val jsonBody = response?.body?.string()
                //println(jsonBody)

                val gson = GsonBuilder().create()

                //class.java??
                data = gson.fromJson(jsonBody, DataModel::class.java)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)


        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        // ???
        searchView = menu?.findItem(R.id.search)?.actionView as SearchView

        searchView!!.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        searchView!!.maxWidth = Integer.MAX_VALUE

        searchView!!.setOnQueryTextListener(object: SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                //MainAdapter(data).filter.filter(query)

                (recyclerView_main.adapter as Filterable).filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //MainAdapter(data).filter.filter(newText)

                (recyclerView_main.adapter as Filterable).filter.filter(newText)
                return false
            }
        })

        return true

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item?.itemId
        return if (id == R.id.search){
            true
        } else super.onOptionsItemSelected(item)
    }

}
