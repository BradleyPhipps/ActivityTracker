package com.example.activitytracker.ui.main.activity

import com.example.activitytracker.R
import com.example.activitytracker.databinding.ActivityDetailsFragmentBinding
import com.example.activitytracker.databinding.MyActivitiesFragmentBinding

class ActivityDetailsView(private val view: ActivityDetailsFragmentBinding) {


    fun setActivityTitle(title: String){
        view.activityDetailsTitle.text = title
    }
    fun setActivityPrice(price: Float){
        view.activityDetailsPrice.text = convertPriceText(price)
    }
    fun setActivityAccessibility(accessibility: String){
        view.activityDetailsAccessibility.text = accessibility
    }
    fun setActivityParticipants(participants: String){
        view.activityDetailsParticipants.text = participants
    }

    fun setActivityBannerImage(type:String){
       view.activityDetailsBannerImage.setImageResource(getImageBanner(type))
    }

    fun setFollowButtonText(followingActivity: Boolean){
        when(followingActivity){
            true -> view.activityDetailsFollowButton.setText(R.string.card_unfollowText)
            false -> view.activityDetailsFollowButton.setText(R.string.card_followText)
        }
    }

    private fun convertPriceText(price: Float): String{
           return when(price){
                0f -> "FREE"
                in 0.01f..0.3f ->  "£"
                in 0.31f..0.6f ->"££"
                in 0.61f..1f ->  "£££"
               else -> "FREE"
           }
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


}