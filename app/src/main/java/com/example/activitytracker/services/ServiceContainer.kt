package com.example.activitytracker.services

import com.example.activitytracker.services.data.DataService

class ServiceContainer(
    val dataService: DataService,
    val activityService: ActivityService
) {
}