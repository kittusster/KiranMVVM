package com.linoop.kiranmvvm.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.linoop.kiranmvvm.viewmodels.MainViewModel
import com.linoop.kiranmvvm.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun showMessage(title:String, message:String) {
        val alert = AlertDialog.Builder(this)
        alert.setPositiveButton("OK"){_,_->

        }
        alert.setTitle(title)
        alert.setMessage(message)
        alert.create()
        alert.show()
    }
}