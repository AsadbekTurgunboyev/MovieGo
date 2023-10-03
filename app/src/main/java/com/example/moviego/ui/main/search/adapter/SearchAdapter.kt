package com.example.moviego.ui.main.search.adapter

import android.content.Intent
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviego.R
import com.example.moviego.domain.models.Movie
import com.example.moviego.domain.models.MovieKeyword
import com.example.moviego.domain.models.SearchMovieModel
import com.example.moviego.ui.main.detail.DetailFilmFragment

class SearchAdapter(var list: List<Movie>) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    var query: String = ""

    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title_search)

        fun bind(model: Movie){
            val mainText = model.title.lowercase()


            val spannableString = SpannableString(mainText)
            val start = mainText.indexOf(query)

            if (start != -1) {
                val end = start + query.length
                spannableString.setSpan(
                    ForegroundColorSpan(Color.WHITE),
                    start,
                    end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }

            title.text = spannableString

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailFilmFragment::class.java)
                intent.putExtra("movie_id",model.id)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_with_keyword,parent,false)
        return SearchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(list[position])
    }


    fun setQueryText(query: String){
        this.query = query
    }

    fun refreshItems() {
        notifyDataSetChanged()
    }
}