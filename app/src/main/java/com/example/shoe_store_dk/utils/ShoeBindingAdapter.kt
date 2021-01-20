package com.example.shoe_store_dk.utils

import androidx.databinding.BindingAdapter
import com.example.shoe_store_dk.model.Shoe
import com.google.android.material.textfield.TextInputEditText

// Checking if shoe size value is not null and then fetch the text, otherwise set empty text
// This method is temporary and needed only at the current project state
@BindingAdapter("shoeSize")
fun TextInputEditText.setText(shoe: Shoe?) {
    setText(
        when (shoe?.size) {
            null -> ""
            else -> shoe.size.toString()
        }
    )
}


