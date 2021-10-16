package com.vnprk.dhca.models

import com.google.gson.annotations.SerializedName

class ProjectDataAPI (
    @SerializedName("id_project") val idProject : Int,
    @SerializedName("num_part") val numPart : Int,
    @SerializedName("data") val data : String,
    @SerializedName("result") val result : String?,
    @SerializedName("token_user") val tokenUser : String?,
    @SerializedName("datetime_send") val datetimeSend : Long?,
    @SerializedName("datetime_start") val datetimeStart : Long?,
    @SerializedName("datetime_finish") val datetimeFinish : Long?,
    @SerializedName("error") val error : String?
)

