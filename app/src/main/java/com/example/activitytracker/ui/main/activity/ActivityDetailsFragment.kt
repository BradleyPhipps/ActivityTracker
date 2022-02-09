package com.example.activitytracker.ui.main.activity

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.activitytracker.R
import com.example.activitytracker.ViewModelFactory
import com.example.activitytracker.databinding.ActivityDetailsFragmentBinding
import com.example.activitytracker.databinding.MyActivitiesFragmentBinding
import com.example.activitytracker.ui.main.myactivities.MyActivitiesFragment
import com.example.activitytracker.ui.main.myactivities.MyActivitiesView
import com.example.activitytracker.ui.main.myactivities.MyActivitiesViewController
import com.example.activitytracker.ui.main.myactivities.MyActivitiesViewModel

class ActivityDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = MyActivitiesFragment()
    }

    private lateinit var viewModel: ActivityDetailsViewModel
    private lateinit var controller: ActivityDetailsViewController
    private lateinit var activityDetailsView: ActivityDetailsView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.my_activities_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = ActivityDetailsFragmentBinding.bind(view)
        viewModel = ViewModelFactory(view.context).create(ActivityDetailsViewModel::class.java)
        activityDetailsView =ActivityDetailsView(binding)
        controller = ActivityDetailsViewController(activityDetailsView,viewModel)
        controller.onViewReady()

    }

}