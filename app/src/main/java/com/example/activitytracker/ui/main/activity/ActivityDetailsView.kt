package com.example.activitytracker.ui.main.activity

import android.view.View
import android.widget.SeekBar
import com.example.activitytracker.R
import com.example.activitytracker.databinding.ActivityDetailsFragmentBinding
import com.example.activitytracker.databinding.MyActivitiesFragmentBinding

class ActivityDetailsView(private val view: ActivityDetailsFragmentBinding) {


    fun setFollowButtonOnClickListener(listener: ()-> Unit){
        view.activityDetailsFollowButton.setOnClickListener{
            listener.invoke()
        }
    }

    fun setProgressOnChangeListener(listener:  (Int?) -> Unit){
        view.activityDetailsProgressSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                //onProgressChanged
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //onStartTrackingTouch
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                listener.invoke(seekBar?.progress)
            }
        })
    }

    fun setActivityProgress(progress: Int?){
        if(progress != null){
            view.activityDetailsProgressSlider.progress = progress
        }
    }

    fun setActivityTitle(title: String){
        view.activityDetailsTitle.text = title
    }
    fun setActivityPrice(price: Float){
        view.activityDetailsPrice.text = convertPriceText(price)
    }
    fun setActivityAccessibility(accessibility: Float){
        view.activityDetailsAccessibility.text = convertAccessibilityText(accessibility)
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
    fun setLinkText(link: String){
        view.activityDetailsLink.text = link
    }

    fun displayLinkItems(){
        view.activityDetailsLinkHeader.visibility = View.VISIBLE
        view.activityDetailsLinkCard.visibility = View.VISIBLE
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

    private fun convertAccessibilityText(accessibility: Float): String{
        return when(accessibility){
            in 0f..0.1f -> "Very Easy"
            in 0.11f..0.3f ->  "Easy"
            in 0.31f..0.8f ->"Difficult"
            in 0.81f..1f ->  "Very Difficult"
            else -> "Accessible"
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