package com.cpfit.examplehiltkotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cpfit.examplehiltkotlin.model.ModelDetail
import com.cpfit.examplehiltkotlin.databinding.DepartmentCarouselDetailBinding


class RvDapartmentDetailAdapter(private val listener: IRvDapartmentDetailAdapter) :
    RecyclerView.Adapter<RvDapartmentDetailAdapter.MyHolder>() {
    private lateinit var context: Context
    private var moviesList = mutableListOf<ModelDetail>()

    class MyHolder(val binding: DepartmentCarouselDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): MyHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    DepartmentCarouselDetailBinding.inflate(layoutInflater, parent, false)
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
            holder.binding.textName.text = name
            holder.binding.textDescription.text = desc
            holder.binding.textPrice.text = price
            holder.itemView.setOnClickListener {
                listener.onClickDetail(desc)
            }
            Glide.with(holder.itemView).load(moviesList[position].imageUrl)
                .into(holder.binding.image)
        }
    }

    fun addMoviesDetailList(moviesList: List<ModelDetail>) {
        this.moviesList.clear()
        this.moviesList.addAll(moviesList)
        notifyDataSetChanged()
    }

    interface IRvDapartmentDetailAdapter {
        fun onClickDetail(desc: String)
    }

}