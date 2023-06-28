package com.dicoding.myapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.myapp.databinding.ItemRowMemberBinding

class ListMemberAdapter(private val listMember: ArrayList<Member>) : RecyclerView.Adapter<ListMemberAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemRowMemberBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, _, description, _, photo) = listMember[position]
        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.binding.imgItemPhoto)
        holder.binding.tvItemName.text = name
        holder.binding.tvItemDescription.text = description
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listMember[holder.adapterPosition]) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Member)
    }

    override fun getItemCount(): Int = listMember.size

    class ListViewHolder(var binding: ItemRowMemberBinding) : RecyclerView.ViewHolder(binding.root)
}