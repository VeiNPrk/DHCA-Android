package com.vnprk.dhca.models

import com.google.gson.annotations.SerializedName

data class UserClass(
    @SerializedName("id") val id : Int,
    @SerializedName("login") val login : String,
    @SerializedName("mobile_token") val token : String
)