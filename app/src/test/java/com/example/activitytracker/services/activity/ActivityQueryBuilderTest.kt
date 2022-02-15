package com.example.activitytracker.services.activity

import com.example.activitytracker.models.ActivityQueryAccessibility
import com.example.activitytracker.models.ActivityQueryData
import com.example.activitytracker.models.ActivityQueryPrice

class ActivityQueryBuilderTest {

    var activityQueryBuilder = ActivityQueryBuilder()

    val expectedUrlString = "https://www.boredapi.com/api/activity"

    val mockQueryData = ActivityQueryData(
        ActivityQueryAccessibility(
            0f,
            -1f
        ),
        0,
        ActivityQueryPrice(
            0f,
            -1f
        ),
        ""
    )

//    @Test
//    fun `Correctly parses querydata into a url string with no additional parameters`(){
//        assertEquals("Url difference found", expectedUrlString, activityQueryBuilder.buildQuery(mockQueryData))
//    }
}