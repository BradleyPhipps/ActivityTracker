package com.example.activitytracker.ui.main.myactivities

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.activitytracker.R
import com.example.activitytracker.models.ActivityCoreData
import com.example.activitytracker.services.ActivityCardBuilder
import com.squareup.picasso.Picasso

class ActivityAdapter (
    private val activityList: List<ActivityCoreData>
    ): RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder>() {

    override fun getItemCount(): Int {
        return activityList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.activity_card, parent, false)

        return ActivityViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {

        val currentActivity = activityList[position]

        ActivityCardBuilder(holder.itemView).buildCard(currentActivity)

    }

    inner class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var activityTitle: TextView = itemView.findViewById(R.id.activityTitle)
        var activityFollowButton: Button = itemView.findViewById(R.id.saveActivity)
        var activityImage: ImageView = itemView.findViewById(R.id.activityImage)
        var activityPrice: TextView = itemView.findViewById(R.id.activityPrice)

    }

}