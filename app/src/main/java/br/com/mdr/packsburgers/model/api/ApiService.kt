package br.com.mdr.packsburgers.model.api

import br.com.mdr.packsburgers.model.PedidoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/orders")
    fun getPedidos(@Query("token") token: String): Call<PedidoResponse>
}