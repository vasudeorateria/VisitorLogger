package com.kjstudios.visitorlogger.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kjstudios.visitorlogger.R
import com.kjstudios.visitorlogger.model.AppDatabase

class MainActivity : AppCompatActivity() {

    private val bottomNavigationView: BottomNavigationView by lazy {
        findViewById(R.id.bottomNavigationView)
    }
    private val navController: NavController by lazy {
        findNavController(R.id.navHostFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView.setupWithNavController(navController)
        AppDatabase.initialiseDatabase(applicationContext)
    }

    override fun onBackPressed() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment)
        val backStackEntryCount = navHostFragment?.childFragmentManager?.backStackEntryCount ?: return
        if (backStackEntryCount <= 2) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}