package com.example.shoe_store_dk.fragments.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoe_store_dk.model.Shoe

class MainViewModel : ViewModel() {

    private var _shoesList = MutableLiveData<MutableList<Shoe>>(mutableListOf())
    val shoesList : LiveData<MutableList<Shoe>>
        get() = _shoesList


    fun addShoeEntries(shoe : Shoe) : MutableLiveData<MutableList<Shoe>> {
        _shoesList.value?.add(shoe)
        return _shoesList
    }
}