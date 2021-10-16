package com.vnprk.dhca.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys= [ "idProject", "numPart" ] )
class ProjectDataEntity (
    val idProject : Int,
    val numPart : Int,
    val data : String,
    val result : String?,
    val datetimeSend : Long?,
    val datetimeStart : Long?,
    val datetimeFinish : Long?
)