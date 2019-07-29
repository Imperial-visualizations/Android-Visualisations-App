package com.example.imperialvisualisationsandroid

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.visualisation_cell.view.*

class MainAdapter(val data: DataModel) : RecyclerView.Adapter<ViewHolder>(), Filterable {

    val visualisations = data.Visualisations

    var searchVisualisations = data.Visualisations.toMutableList()

    override fun getItemCount(): Int {
        return searchVisualisations.count()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(p0.context)
        val cellForRow = layoutInflater.inflate(R.layout.visualisation_cell, p0, false)
        return ViewHolder(cellForRow)
    }


    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)

        holder.itemView.setBackgroundColor(Color.WHITE)

        val selectedVisualisation = searchVisualisations.get(position)

        holder.view.titleTextView.text = searchVisualisations[position].name
        holder.view.infoTextView.text = searchVisualisations[position].info

        //PICASSO
        //Picasso.get().load(visualisations[position].imageURL).into(holder.view.VisualisationImageView)


        //GLIDE
        val context = holder.view.context
        Glide.with(context)
            .load(searchVisualisations[position].imageURL)
            .into(holder.view.VisualisationImageView)

        holder.selectedVisualisation = selectedVisualisation

    }


    override fun getFilter(): Filter {
        return object: Filter() {

            override fun performFiltering(p0: CharSequence?): FilterResults {
                val searchQuery = p0.toString().toLowerCase()

                var filteredVisualisations = ArrayList<Visualisation>()

                if (searchQuery.isEmpty()) {
                    filteredVisualisations.addAll(visualisations)
                } else {
                    for (vis in visualisations) {

                        var visString = (vis.name + " " + vis.info + " " + vis.tags).toLowerCase()

                        if (visString.contains(searchQuery)) {
                            filteredVisualisations.add(vis)
                        }
                    }

//                    searchVisualisations.clear()
//                    searchVisualisations.addAll(filteredVisualisations)

                }

                val filterResults = FilterResults()
                filterResults.count = filteredVisualisations.size
                filterResults.values = filteredVisualisations
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                searchVisualisations = p1?.values as ArrayList<Visualisation>
                notifyDataSetChanged()
            }
        }
    }
}

class ViewHolder(val view: View, var selectedVisualisation: Visualisation? = null) : RecyclerView.ViewHolder(view) {

    companion object {
        val SelectedVisualisationTitle = "SelectedVisualisationTitle"
        val SelectedVisualisationWebURL = "SelectedVisualisationWebURL"
    }

    init {
        view.setOnClickListener {
            val intent = Intent(view.context, VisualisationDetailActivity::class.java)

            intent.putExtra(SelectedVisualisationTitle, selectedVisualisation?.name)
            intent.putExtra(SelectedVisualisationWebURL, selectedVisualisation?.url_name)

            view.context.startActivity(intent)
        }

//        view.setOnLongClickListener {
////            val intent = Intent(view.context, GIFDetailActivity::class.java)
////            view.context.startActivity(intent)
////        }
    }

}

