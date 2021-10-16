package com.vnprk.dhca.Room

import com.vnprk.dhca.DhcaApi
import com.vnprk.dhca.models.*
import retrofit2.Response
import javax.inject.Inject

class RemoteDb @Inject constructor(private val retrofit: DhcaApi) {
    //val retrofit = App.instance.getApi()

    suspend fun authorization(login:String, password:String, token:String): Result<ResponseData<UserClass>> {
        return getResponse { retrofit.authorization(login, password, token)}
    }/* = retrofit.authorization(login, password, token)*/

    suspend fun registration(login:String, password:String, token:String): Result<ResponseData<UserClass>> {
        return getResponse { retrofit.registration(login, password, token)}
    }

    suspend fun getAllProjects(): Result<ResponseData<ProjectAPI>> {
        return getResponse { retrofit.getProjects() }
    }

    suspend fun getProjectData(idProject:Int): Result<ResponseData<ProjectDataAPI>> {
        return getResponse { retrofit.getProjectData(idProject) }
    }
    /* = retrofit.registration(login, password, token)*/
/*
    suspend fun getInitData(idEnt:Int) = retrofit.getInitData(idEnt)

    suspend fun synchronizeCastData(idUser:Int, idEnt:Int, detailsJson:String) = retrofit.synchronizeCastData(idUser, idEnt, detailsJson)

    suspend fun synchronizeNotNumData(idUser:Int, idEnt:Int, detailsJson:String) = retrofit.synchronizeNotNumData(idUser, idEnt, detailsJson)

    suspend fun getSearchNumDetails(type:Int, idEntUser:Int, searchString:String) = retrofit.getSearchNumDetails(type, idEntUser, searchString) //{

    suspend fun getSearchNotNumDetails(type:Int, idEntUser:Int, searchString:String)= retrofit.getSearchNotNumDetails(type, idEntUser, searchString) // {
*/

    private suspend fun <T> getResponse(request: suspend () -> Response<T>): Result<T> {
        return try {
            val result = request.invoke()
            if (result.isSuccessful) {
                return Result.success(result.body())
            } else {
                val errorMessage = result.message()
                Result.error(errorMessage)
            }
        } catch (e: Throwable) {
            Result.error(e.message.toString())
        }
    }
}