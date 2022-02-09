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

        holder.activityTitle.text = activityList[position].activityTitle
        holder.activityCategory.text = activityList[position].activityType
        holder.activityFollowButton.text = "Unfollow Activity"
        holder.activityImage.setImageResource(R.drawable.banner_education)
        holder.activityImage.scaleType=ImageView.ScaleType.CENTER_CROP
        //Picasso.get().load(articleData.data.items[position].image.small).into(holder.articleImage)
        //holder.articleImage.contentDescription = articleData.data.items[position].image.altText
    }


    inner class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var activityTitle: TextView = itemView.findViewById(R.id.activityTitle)
        var activityCategory: TextView = itemView.findViewById(R.id.activityCategory)
        var activityFollowButton: Button = itemView.findViewById(R.id.saveActivity)
        var activityImage: ImageView = itemView.findViewById(R.id.activityImage)

       // var activityPrice: ImageView = itemView.findViewById(R.id.acti)

    }
}