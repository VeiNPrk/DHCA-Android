package com.vnprk.dhca.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ResponseDataTest(
    @PrimaryKey
    val isSucces : Int,
    val message : String/*,
    val result : List<T>?,
    val versionDb :Int,
    val dateUpdateDb:String*/
)