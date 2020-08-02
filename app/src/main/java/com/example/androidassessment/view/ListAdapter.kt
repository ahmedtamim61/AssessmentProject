package com.example.androidassessment.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidassessment.R
import com.example.androidassessment.model.Data

class ListAdapter(private val context : Context, private val data : ArrayList<Data>) :
    RecyclerView.Adapter<ListAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.header?.text = data[position].title
        holder.description?.text = data[position].description
        Glide.with(context)
            .load(data[position].imageHref)
            .centerCrop()
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .fallback(R.drawable.placeholder)
            .into(holder.image!!)
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var header : TextView? = null
        var description : TextView? = null
        var image : ImageView? = null

        init {
            header = itemView.findViewById(R.id.header)
            description = itemView.findViewById(R.id.description)
            image = itemView.findViewById(R.id.image_view)
        }
    }
}