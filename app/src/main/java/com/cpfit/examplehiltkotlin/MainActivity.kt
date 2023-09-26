package com.cpfit.examplehiltkotlin

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.cpfit.examplehiltkotlin.api.viewModel.TodoViewModel
import com.cpfit.examplehiltkotlin.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var todoViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]
        getTodo()
        observe()

        binding.btn.setOnClickListener{
            getTodo()
        }
    }

    private fun getTodo(){
        todoViewModel.getTodo()
    }

    private fun observe(){
        todoViewModel.getTodo.observe(this){
            if (it.error == null && it.data != null
            ) {
                Toast.makeText(this,it.data.title,Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this,it.error.toString(),Toast.LENGTH_LONG).show()
            }
        }
    }
}