package com.example.moviego.ui.main.seeAll

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviego.R
import com.example.moviego.di.IMAGE_URL
import com.example.moviego.domain.models.MovieItemModel
import com.example.moviego.ui.main.detail.DetailFilmFragment

class SeeAllAdapter(val list: List<MovieItemModel>): RecyclerView.Adapter<SeeAllAdapter.SeeAllViewHolder>() {

    inner class SeeAllViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val image: ImageView = itemView.findViewById(R.id.movies_image_see_all)

        fun bind( model: MovieItemModel){
            val imageUrl = IMAGE_URL + model.poster_path
            Glide.with(itemView.context).load(imageUrl).into(image)
            itemView.setOnClickListener{
                val intent = Intent(itemView.context, DetailFilmFragment::class.java)
                intent.putExtra("movie_id",model.id)
                itemView.context.startActivity(intent)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeeAllViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_see_all,parent,false)
        return SeeAllViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SeeAllViewHolder, position: Int) {
        holder.bind(list[position])
    }
}