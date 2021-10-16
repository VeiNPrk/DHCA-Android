package com.vnprk.dhca.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ResponseData<T>(
    @SerializedName("status") val status : Int,
    @SerializedName("message") val message : String,
    @SerializedName("result") val result : List<T>?/*,
    val versionDb :Int,
    val dateUpdateDb:String*/
)