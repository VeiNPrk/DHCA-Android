package com.vnprk.dhca.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.vnprk.dhca.di.DateUtils

@Entity
class ProjectEntity (
    @PrimaryKey
    val id : Int,
    val name : String,
    val description : String,
    val datetimeCreate : Long,
    val datetimeFinish : Long?,
    val isFinish : Boolean
){
    fun strDateCreate():String{
        return DateUtils.longToDateTimeStr(datetimeCreate)
    }

    fun strDateFinish():String{
        return DateUtils.longToDateTimeStr(datetimeFinish)
    }

    fun getPercentComplete():String{
        return "1%"
    }
}