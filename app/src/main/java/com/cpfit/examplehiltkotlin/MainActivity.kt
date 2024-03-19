package com.cpfit.examplehiltkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.cpfit.examplehiltkotlin.adapter.RvDapartmentCarouselAdapter
import com.cpfit.examplehiltkotlin.adapter.RvDapartmentDetailAdapter
import com.cpfit.examplehiltkotlin.model.ModelDetail
import com.cpfit.examplehiltkotlin.model.ModelDepartment
import com.cpfit.examplehiltkotlin.viewModel.DepartmentViewModel
import com.cpfit.examplehiltkotlin.databinding.ActivityMainBinding
import com.cpfit.examplehiltkotlin.dialog.CustomDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), RvDapartmentCarouselAdapter.IRvDapartmentCarouselAdapter,
    RvDapartmentDetailAdapter.IRvDapartmentDetailAdapter {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var departmentViewModel: DepartmentViewModel
    private lateinit var rvDapartmentCarouselAdapter: RvDapartmentCarouselAdapter
    private lateinit var rvDapartmentDetailAdapter: RvDapartmentDetailAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        departmentViewModel = ViewModelProvider(this)[DepartmentViewModel::class.java]
        getTodo()
        observe()
        departmentViewModel.clearUrl()
    }

    private fun getTodo() {
        departmentViewModel.getTodo()
    }

    private fun observe() {
        departmentViewModel.getTodo.observe(this) {
            if (it.error == null && it.data != null
            ) {
                setDepartmentCarousel(it.data)
                departmentViewModel.getDepartmentDetailById(it.data[0].id.toInt())
                binding.txtDepartmentName.text = it.data[0].name
            } else {
                Toast.makeText(this, it.error.toString(), Toast.LENGTH_LONG).show()
            }
        }

        departmentViewModel.getDepartmentDetail.observe(this) {
            if (it.error == null && it.data != null
            ) {
                setDepartmentDetail(it.data)
            } else {
                Toast.makeText(this, it.error.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setDepartmentCarousel(data: List<ModelDepartment>) {
        rvDapartmentCarouselAdapter = RvDapartmentCarouselAdapter(this)
        binding.rvDepartmentCarousel.adapter = rvDapartmentCarouselAdapter
        binding.rvDepartmentCarousel.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvDapartmentCarouselAdapter.addMoviesList(data)
    }

    private fun showDialogDetail(description: String) {
        val customDialog = CustomDialog(this)
        customDialog.setDescription(description)
        customDialog.show()
    }

    private fun setDepartmentDetail(data: List<ModelDetail>) {
        val gridLayoutManager = GridLayoutManager(this, 2)
        rvDapartmentDetailAdapter = RvDapartmentDetailAdapter(this)
        binding.rvDepartmentDetail.adapter = rvDapartmentDetailAdapter
        binding.rvDepartmentDetail.layoutManager = gridLayoutManager
        rvDapartmentDetailAdapter.addMoviesDetailList(data)
    }

    override fun onClickPicture(departmentId: Int, name: String) {
        departmentViewModel.getDepartmentDetailById(departmentId)
        binding.txtDepartmentName.text = name
    }

    override fun onClickDetail(desc: String) {
        showDialogDetail(desc)
    }
}