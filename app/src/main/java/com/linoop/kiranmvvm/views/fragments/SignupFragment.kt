package com.linoop.kiranmvvm.views.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.linoop.kiranmvvm.R
import com.linoop.kiranmvvm.databinding.FragmentSignupBinding
import com.linoop.kiranmvvm.models.UserTable
import com.linoop.kiranmvvm.utils.DatabaseResult
import com.linoop.kiranmvvm.viewmodels.MainViewModel
import com.linoop.kiranmvvm.views.MainActivity

class SignupFragment : Fragment(R.layout.fragment_signup) {

    private lateinit var binding: FragmentSignupBinding

    private lateinit var viewModel: MainViewModel

    private lateinit var context: MainActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignupBinding.bind(view)
        context = activity as MainActivity
        viewModel = context.mainViewModel
        setupFragment()
    }

    private fun setupFragment() {
        binding.progressBar.visibility = View.GONE
        viewModel.signupResponse.observe(context) {
            when (it) {
                is DatabaseResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is DatabaseResult.Error -> {
                    binding.progressBar.visibility = View.GONE
                    context.showMessage("Error", it.message.toString())
                }
                is DatabaseResult.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.username.setText("")
                    binding.email.setText("")
                    binding.password.setText("")
                    context.showMessage("Success", it.message.toString())
                }
            }
        }

        binding.submit.setOnClickListener {
            signup()
        }

    }

    private fun signup() {
        val user = UserTable(
            binding.username.text.toString().trim(),
            binding.email.text.toString().trim(),
            binding.password.text.toString().trim(),
        )

        viewModel.signup(user)
    }
}