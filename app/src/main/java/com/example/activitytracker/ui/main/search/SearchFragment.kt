package com.example.activitytracker.ui.main.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.activitytracker.R
import com.example.activitytracker.ViewModelFactory
import com.example.activitytracker.databinding.HomeFragmentBinding
import com.example.activitytracker.databinding.SearchFragmentBinding
import com.example.activitytracker.ui.main.home.HomeView
import com.example.activitytracker.ui.main.home.HomeViewController
import com.example.activitytracker.ui.main.home.HomeViewModel
import com.example.activitytracker.ui.main.myactivities.MyActivitiesView
import com.example.activitytracker.ui.main.myactivities.MyActivitiesViewController
import com.example.activitytracker.ui.main.myactivities.MyActivitiesViewModel

class SearchFragment : Fragment(R.layout.search_fragment) {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var searchView: SearchView
    private lateinit var viewModel: SearchViewModel
    private lateinit var controller: SearchViewController
    private lateinit var myActivitiesView: SearchView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val binding = SearchFragmentBinding.bind(view)
        viewModel = ViewModelFactory(view.context, findNavController()).create(SearchViewModel::class.java)
        searchView = SearchView(binding)
        controller = SearchViewController(searchView,viewModel)
        controller.onViewReady()
    }

}