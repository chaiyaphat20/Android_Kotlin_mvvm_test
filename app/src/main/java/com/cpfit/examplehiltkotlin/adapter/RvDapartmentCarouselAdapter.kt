package com.cpfit.examplehiltkotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cpfit.examplehiltkotlin.api.model.ModelMovies
import com.cpfit.examplehiltkotlin.databinding.DepartmentCarouselItemBinding

class RvDapartmentCarouselAdapter :
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
        with(holder) {
            with(moviesList[position]) {
                binding.textMovieName.text = name

            }
        }
    }

    fun addMoviesList(moviesList: List<ModelMovies>) {
        this.moviesList.clear()
        this.moviesList.addAll(moviesList)
        notifyDataSetChanged()
    }
}