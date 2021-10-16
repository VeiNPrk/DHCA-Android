package com.vnprk.dhca.models

import com.google.gson.annotations.SerializedName

data class ProjectAPI (
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("description") val description : String,
    @SerializedName("datetime_create") val datetimeCreate : String,
    @SerializedName("datetime_finish") val datetimeFinish : String?,
    @SerializedName("is_finish") val isFinish : Int
)