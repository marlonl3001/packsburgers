package br.com.mdr.packsburgers.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.mdr.packsburgers.databinding.PedidosFragmentBinding
import br.com.mdr.packsburgers.model.Pedido
import br.com.mdr.packsburgers.view.adapter.PedidoAdapter
import br.com.mdr.packsburgers.viewmodel.MainViewModel

class PedidosFragment : Fragment() {
    private val adapter = PedidoAdapter()

    companion object {
        private lateinit var viewModel: MainViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = MainViewModel()
        val binding = PedidosFragmentBinding.inflate(inflater, container, false)
        binding.recyclerPedidos.adapter = adapter
        return binding.root
    }

    fun setPedidos(pedidos: MutableList<Pedido>) {
        adapter.updatePedidos(pedidos)
    }
}
