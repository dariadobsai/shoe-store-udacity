package com.example.shoe_store_dk.fragments.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shoe_store_dk.R
import com.example.shoe_store_dk.databinding.ListShoeLayoutBinding
import com.example.shoe_store_dk.model.Shoe

class ShoeListAdapter() :
    RecyclerView.Adapter<ShoeListAdapter.ShoeViewHolder>() {

    var shoes: MutableList<Shoe> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShoeViewHolder {

        val binding: ListShoeLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_shoe_layout,
            parent,
            false
        )

        return ShoeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoeViewHolder, position: Int) {
        holder.bind(shoes[position])
    }

    override fun getItemCount(): Int {
        return shoes.size
    }

    fun setShowList(shoes: MutableList<Shoe>) {
        if (this.shoes !== shoes)
            this.shoes = shoes

        notifyDataSetChanged()
    }

    inner class ShoeViewHolder(val binding: ListShoeLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(shoe: Shoe) {
            with(binding) {
                shoeModel = shoe
                executePendingBindings()
            }
        }
    }
}