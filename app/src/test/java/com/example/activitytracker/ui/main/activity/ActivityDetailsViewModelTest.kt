package com.example.activitytracker.ui.main.activity

import android.util.Log
import com.example.MainCoroutineRule
import com.example.activitytracker.models.ActivityCoreData
import com.example.activitytracker.services.activity.SavedActivityRepository
import com.example.activitytracker.services.activity.ActivityService
import com.example.activitytracker.services.json.JsonService
import com.example.activitytracker.services.navigation.NavigationService
import com.example.activitytracker.ui.main.activity.ActivityDetailsViewModel
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.mockito.kotlin.whenever

class ActivityDetailsViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var mockSavedActivityRepository: SavedActivityRepository

    @Mock
    private lateinit var mockJsonService: JsonService

    private lateinit var viewModel: ActivityDetailsViewModel

    private var mockActivityDataString = "dataString"

    var mockActivity =
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
    fun `viewModel should invoke convertDataStringToActivity`() = mainCoroutineRule.runBlockingTest {
        `given a viewModel is initialised`()
        `when viewModel convertDataString is invoked`()

        `then jsonService should be invoked`()
    }

    @Test
    fun `viewModel should invoke savedActivityRepository unfollow activity when unfollowActivity is invoked`() = mainCoroutineRule.runBlockingTest {
        `given a viewModel is initialised`()
        `when unfollowActivity is invoked`()
        `then unfollowActivity should be invoked in the savedActivityRepository`()
    }



    @Test
    fun `iewModel should invoke savedActivityRepository follow activity when followActivity is invoked`(){
        `given a viewModel is initialised`()
        `when followActivity is invoked`()
        `then followActivity should be invoked in the savedActivityRepository`()

    }

    @Test
    fun `viewModel should invoke updateActivityProgress in savedRepository if updateProgress is invoked`(){
        `given a viewModel is initialised`()
        `when updateActivityProgress is invoked`()
        `then updateActivityProgress should be invoked in the savedActivityRepository`()
    }

    //GIVEN
    private fun `given a viewModel is initialised`() {
        viewModel = ActivityDetailsViewModel(mockSavedActivityRepository, mockJsonService)
        viewModel.activityDataString = mockActivityDataString
        viewModel.activityCoreData = mockActivity
    }

    //WHEN
    private fun `when viewModel convertDataString is invoked`() {
        viewModel.convertDataStringToActivity(mockActivityDataString)
    }
    private fun `when followActivity is invoked`() {
        viewModel.followActivity(mockActivity)
    }

    private fun `when unfollowActivity is invoked`() {
        viewModel.unfollowActivity(mockActivity)
    }

    private fun `when updateActivityProgress is invoked`() {
        viewModel.updateActivityProgress(50)
    }


    //THEN
    private fun `then jsonService should be invoked`() {
        verify(mockJsonService).convertToObject<ActivityCoreData>(any())
    }

    private fun `then followActivity should be invoked in the savedActivityRepository`() {
        verify(mockSavedActivityRepository).followActivity("1234", 0)
    }
    private fun `then unfollowActivity should be invoked in the savedActivityRepository`() {
        verify(mockSavedActivityRepository).unfollowActivity("1234")
    }

    private fun `then updateActivityProgress should be invoked in the savedActivityRepository`() {
        verify(mockSavedActivityRepository).updateActivityProgress("1234",50)
    }


}