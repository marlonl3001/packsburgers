package br.com.mdr.packsburgers.viewmodel

import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import br.com.mdr.packsburgers.PacksApp
import br.com.mdr.packsburgers.R
import br.com.mdr.packsburgers.model.Pedido
import br.com.mdr.packsburgers.model.PedidoResponse
import br.com.mdr.packsburgers.model.api.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(var layoutCabecalho: ConstraintLayout) : ViewModel() {
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

    fun recyclerScrollListener(layoutCabecalho: ConstraintLayout) = object: RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val params = layoutCabecalho.layoutParams
            var currentHeight = params.height
            val maxHeight = convertDptoPx(PacksApp.activity.resources.getDimension(R.dimen.mainHeadSize).toInt())
            var currentAlpha = layoutCabecalho.alpha

            if (currentHeight < maxHeight) {
                if (dy > 0) {//Scroll para baixo
                    if (currentHeight < maxHeight && currentHeight > 0) {
                        currentHeight -= 10
                        currentAlpha -= 0.05f
                    }
                } else if (dy < 0 && currentHeight >= 0) {//Scroll para cima
                    currentHeight += 10
                    currentAlpha += 0.05f
                }
                currentAlpha = if (currentAlpha < 0) 0f else if (currentAlpha > 1.0) 1.0f else currentAlpha
                params.height = currentHeight
                layoutCabecalho.alpha = currentAlpha
                layoutCabecalho.layoutParams = params
            }
            Log.i("App", "HEIGHT: ${currentHeight}  MAX: ${maxHeight}")
            Log.i("App", "CURRENT ALPHA: ${currentAlpha}")
            Log.i("App", "SCROLL DIRECTION: ${dy}")
        }
    }

    private fun convertDptoPx(dp: Int): Int {
        val scale = PacksApp.activity.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }
}
