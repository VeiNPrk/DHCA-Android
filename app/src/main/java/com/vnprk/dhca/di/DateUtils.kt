package com.vnprk.dhca.di

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateUtils {
    companion object {
        fun stringToLong(dt:String):Long {
            //String dtStart = "2010-10-15T09:27:37Z";
            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            var dtMills = 0L
            try {
                val date = format.parse(dt);
                dtMills = date.time
            } catch ( e: ParseException) {
                e.printStackTrace();
            }
            return dtMills
        }

        fun longToDateTimeStr(timeMills: Long?) :String {
            val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
            //dateFormat.timeZone = TimeZone.getTimeZone("GMT")
            //String.format()
            return if(timeMills!=null && timeMills!=0L) dateFormat.format(Date(timeMills)) else " - "
        }
    }
}