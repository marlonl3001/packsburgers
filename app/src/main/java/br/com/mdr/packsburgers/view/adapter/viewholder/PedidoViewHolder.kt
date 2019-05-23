package br.com.mdr.packsburgers.view.adapter.viewholder

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.mdr.packsburgers.PacksApp
import br.com.mdr.packsburgers.databinding.PedidoItemBinding
import br.com.mdr.packsburgers.model.Pedido
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class PedidoViewHolder(val binding: PedidoItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(pedido: Pedido, listener: View.OnClickListener) {
        binding.pedido = pedido
        binding.clickListener = listener
        Picasso.get().load(pedido.url).into(binding.imgPedido, object : Callback {
            override fun onSuccess() {
                Log.i("App", "Carregou a imagem")
            }

            override fun onError(e: Exception?) {
                Log.e("App", e!!.localizedMessage)
            }
        })
        binding.executePendingBindings()
    }
}