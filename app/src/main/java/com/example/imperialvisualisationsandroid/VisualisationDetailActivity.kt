package com.example.imperialvisualisationsandroid

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.r0adkll.slidr.Slidr
import com.r0adkll.slidr.model.SlidrConfig
import kotlinx.android.synthetic.main.visualisation_activity_detail.*

class VisualisationDetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        //this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

        val config = SlidrConfig.Builder()
            .edge(true)
            .edgeSize(0.1f)
            .build()

        super.onCreate(savedInstanceState)

        setContentView(R.layout.visualisation_activity_detail)

        Slidr.attach(this, config) //back swipe functionality from Slidr

        val SelectedVisualisationTitle = intent.getStringExtra(ViewHolder.SelectedVisualisationTitle)
        val SelectedVisualisationWebURL = intent.getStringExtra(ViewHolder.SelectedVisualisationWebURL)

        supportActionBar?.title = SelectedVisualisationTitle

        VisualisationWebView.settings.loadWithOverviewMode = true
        VisualisationWebView.settings.useWideViewPort = true
        VisualisationWebView.settings.lightTouchEnabled = true
        VisualisationWebView.settings.javaScriptEnabled = true

        VisualisationWebView.loadUrl(SelectedVisualisationWebURL)

    }


    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
    }
}