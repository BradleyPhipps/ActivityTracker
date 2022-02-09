package com.example.activitytracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.activitytracker.ui.main.home.HomeFragment
import com.example.activitytracker.ui.main.myactivities.MyActivitiesFragment

class MainActivity : AppCompatActivity() {

    private lateinit var bootstrapper: Bootstrapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        bootstrapper = Bootstrapper()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MyActivitiesFragment.newInstance())
                .commitNow()
        }
    }
}