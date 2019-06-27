package br.com.mdr.packsburgers.view.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment

import br.com.mdr.packsburgers.databinding.LoginFragmentBinding
import br.com.mdr.packsburgers.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

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