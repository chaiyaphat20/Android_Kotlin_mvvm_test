package com.cpfit.examplehiltkotlin.adapter

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cpfit.examplehiltkotlin.api.model.ModelMovies
import com.cpfit.examplehiltkotlin.databinding.DepartmentCarouselItemBinding


class RvDapartmentCarouselAdapter(private val listener: IRvDapartmentCarouselAdapter) :
    RecyclerView.Adapter<RvDapartmentCarouselAdapter.MyHolder>() {
    private lateinit var context: Context
    private var moviesList = mutableListOf<ModelMovies>()

    class MyHolder(val binding: DepartmentCarouselItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): MyHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    DepartmentCarouselItemBinding.inflate(layoutInflater, parent, false)
                return MyHolder(binding)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        context = parent.context
        return MyHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        with(moviesList[position]) {
            holder.binding.textMovieName.text = name
            holder.itemView.setOnClickListener {
                listener.onClickPicture(id.toInt(), name)
            }
            Glide.with(holder.itemView).load(moviesList[position].imageUrl)
                .into(holder.binding.image)
        }
    }

    fun addMoviesList(moviesList: List<ModelMovies>) {
        this.moviesList.clear()
        this.moviesList.addAll(moviesList)
        notifyDataSetChanged()
    }

    interface IRvDapartmentCarouselAdapter {
        fun onClickPicture(departmentId: Int, name: String)
    }
}