package com.example.shoe_store_dk.fragments.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shoe_store_dk.R
import com.example.shoe_store_dk.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false)

        val navController = findNavController()
        binding.welBtn.setOnClickListener {
            if (navController.currentDestination?.id == R.id.welcomeFragment)
                navController.navigate(R.id.action_welcomeFragment_to_instructionsFragment)
        }

        return binding.root
    }
}