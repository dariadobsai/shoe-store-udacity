package com.example.shoe_store_dk.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.shoe_store_dk.R
import com.example.shoe_store_dk.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        if (savedInstanceState == null) {
            setUpNavigation()
        }
    }

    private fun setUpNavigation() {
        val controller = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(controller.graph)
        binding.toolbar.setupWithNavController(controller, appBarConfiguration)

        /**
         * Hide toolbar for Login and Onboarding screens, and make it visible only for the main app destinations (main and details screens)
         */
        controller.addOnDestinationChangedListener { _, destination, _ ->
            binding.appBarLayout.visibility = (
                    when (destination.id) {
                        R.id.loginFragment -> View.GONE
                        R.id.welcomeFragment -> View.GONE
                        R.id.instructionsFragment -> View.GONE
                        else -> View.VISIBLE
                    })
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setUpNavigation() // Enable back navigation
    }
}
