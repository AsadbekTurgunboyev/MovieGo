package com.example.moviego.ui.main.home.adapter

import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviego.R

class TypeAdapter(private val images: List<Int>): RecyclerView.Adapter<TypeAdapter.TypeViewHolder>() {

    inner class TypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView = itemView.findViewById<ImageView>(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_type, parent, false)
        return TypeViewHolder(view)
    }

    override fun getItemCount(): Int {

        return images.size
    }

    override fun onBindViewHolder(holder: TypeViewHolder, position: Int) {
        val params = holder.imageView.layoutParams

        // If the position is 0, set the height to 180dp, otherwise set it to 90dp
        params.height = if (position == 0) {
            convertDpToPixel(190, holder.itemView.context)
        } else {
            convertDpToPixel(90, holder.itemView.context)
        }

        holder.imageView.layoutParams = params
        holder.imageView.setImageResource(images[position])
    }

    fun convertDpToPixel(dp: Int, context: Context): Int {
        val resources = context.resources
        val metrics = resources.displayMetrics
        return (dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
    }
}