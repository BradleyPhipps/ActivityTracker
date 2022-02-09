package com.example.activitytracker.ui.main.activity

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.activitytracker.R

class ActivityDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = ActivityDetailsFragment()
    }

    private lateinit var viewModel: ActivityDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ActivityDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}