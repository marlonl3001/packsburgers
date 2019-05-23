package br.com.mdr.packsburgers.model

import com.google.gson.annotations.SerializedName

class Pedido(
    @SerializedName("id_pedido") var id: Int,
    @SerializedName("dec_pedido") var desc: String,
    @SerializedName("data_agendamento") var dataAgendamento: String,
    @SerializedName("data_entrega") var dataEntrega: String,
    @SerializedName("url") var url: String) {

    fun getDataPedido(): String =
        if (dataEntrega.isNotEmpty()) "Entregue: ${dataEntrega}" else "Agendado: ${dataAgendamento}"
}