package br.com.mdr.packsburgers.view.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import br.com.mdr.packsburgers.PacksApp
import br.com.mdr.packsburgers.databinding.UserDetailFragmentBinding
import br.com.mdr.packsburgers.model.Usuario
import br.com.mdr.packsburgers.viewmodel.UserDetailViewModel
import kotlinx.android.synthetic.main.user_detail_fragment.*

class UserDetailFragment : Fragment() {
    private lateinit var binding: UserDetailFragmentBinding

    companion object {
        private lateinit var usuario: Usuario
    }

    private lateinit var viewModel: UserDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UserDetailFragmentBinding.inflate(inflater)
        PacksApp.instance.setActionBar(binding.toolbar, true)
        PacksApp.activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserDetailViewModel::class.java)
        usuario = UserDetailFragmentArgs.fromBundle(arguments!!).user
        viewModel.user.value = usuario
        binding.viewModel = viewModel
    }

    override fun onResume() {
        super.onResume()
        toolbar.title = "Editar Informações"
        toolbar.setTitleTextColor(ContextCompat.getColor(activity!!.applicationContext, android.R.color.white))
    }
}