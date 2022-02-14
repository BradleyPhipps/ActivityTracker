package com.example.activitytracker.ui.main.myactivities

import android.widget.Button
import com.example.MainCoroutineRule
import com.example.activitytracker.models.ActivityCoreData
import com.example.activitytracker.services.activity.ActivityService
import com.example.activitytracker.services.activity.SavedActivityRepository
import com.example.activitytracker.services.navigation.NavigationService
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

class MyActivitiesViewModelTest {
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var mockActivityService: ActivityService

    @Mock
    private lateinit var mockSavedActivityRepository: SavedActivityRepository

    @Mock
    private lateinit var mockNavService: NavigationService

    private lateinit var myActivitiesViewModel: MyActivitiesViewModel

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

    private var keyList = listOf("1234", "22315")
    private var mockMap = emptyMap<String, String>()

    private var onDataLoadedInvoked = false
    private var onDataLoadingInvoked = false
    private var onFollowStateChangedInvoked = false


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }


    @Test
    fun `viewModel should invoke loading and loaded call backs on getSavedActivtities being invoked`() = mainCoroutineRule.runBlockingTest{
        `given a viewModel is initialised`()
        `given data is returned from the activityService`()
        `given onActivityDataLoading is set`()
        `given onActivityDataLoaded is set`()
        `when getSavedActivities is invoked`()
        `then onDataLoading is invoked`()
        `then onDataLoaded is invoked`()
    }

    @Test
    fun `viewModel should invoke followStateChanged callback on setActivityFollow being invoked`() = mainCoroutineRule.runBlockingTest {
        `given a viewModel is initialised`()
        `given onFollowStateChanged is set`()
        `when setActivityFollow is invoked`(false)
        `then onFollowStateChanged is invoked`()
    }

    @Test
    fun `viewModel should invoke followActivity in the savedActivityRepository if activityFollowed is false`(){
        `given a viewModel is initialised`()
        `when setActivityFollow is invoked`(false)
        `then savedActivityRepository follow is invoked`()
    }

    @Test
    fun `viewModel should invoke unfollowActivity in the savedActivityRepository if activityFollowed is true`(){
        `given a viewModel is initialised`()
        `when setActivityFollow is invoked`(true)
        `then savedActivityRepository unfollow is invoked`()
    }

    //GIVEN
    private fun `given a viewModel is initialised`() {
        myActivitiesViewModel = MyActivitiesViewModel(mockActivityService, mockSavedActivityRepository, mockNavService)
    }

    private suspend fun `given data is returned from the activityService`(){
        whenever(mockActivityService.getSavedActivities(mockMap)).thenReturn(mutableListOf(mockActivity))
    }

    private fun `given onActivityDataLoaded is set`() {
        myActivitiesViewModel.onActivityDataLoaded = {
            onDataLoadedInvoked = true
        }
    }

    private fun `given onActivityDataLoading is set`() {
        myActivitiesViewModel.onActivityDataLoading = {
            onDataLoadingInvoked = true
        }
    }

    private fun `given onFollowStateChanged is set`() {
        myActivitiesViewModel.onFollowStateChanged = { p1, p2 ->
            onFollowStateChangedInvoked = true
        }
    }

    //WHEN
    private fun `when getSavedActivities is invoked`() {
        myActivitiesViewModel.getSavedActivities()
    }

    private fun `when setActivityFollow is invoked`(followed: Boolean) {
        mockActivity.activityFollowed = followed
        myActivitiesViewModel.setActivityFollow(any(), mockActivity)
    }
    //THEN

    private fun `then savedActivityRepository follow is invoked`(){
        Mockito.verify(mockSavedActivityRepository, Mockito.times(1)).followActivity(any(), any())
    }

    private fun `then savedActivityRepository unfollow is invoked`(){
        Mockito.verify(mockSavedActivityRepository, Mockito.times(1)).unfollowActivity(any())
    }

    private fun `then onDataLoading is invoked`() {
        Assert.assertTrue("not true", onDataLoadingInvoked)
    }

    private fun `then onDataLoaded is invoked`() {
        Assert.assertTrue("not true", onDataLoadedInvoked)
    }

    private fun `then onFollowStateChanged is invoked`() {
        Assert.assertTrue("not true", onFollowStateChangedInvoked)
    }
}