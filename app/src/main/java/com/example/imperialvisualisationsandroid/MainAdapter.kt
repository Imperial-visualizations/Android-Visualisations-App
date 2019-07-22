package com.example.imperialvisualisationsandroid

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.visualisation_cell.view.*

class MainAdapter(val data: DataModel) : RecyclerView.Adapter<viewHolder>() {

    val visualisations = data.Visualisations

    override fun getItemCount(): Int {
        return visualisations.count()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): viewHolder {
        val layoutInflater = LayoutInflater.from(p0.context)
        val cellForRow = layoutInflater.inflate(R.layout.visualisation_cell, p0, false)
        return viewHolder(cellForRow)
    }


    override fun onBindViewHolder(p0: viewHolder, p1: Int) {

    }

    override fun onBindViewHolder(holder: viewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)

        holder.itemView.setBackgroundColor(Color.WHITE)

        holder.view.titleTextView.text = visualisations[position].name
        holder.view.infoTextView.text = visualisations[position].info

        //PICASSO
        //Picasso.get().load(visualisations[position].imageURL).into(holder.view.VisualisationImageView)


        //GLIDE
        val context = holder.view.context
        Glide.with(context)
            .load(visualisations[position].imageURL)
            .into(holder.view.VisualisationImageView)


    }
}

class viewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    init {
        view.setOnClickListener {
            val intent = Intent(view.context, VisualisationDetailActivity::class.java)
            view.context.startActivity(intent)
        }

//        view.setOnLongClickListener {
//            val intent = Intent(view.context, GIFDetailActivity::class.java)
//            view.context.startActivity(intent)
//        }
    }

}