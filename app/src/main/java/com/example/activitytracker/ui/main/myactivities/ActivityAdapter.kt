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
    private val activitySelectedListener: OnItemClickListener
    ): RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder>() {

    private lateinit var onClickListener: OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener){
        onClickListener = listener
    }

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

        //TODO: this is having to find the id every call - change
        ActivityCardBuilder(holder.itemView).buildCard(currentActivity)
    }

    inner class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener{
        init {
                itemView.setOnClickListener(this)
            }

        override fun onClick(view: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION) {
                activitySelectedListener.onActivityCardClicked(position)
             }
        }
    }

    interface OnItemClickListener {
        fun onActivityCardClicked(position: Int)
    }

}