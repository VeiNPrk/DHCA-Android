package com.vnprk.dhca

import android.content.Context
import androidx.preference.PreferenceManager

class SharedPreferencesUtils{
    companion object {
        const val PREFERENCE_MY_LOGIN = "preference_my_login"
        const val PREFERENCE_MY_TOKEN = "preference_my_token"
        const val PREFERENCE_MY_ID = "preference_my_id"
        const val SETTING_HAS_CHILD = "setting_has_child"
        const val SETTING_HAS_ANIMAL = "setting_has_animal"
        const val SETTING_TIME_FINISH = "setting_finish_time"
        const val SETTING_SECOND_PLAYER = "setting_second_player"
        const val SETTING_DEFAULT_TIME = "setting_default_time"
/*
        val NAME_PREF_VERSION_DB = "my_version_db"
        val NAME_PREF_DATE_UPDATE_DB = "my_date_update_db"
        val NAME_PREF_HAS_NEW_VERSION_DB = "my_has_new_version_db"

        fun isNetworkAvailable(context:Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
            return if (connectivityManager is ConnectivityManager) {
                val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
                networkInfo?.isConnected ?: false
            } else false
        }

        fun getSharedPreferences(context:Context?){
             context?.getSharedPreferences(NAME_PREFS, AppCompatActivity.MODE_PRIVATE)
        }

        fun getString(context:Context, namePrefs:String) = context.getSharedPreferences(NAME_PREFS, MODE_PRIVATE).getString(namePrefs, "")

        fun getInt(context:Context, namePrefs:String) = context.getSharedPreferences(NAME_PREFS, MODE_PRIVATE).getInt(namePrefs, 0)
*/
        fun setMyId(context:Context, value:Int){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putInt(PREFERENCE_MY_ID, value)
            editor.apply()
        }

        fun getMyId(context:Context) = PreferenceManager.getDefaultSharedPreferences(context).getInt(
            PREFERENCE_MY_ID, 0)

        fun setMyLogin(context:Context, value:String){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putString(PREFERENCE_MY_LOGIN, value)
            editor.apply()
        }

        fun getMyLogin(context:Context) = PreferenceManager.getDefaultSharedPreferences(context).getString(
                PREFERENCE_MY_LOGIN, "")

        fun setMyToken(context:Context, value:String){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putString(PREFERENCE_MY_TOKEN, value)
            editor.apply()
        }

        fun getMyToken(context:Context) = PreferenceManager.getDefaultSharedPreferences(context).getString(
            PREFERENCE_MY_TOKEN, "")

        /*fun getHasAnimal(context:Context) = PreferenceManager.getDefaultSharedPreferences(context).getBoolean(
            SETTING_HAS_ANIMAL, false)

        private fun getDefaultTime(context:Context) = PreferenceManager.getDefaultSharedPreferences(context).getBoolean(
                SETTING_DEFAULT_TIME, false)

        fun getFinishTime(context:Context) = if(getDefaultTime(context)) 0 else PreferenceManager.getDefaultSharedPreferences(context).getLong(
            SETTING_TIME_FINISH, 0)

        fun getSecondPlayer(context:Context) = PreferenceManager.getDefaultSharedPreferences(context).getString(
                SETTING_SECOND_PLAYER, "0")?.toInt()

        fun setDefaultSecondPlayer(context: Context){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putString(SETTING_SECOND_PLAYER, "0")
            editor.apply()
        }*/

        /*fun setUser(context:Context, user: UserClass){
          val editor = context.getSharedPreferences(NAME_PREFS, MODE_PRIVATE).edit()
          editor.putInt(NAME_PREF_USER_ID, user.id)
          editor.apply()
          editor.putInt(NAME_PREF_USER_ENT, user.idEnt)
          editor.apply()
        }*/

   /*     fun setString(context:Context, namePrefs:String, value:String){
          val editor = context.getSharedPreferences(NAME_PREFS, MODE_PRIVATE).edit()
          editor.putString(namePrefs, value)
          editor.apply()
        }

        fun setBoolean(context:Context, namePrefs:String, value:Boolean){
            val editor = context.getSharedPreferences(NAME_PREFS, MODE_PRIVATE).edit()
            editor.putBoolean(namePrefs, value)
            editor.apply()
        }

        fun getBoolean(context:Context, namePrefs:String)= context.getSharedPreferences(NAME_PREFS, MODE_PRIVATE).getBoolean(
            namePrefs, false)*/
    }
}