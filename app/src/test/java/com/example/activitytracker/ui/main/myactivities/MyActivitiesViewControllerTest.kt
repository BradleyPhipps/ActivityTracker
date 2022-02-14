package com.example.activitytracker.ui.main.home

import com.example.MainCoroutineRule
import com.example.activitytracker.models.ActivityCoreData
import com.example.activitytracker.ui.main.myactivities.MyActivitiesView
import com.example.activitytracker.ui.main.myactivities.MyActivitiesViewController
import com.example.activitytracker.ui.main.myactivities.MyActivitiesViewModel
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class MyActivitiesViewControllerTest {
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var mockMyActivitiesView: MyActivitiesView

    @Mock
    private lateinit var mockViewModel: MyActivitiesViewModel

    private lateinit var controller: MyActivitiesViewController

    private var mockActivity =
        ActivityCoreData(0.0f,
            "test",
            "1234",
            "",
            1,
            1f,
            "eductation",
            false,
            0)

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `activities are loaded on viewReady`()= mainCoroutineRule.runBlockingTest{
        `given a viewController is initiated`()
        `given onViewReady has been invoked`()

        `then getSavedActivities is invoked in viewModel`(1)
    }

    @Test
    fun `when data is loaded ActivityData is invoked`()= mainCoroutineRule.runBlockingTest{
        `given a viewController is initiated`()
        `when viewModel invokes getActivity invoke onActivityDataLoaded`()
        `given onViewReady has been invoked`()
        `given getActivity has been invoked`()

        `then onActivityDataLoaded is invoked in viewModel`(1)
    }

    @Test
    fun `when data is loading invoke onDataLoading`()= mainCoroutineRule.runBlockingTest{
        `given a viewController is initiated`()
        `when viewModel invokes getActivity invoke onActivityDataLoading`()
        `given onViewReady has been invoked`()
        `given getActivity has been invoked`()

        `then onActivityDataLoading is invoked in viewModel`(1)
    }

    private fun `given a viewController is initiated`(){
        controller = MyActivitiesViewController(mockMyActivitiesView,mockViewModel)
    }

    private fun `given onViewReady has been invoked`() {
        controller.onViewReady()
    }

    private fun `given getActivity has been invoked`(){
        verify(mockViewModel).getSavedActivities()
    }

    private fun `when viewModel invokes getActivity invoke onActivityDataLoaded`() {
        whenever(mockViewModel.getSavedActivities()).then { mockViewModel.onActivityDataLoaded?.invoke() }
    }
    private fun `when viewModel invokes getActivity invoke onActivityDataLoading`() {
        whenever(mockViewModel.getSavedActivities()).then { mockViewModel.onActivityDataLoading?.invoke() }
    }

    private fun `then onActivityDataLoading is invoked in viewModel`(numWanted: Int) {
        Mockito.verify(mockViewModel).onActivityDataLoading?.invoke()
    }

    private fun `then getSavedActivities is invoked in viewModel`(numWanted: Int) {
        Mockito.verify(mockViewModel, Mockito.times(numWanted)).getSavedActivities()
    }

    private fun `then onActivityDataLoaded is invoked in viewModel`(numWanted: Int) {
        Mockito.verify(mockViewModel).onActivityDataLoaded
    }

    private fun `then the view displays the cards`(){
        verify(mockMyActivitiesView).setupRecyclerView(any(),any(),any())
    }

    private fun `and the view hides loading spinner`(){
        verify(mockMyActivitiesView).hideLoadingSpinner()
    }

}