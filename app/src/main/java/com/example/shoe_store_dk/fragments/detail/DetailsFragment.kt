package com.example.shoe_store_dk.fragments.detail

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.shoe_store_dk.R
import com.example.shoe_store_dk.databinding.FragmentDetailsBinding
import com.example.shoe_store_dk.fragments.main.MainViewModel
import com.example.shoe_store_dk.model.Shoe
import com.google.android.material.textfield.TextInputLayout

class DetailsFragment : Fragment(), View.OnClickListener, TextWatcher {

    private lateinit var binding: FragmentDetailsBinding

    private val shoeListViewModel: MainViewModel by activityViewModels()

    private lateinit var inputFields: List<TextInputLayout>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)

        binding.apply {
            btnCancel.setOnClickListener(this@DetailsFragment)
            btnSave.setOnClickListener(this@DetailsFragment)
            shoeNameInput.addTextChangedListener(this@DetailsFragment)
            shoeSizeInput.addTextChangedListener(this@DetailsFragment)
            shoeCompanyInput.addTextChangedListener(this@DetailsFragment)
            shoeDescInput.addTextChangedListener(this@DetailsFragment)

            inputFields = listOf(
                shoeNameLayout,
                shoeSizeLayout,
                shoeCompanyLayout,
                shoeDescLayout
            )
        }

        return binding.root
    }

    private fun saveShoeEntry(): Shoe {
        return Shoe(
            binding.shoeNameInput.text.toString(),
            binding.shoeSizeInput.text.toString().toDouble(),
            binding.shoeCompanyInput.text.toString(),
            binding.shoeDescInput.text.toString()
        )
    }

    override fun onClick(v: View?) {
        val navController = v?.findNavController()
        when (v?.id) {
            R.id.btn_cancel -> navController?.navigateUp() // go back to the MainFragment
            R.id.btn_save -> {
                // save a new shoe entry and go back to the MainFragment
                shoeListViewModel.addShoeEntries(saveShoeEntry())
                navController?.navigateUp()
            }
        }
    }

    override fun afterTextChanged(s: Editable?) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        var everythingIsValid = true
        for (inputField in inputFields) {
            when {
                inputField.editText!!.text.toString().isEmpty() -> {
                    everythingIsValid = false
                }
                inputField.editText?.text?.length!! > inputField.counterMaxLength -> {
                    inputField.error =
                        getString(R.string.max_char_count) + inputField.counterMaxLength
                    everythingIsValid = false
                }
                else -> {
                    inputField.error = null
                }
            }

        }

        binding.btnSave.isEnabled = everythingIsValid

    }
}