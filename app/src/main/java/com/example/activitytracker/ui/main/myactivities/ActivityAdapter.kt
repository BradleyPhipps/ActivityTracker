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

        var currentActivity = activityList[position]

        holder.activityTitle.text = currentActivity.activityTitle
        holder.activityCategory.text = currentActivity.activityType
        holder.activityFollowButton.text = "Unfollow Activity"
        holder.activityImage.setImageResource(getImageBanner(currentActivity.activityType))
        holder.activityImage.scaleType=ImageView.ScaleType.CENTER_CROP

        when(currentActivity.activityType){
            "education" -> holder.activityImage.setImageResource(R.drawable.banner_education)
            "recreational" -> holder.activityImage.setImageResource(R.drawable.banner_recreational)
            "cooking" -> holder.activityImage.setImageResource(R.drawable.banner_cooking)
            "charity"-> holder.activityImage.setImageResource(R.drawable.banner_charity)
            "music"-> holder.activityImage.setImageResource(R.drawable.banner_music)
            "diy"-> holder.activityImage.setImageResource(R.drawable.banner_diy)
            "busywork"-> holder.activityImage.setImageResource(R.drawable.banner_busywork)
            "social"-> holder.activityImage.setImageResource(R.drawable.banner_social)
            "relaxation"-> holder.activityImage.setImageResource(R.drawable.banner_relaxation)
        }
        //Picasso.get().load(articleData.data.items[position].image.small).into(holder.articleImage)
        //holder.articleImage.contentDescription = articleData.data.items[position].image.altText
    }

    private fun getImageBanner(typeString: String): Int{
        return when(typeString){
            "education" -> R.drawable.banner_education
            "recreational" -> R.drawable.banner_recreational
            "cooking" -> R.drawable.banner_cooking
            "charity"-> R.drawable.banner_charity
            "music"-> R.drawable.banner_music
            "diy"-> R.drawable.banner_diy
            "busywork"-> R.drawable.banner_busywork
            "social"-> R.drawable.banner_social
            "relaxation"-> R.drawable.banner_relaxation
            else -> R.drawable.banner_recreational
        }
    }




    inner class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var activityTitle: TextView = itemView.findViewById(R.id.activityTitle)
        var activityCategory: TextView = itemView.findViewById(R.id.activityCategory)
        var activityFollowButton: Button = itemView.findViewById(R.id.saveActivity)
        var activityImage: ImageView = itemView.findViewById(R.id.activityImage)

       // var activityPrice: ImageView = itemView.findViewById(R.id.acti)

    }
}