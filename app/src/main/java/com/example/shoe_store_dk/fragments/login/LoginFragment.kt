package com.example.shoe_store_dk.fragments.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.shoe_store_dk.R
import com.example.shoe_store_dk.databinding.FragmentLoginBinding

class LoginFragment : Fragment(), View.OnClickListener, TextWatcher {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by activityViewModels()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.apply {
            btnLogin.setOnClickListener(this@LoginFragment)
            btnExistingAccount.setOnClickListener(this@LoginFragment)
            loginEmailInput.addTextChangedListener(this@LoginFragment)
            loginPasswordInput.addTextChangedListener(this@LoginFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        // Close application on back press when Login Fragment is opened
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().finish()
        }
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.btn_login || view?.id == R.id.btn_existing_account) {
            viewModel.authenticate()
            if (navController.currentDestination?.id == R.id.loginFragment) {
                navController.navigate(R.id.action_loginFragment_to_login_and_onboarding)
            }
        }
    }

    override fun afterTextChanged(s: Editable?) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // Listen to the input text changes and disable or enable login/sign up buttons
        binding.apply {
            when (viewModel.validateEmail(
                binding.loginEmailInput.text.trim().toString(),
                binding.loginPasswordInput.text.toString()
            )) {
                true -> {
                    inputError.visibility = View.GONE
                    btnLogin.isEnabled = true
                    btnExistingAccount.isEnabled = true
                }
                false -> {
                    inputError.visibility = View.VISIBLE
                    btnLogin.isEnabled = false
                    btnExistingAccount.isEnabled = false

                }
            }
        }
    }
}