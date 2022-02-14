package com.example.activitytracker.ui.main.home

import com.example.MainCoroutineRule
import com.example.activitytracker.models.ActivityCoreData
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class HomeViewControllerTest {
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var mockHomeView: HomeView

    @Mock
    private lateinit var mockViewModel: HomeViewModel

    private lateinit var controller: HomeViewController

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
    fun `data is loaded once controller onViewReady invoked`()= mainCoroutineRule.runBlockingTest {
        `given a viewController is initiated`()
        `given onViewReady has been invoked`()

        `then getActivity is invoked in viewModel`(1)

    }
    @Test
    fun `new data is loaded when the new activity button is clicked`()= mainCoroutineRule.runBlockingTest{
        `given a viewController is initiated`()
        `given onViewReady has been invoked`()
        `when new activity button is clicked`()

        `then getActivity is invoked in viewModel`(2)


    }

    @Test
    fun `when data is loaded displayData card is displayed`()= mainCoroutineRule.runBlockingTest{
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


    private fun `when new activity button is clicked`() {
        val clickCaptor = argumentCaptor<() -> Unit>()
        verify(mockHomeView).setButtonClickedListener(clickCaptor.capture())
        clickCaptor.firstValue.invoke()
    }

    private fun `given a viewController is initiated`(){
        controller = HomeViewController(mockHomeView,mockViewModel)
    }

    private fun `given onViewReady has been invoked`() {
        controller.onViewReady()
    }

    private fun `given getActivity has been invoked`(){
        verify(mockViewModel).getActivity()
    }

    private fun `when viewModel invokes getActivity invoke onActivityDataLoaded`() {
        whenever(mockViewModel.getActivity()).then { mockViewModel.onActivityDataLoaded?.invoke() }
    }
    private fun `when viewModel invokes getActivity invoke onActivityDataLoading`() {
        whenever(mockViewModel.getActivity()).then { mockViewModel.onActivityDataLoading?.invoke() }
    }

    private fun `then onActivityDataLoading is invoked in viewModel`(numWanted: Int) {
        Mockito.verify(mockViewModel).onActivityDataLoading?.invoke()
    }

    private fun `then getActivity is invoked in viewModel`(numWanted: Int) {
        Mockito.verify(mockViewModel, Mockito.times(numWanted)).getActivity()
    }

    private fun `then onActivityDataLoaded is invoked in viewModel`(numWanted: Int) {
        Mockito.verify(mockViewModel).onActivityDataLoaded
    }

    private fun `then the view displays the card`(){
        verify(mockHomeView).displayCard(any())
    }

    private fun `and the view hides loading spinner`(){
        verify(mockHomeView).hideLoadingSpinner()
    }

}