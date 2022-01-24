package com.example.activitytracker.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.activitytracker.R
import com.example.activitytracker.ViewModelFactory
import com.example.activitytracker.databinding.HomeFragmentBinding

class HomeFragment : Fragment(R.layout.main_activity) {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var controller: HomeViewController
    private lateinit var homeView: HomeView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = HomeFragmentBinding.bind(view)
        viewModel = ViewModelFactory().create(HomeViewModel::class.java)
        homeView = HomeView(binding)
        controller = HomeViewController(homeView,viewModel)
        controller.onViewReady()
    }

}