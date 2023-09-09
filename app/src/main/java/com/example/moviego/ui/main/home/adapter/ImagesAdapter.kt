package com.example.moviego.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviego.R
import com.example.moviego.di.IMAGE_URL
import com.example.moviego.domain.models.BackDrops
import com.example.moviego.domain.models.ImagesMovieModel

class ImagesAdapter(val list: List<BackDrops>): RecyclerView.Adapter<ImagesAdapter.ViewHolderImages>() {

    inner class ViewHolderImages(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image : ImageView = itemView.findViewById(R.id.detail_images)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderImages {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_images,parent,false)
        return ViewHolderImages(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolderImages, position: Int) {
        val imageUrl = IMAGE_URL + list[position].file_path
        Glide.with(holder.itemView.context).load(imageUrl).into(holder.image)

    }
}