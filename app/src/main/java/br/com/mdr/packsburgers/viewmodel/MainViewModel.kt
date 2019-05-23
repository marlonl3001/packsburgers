package br.com.mdr.packsburgers.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import br.com.mdr.packsburgers.model.Pedido
import br.com.mdr.packsburgers.model.PedidoResponse
import br.com.mdr.packsburgers.model.api.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    var pedidosF = MutableLiveData<MutableList<Pedido>>()
    var pedidosA = MutableLiveData<MutableList<Pedido>>()
    var isLoading = MutableLiveData<Boolean>().apply { value = false }

    fun fetchPedidos() {
        isLoading.value = true
        val pedidosList = mutableListOf<Pedido>()
        for (i in 1..10) {
            pedidosList.add(Pedido(i, "Pedido ${i}", "10/10/2019", "10/10/2019",
                "http://media-cdn.tripadvisor.com/media/photo-s/0c/c1/0c/ee/nosso-pedido-no-restaurante.jpg"))
        }
        pedidosF.value = pedidosList
        val pedidosAList = mutableListOf<Pedido>()
        for (i in 1..10) {
            pedidosAList.add(Pedido(i, "Pedido ${i}", "10/10/2019", "",
                "http://media-cdn.tripadvisor.com/media/photo-s/0c/c1/0c/ee/nosso-pedido-no-restaurante.jpg"))
        }
        pedidosA.value = pedidosAList
        isLoading.value = false
//        ApiClient.instance.getPedidos("").enqueue(object : Callback<PedidoResponse> {
//            override fun onResponse(call: Call<PedidoResponse>, response: Response<PedidoResponse>) {
//                isLoading.value = false
//                if (response.isSuccessful) {
//                    pedidos.value = response.body()!!.pedidos
//                }
//            }
//
//            override fun onFailure(call: Call<PedidoResponse>, t: Throwable) {
//                isLoading.value = false
//            }
//        })
    }

    fun recyclerScrollListener() = RecyclerView.OnScrollListener()

    }
}
