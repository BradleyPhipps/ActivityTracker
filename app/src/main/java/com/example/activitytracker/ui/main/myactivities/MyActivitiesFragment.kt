package com.example.activitytracker.ui.main.myactivities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.activitytracker.R
import com.example.activitytracker.ViewModelFactory
import com.example.activitytracker.databinding.MyActivitiesFragmentBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MyActivitiesFragment : Fragment() {

    companion object {
        fun newInstance() = MyActivitiesFragment()
    }

    private lateinit var viewModel: MyActivitiesViewModel
    private lateinit var controller: MyActivitiesViewController
    private lateinit var myActivitiesView: MyActivitiesView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.my_activities_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = MyActivitiesFragmentBinding.bind(view)
        viewModel = ViewModelFactory(view.context).create(MyActivitiesViewModel::class.java)
        myActivitiesView = MyActivitiesView(binding)
        controller = MyActivitiesViewController(myActivitiesView,viewModel)
        controller.onViewReady()

    }

}