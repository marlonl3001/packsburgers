package br.com.mdr.packsburgers.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.mdr.packsburgers.databinding.PedidoItemBinding
import br.com.mdr.packsburgers.model.Pedido
import br.com.mdr.packsburgers.view.adapter.viewholder.PedidoViewHolder

class PedidoAdapter: RecyclerView.Adapter<PedidoViewHolder>() {
    private var pedidos: MutableList<Pedido>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PedidoItemBinding.inflate(inflater, parent, false)
        return PedidoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PedidoViewHolder, position: Int) {
        val pedido = pedidos!![position]
        holder.bind(pedido, pedidoClickListener(pedido))
    }

    override fun getItemCount(): Int = if (pedidos != null) pedidos!!.size else 0

    private fun pedidoClickListener(pedido: Pedido): View.OnClickListener {
        return View.OnClickListener {

        }
    }

    fun updatePedidos(pedidosList: MutableList<Pedido>) {
        this.pedidos = pedidosList
        notifyDataSetChanged()
    }
}