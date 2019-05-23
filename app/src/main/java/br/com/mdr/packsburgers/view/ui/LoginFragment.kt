package br.com.mdr.packsburgers.view.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.mdr.packsburgers.R
import br.com.mdr.packsburgers.databinding.LoginFragmentBinding
import br.com.mdr.packsburgers.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = LoginViewModel()
        val binding = LoginFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }
}