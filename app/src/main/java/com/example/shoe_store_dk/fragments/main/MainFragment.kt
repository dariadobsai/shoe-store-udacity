package com.example.shoe_store_dk.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shoe_store_dk.R
import com.example.shoe_store_dk.databinding.FragmentMainBinding
import com.example.shoe_store_dk.fragments.login.LoginViewModel
import com.example.shoe_store_dk.fragments.login.LoginViewModel.AuthenticationState.AUTHENTICATED
import com.example.shoe_store_dk.fragments.login.LoginViewModel.AuthenticationState.UNAUTHENTICATED
import com.example.shoe_store_dk.fragments.main.adapter.ShoeListAdapter


class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding
    private val loginViewModel: LoginViewModel by activityViewModels()
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var shoeAdapter: ShoeListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main, container, false
        )

        userAuthentication()

        funPopulateShoeList()

        navigateToDetailsScreen()

        return binding.root
    }

    private fun userAuthentication() {
        loginViewModel.authenticationState.observe(
            viewLifecycleOwner,
            Observer { state ->
                when (state) {
                    AUTHENTICATED -> return@Observer
                    UNAUTHENTICATED -> findNavController().navigate(R.id.loginFragment)
                }
            })
    }

    private fun funPopulateShoeList() {
        shoeAdapter = ShoeListAdapter()

        viewModel.shoesList.observe(viewLifecycleOwner, Observer { shoes ->
            if (shoes.isNotEmpty()) {
                setListVisibility(View.VISIBLE, View.GONE)
                shoeAdapter.setShowList(shoes)

            } else {
                setListVisibility(View.GONE, View.VISIBLE)
            }
        })

        binding.shoeList.apply {
            adapter = shoeAdapter
            isFocusable = false
            layoutManager = GridLayoutManager(
                context,
                resources.getInteger(R.integer.grid_items_count),
                GridLayoutManager.VERTICAL,
                false
            )
        }
    }

    private fun navigateToDetailsScreen() {
        binding.fabAddShoe.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.mainFragment)
                it.findNavController().navigate(R.id.detailsFragment)
            else {
                Toast.makeText(
                    context!!,
                    findNavController().currentDestination?.id.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setListVisibility(listVisible: Int, emptyVisible: Int) {
        binding.apply {
            shoeList.visibility = listVisible
            root.findViewById<ConstraintLayout>(R.id.shoe_empty_view).visibility = emptyVisible
        }
    }
}