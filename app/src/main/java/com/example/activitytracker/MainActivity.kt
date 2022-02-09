package com.example.activitytracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.activitytracker.databinding.MainActivityBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding:MainActivityBinding
    private lateinit var bootstrapper: Bootstrapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bootstrapper = Bootstrapper()

        setupNavigation()
    }

    private fun setupNavigation(){
        val navView: BottomNavigationView = binding.bottomNavigation
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.selectedItemId = R.id.navHome
        navView.setupWithNavController(navController)

        navView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.navHome -> {
                    navController.navigate(R.id.action_global_homeFragment)
                    true}
                R.id.navMyActivities ->{
                    navController.navigate(R.id.action_global_myActivitiesFragment)
                    true
                }
                R.id.navSearch ->{
                    navController.navigate(R.id.action_global_searchFragment)
                    true
                }
                else -> false
            }
        }

    }
}