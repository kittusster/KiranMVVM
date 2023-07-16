package com.linoop.kiranmvvm.views.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.linoop.kiranmvvm.R
import com.linoop.kiranmvvm.databinding.FragmentLoginBinding
import com.linoop.kiranmvvm.utils.DatabaseResult
import com.linoop.kiranmvvm.viewmodels.MainViewModel
import com.linoop.kiranmvvm.views.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding

    private lateinit var viewModel: MainViewModel
    private lateinit var context: MainActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        context = activity as MainActivity
        viewModel = context.mainViewModel
        setupFragment()
    }

    private fun setupFragment() {
        binding.signup.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignupFragment())
        }

        binding.progressBar.visibility = View.GONE
        viewModel.loginResponse.observe(context){
            when (it) {
                is DatabaseResult.Error -> {
                    context.showMessage("Error", it.message.toString())
                    binding.progressBar.visibility = View.GONE
                }
                is DatabaseResult.Loading -> binding.progressBar.visibility = View.VISIBLE
                is DatabaseResult.Success -> {
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                    binding.progressBar.visibility = View.GONE
                    binding.username.setText("")
                    binding.password.setText("")
                }
            }
        }

        binding.login.setOnClickListener {
            login()
        }
    }

    private fun login() {
        viewModel.login(
            binding.username.text.toString().trim(),
            binding.password.text.toString().trim()
        )
    }
}