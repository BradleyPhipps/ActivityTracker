package com.example.activitytracker.services.activity

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.activitytracker.R
import com.example.activitytracker.models.ActivityCoreData

class ActivityCardBuilder(private val cardView: View) {

    fun buildCard(activityData: ActivityCoreData){
        setTitleText(activityData.activityTitle)
        setPriceText(cardView.findViewById(R.id.activityPrice), activityData.activityPrice)
        setImageBanner(cardView.findViewById(R.id.activityImage), activityData.activityType, activityData.activityTitle)
        setFollowButtonText(cardView.findViewById(R.id.saveActivity), activityData.activityFollowed)

    }

    fun setCardListener(onFollowButtonClickListener: ()-> Unit){
        setOnFollowButtonClickListener(cardView.findViewById(R.id.saveActivity), onFollowButtonClickListener)
    }

    private fun setTitleText(titleText: String){
        cardView.findViewById<TextView>(R.id.activityTitle).text = titleText
    }

    private fun setFollowButtonText(followButton: Button, followed: Boolean){
        when(followed){
            true -> followButton.text = followButton.context.getString(R.string.card_unfollowText)
            false -> followButton.text = followButton.context.getString(R.string.card_followText)
        }
    }

    private fun setPriceText(priceTextView: TextView, priceValue: Float){
        when(priceValue){
            0f -> priceTextView.text = "FREE"
            in 0.01f..0.3f -> priceTextView.text = "£"
            in 0.31f..0.6f -> priceTextView.text = "££"
            in 0.61f..1f -> priceTextView.text = "£££"
        }
    }

    private fun setImageBanner(imageBanner: ImageView, activityType: String, contentDescription: String){
        imageBanner.setImageResource(getImageBanner(activityType))
        imageBanner.scaleType = ImageView.ScaleType.CENTER_CROP
        imageBanner.contentDescription = contentDescription
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
    private fun setOnFollowButtonClickListener(followButton: Button,  onClickListener: ()-> Unit){
        followButton.setOnClickListener {onClickListener()}
    }
}