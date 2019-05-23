package br.com.mdr.packsburgers.model

import com.google.gson.annotations.SerializedName

class LoginRequest (
    @SerializedName("email") var email: String,
    @SerializedName("senha") var password: String)