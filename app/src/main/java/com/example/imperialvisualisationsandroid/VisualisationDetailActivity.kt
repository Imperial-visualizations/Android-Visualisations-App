package com.example.imperialvisualisationsandroid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.r0adkll.slidr.Slidr

class VisualisationDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.visualisation_activity_detail)

        Slidr.attach(this) //back swipe functionality from Slidr

    }
}