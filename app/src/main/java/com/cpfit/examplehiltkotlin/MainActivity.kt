package com.cpfit.examplehiltkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cpfit.examplehiltkotlin.adapter.RvDapartmentCarouselAdapter
import com.cpfit.examplehiltkotlin.api.model.ModelMovies
import com.cpfit.examplehiltkotlin.api.viewModel.MoviesViewModel
import com.cpfit.examplehiltkotlin.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var todoViewModel: MoviesViewModel
    private lateinit var adapter: RvDapartmentCarouselAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        todoViewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        getTodo()
        observe()
        todoViewModel.clearUrl()
//        binding.btn.setOnClickListener {
//            getTodo()
//        }
    }

    private fun getTodo() {
        todoViewModel.getTodo()
    }

    private fun observe() {
        todoViewModel.getTodo.observe(this) {
            if (it.error == null && it.data != null
            ) {
                it.data
                Log.d("ART", "sss")
                setDepartmentCarousel(it.data)
            } else {
                Toast.makeText(this, it.error.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setDepartmentCarousel(data: List<ModelMovies>) {
        adapter = RvDapartmentCarouselAdapter()
        binding.rvDepartmentCarousel.adapter = adapter
        binding.rvDepartmentCarousel.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapter.addMoviesList(data)
    }
}