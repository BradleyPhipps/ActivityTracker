package com.example.activitytracker.ui.main.myactivities

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.activitytracker.R
import com.example.activitytracker.models.ActivityCoreData
import com.example.activitytracker.services.ActivityCardBuilder
import com.squareup.picasso.Picasso

class ActivityAdapter (
    private val activityList: List<ActivityCoreData>,
    private val activitySelectedListener:  SelectedItemListener,
    private val activityFollowButtonClickListener: ItemFollowButtonClickListener
    ): RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder>() {

    override fun getItemCount(): Int {
        return activityList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.activity_card, parent, false)

        return ActivityViewHolder(cellForRow) { listPosition ->
            activitySelectedListener(activityList[listPosition])
        }
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        val currentActivity = activityList[position]
        //TODO: this is having to find the id every call - change
        with(ActivityCardBuilder(holder.itemView)){
            buildCard(currentActivity)
            holder.itemView.findViewById<Button>(R.id.saveActivity).setOnClickListener { activityFollowButtonClickListener(currentActivity) }
        }

    }

    inner class ActivityViewHolder(itemView: View, onItemClicked : (Int) -> Unit): RecyclerView.ViewHolder(itemView){
        init {
               itemView.setOnClickListener {
                   onItemClicked(adapterPosition)
               }
            }
    }


}