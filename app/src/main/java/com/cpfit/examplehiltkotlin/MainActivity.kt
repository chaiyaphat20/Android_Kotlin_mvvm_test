package com.cpfit.examplehiltkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.cpfit.examplehiltkotlin.adapter.RvDapartmentCarouselAdapter
import com.cpfit.examplehiltkotlin.adapter.RvDapartmentDetailAdapter
import com.cpfit.examplehiltkotlin.api.model.ModelDetail
import com.cpfit.examplehiltkotlin.api.model.ModelMovies
import com.cpfit.examplehiltkotlin.api.viewModel.MoviesViewModel
import com.cpfit.examplehiltkotlin.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), RvDapartmentCarouselAdapter.IRvDapartmentCarouselAdapter {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var todoViewModel: MoviesViewModel
    private lateinit var rvDapartmentCarouselAdapter: RvDapartmentCarouselAdapter
    private lateinit var rvDapartmentDetailAdapter: RvDapartmentDetailAdapter
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
                setDepartmentCarousel(it.data)
                todoViewModel.getDepartmentDetailById(it.data[0].id.toInt())
                binding.txtDepartmentName.text = it.data[0].name
            } else {
                Toast.makeText(this, it.error.toString(), Toast.LENGTH_LONG).show()
            }
        }

        todoViewModel.getDepartmentDetail.observe(this) {
            if (it.error == null && it.data != null
            ) {
                setDepartmentDetail(it.data)
            } else {
                Toast.makeText(this, it.error.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setDepartmentCarousel(data: List<ModelMovies>) {
        rvDapartmentCarouselAdapter = RvDapartmentCarouselAdapter(this)
        binding.rvDepartmentCarousel.adapter = rvDapartmentCarouselAdapter
        binding.rvDepartmentCarousel.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvDapartmentCarouselAdapter.addMoviesList(data)
    }

    private fun setDepartmentDetail(data: List<ModelDetail>) {
        val gridLayoutManager = GridLayoutManager(this, 2)
        rvDapartmentDetailAdapter = RvDapartmentDetailAdapter()
        binding.rvDepartmentDetail.adapter = rvDapartmentDetailAdapter
        binding.rvDepartmentDetail.layoutManager = gridLayoutManager
        rvDapartmentDetailAdapter.addMoviesDetailList(data)
    }

    override fun onClickPicture(departmentId: Int, name: String) {
        todoViewModel.getDepartmentDetailById(departmentId)
        binding.txtDepartmentName.text = name
    }
}