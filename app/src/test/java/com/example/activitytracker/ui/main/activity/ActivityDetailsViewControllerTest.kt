package com.example.activitytracker.ui.main.activity

import com.example.MainCoroutineRule
import com.example.activitytracker.models.ActivityCoreData
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify

class MyActivitiesViewControllerTest {
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var mockView: ActivityDetailsView

    @Mock
    private lateinit var mockViewModel: ActivityDetailsViewModel

    private lateinit var controller: ActivityDetailsViewController

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
    fun `activitiy data is converted on viewReady`()= mainCoroutineRule.runBlockingTest{
        `given a viewController is initiated`()
        `when onViewReady has been invoked`()
        `then convertDataString should be invoked`()
    }

    @Test
    fun `activitiy is loaded on viewReady`()= mainCoroutineRule.runBlockingTest{
        `given a viewController is initiated`()
        `when onViewReady has been invoked`()
        `then view should be populated`()
    }

    @Test
    fun `when data is loaded ActivityData is invoked`()= mainCoroutineRule.runBlockingTest{
        `given a viewController is initiated`()

    }

    @Test
    fun `seekbarListener set on viewReady`()= mainCoroutineRule.runBlockingTest{
        `given a viewController is initiated`()
        `when onViewReady has been invoked`()

    }
    
//GIVEN
    private fun `given a viewController is initiated`(){
        controller = ActivityDetailsViewController(mockView,mockViewModel)
        mockViewModel.activityCoreData = mockActivity
    }

//WHEN
    private fun `when onViewReady has been invoked`() {
        controller.onViewReady()
    }

//THEN
    private fun `then view should be populated`(){
        verify(mockView).setActivityTitle("string")
    }

    private fun `then convertDataString should be invoked`(){
        verify(mockViewModel).convertDataStringToActivity("string")
    }


}