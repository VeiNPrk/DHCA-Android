package com.vnprk.dhca

import com.vnprk.dhca.models.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface DhcaApi {
/*	@FormUrlEncoded
	@POST("/service/set_phone_number.php")
	fun sendNumber(@Field("phone") phoneNumber:String, @Field("token") token:String): Call<ResponseClass>

	@FormUrlEncoded
	@POST("/service/authorization_user.php")
	suspend fun sendAuthCode(@Field("code") authCode:String, @Field("token") token:String): Response<UserClass>
*/
	/*
	@GET("get_holidays.php")
	suspend fun getHolidays(@Query("db_version") versionDb:Int): Response<ResponseData<Holiday>>

	@GET("get_holiday_db_version.php")
	suspend fun getDbVersion(): Response<ResponseData<Holiday>>
	*/


	@FormUrlEncoded
	@POST("authorization.php")
	suspend fun authorization(@Field("login") login:String, @Field("password") pasw:String, @Field("token") token:String): Response<ResponseData<UserClass>>

	@FormUrlEncoded
	@POST("registration.php")
	suspend fun registration(@Field("login") login:String, @Field("password") pasw:String, @Field("token") token:String): Response<ResponseData<UserClass>>

	@GET("get_projects.php")
	suspend fun getProjects(): Response<ResponseData<ProjectAPI>>

	@GET("get_projects_data.php")
	suspend fun getProjectData(@Query("id_project") idProject:Int): Response<ResponseData<ProjectDataAPI>>

	@POST("send_result.php")
	suspend fun sendResult(@Query("id_project") idProject:Int, @Query("num_part") numPart:Int, @Query("result") result:String?, @Query("error") error:String?): Response<ResponseData<Any>>
/*
	@GET("/service/get_search_data.php")
	suspend fun getSearchNumDetails(@Query("type") type:Int, @Query("id_ent") idEnt:Int, @Query("search") searchString:String): Response<ResponseData<DetailNum>>

	@GET("/service/get_search_data.php")
	suspend fun getSearchNotNumDetails(@Query("type") type:Int, @Query("id_ent") idEnt:Int, @Query("search") searchString:String): Response<ResponseData<DetailNotNum>>
	//fun getInitData(@Query("id_ent") idEnt:Int): Call<String>

	@GET("/service/get_control_details.php")
	fun getControlData(@Query("id_user") idUser:Int, @Query("id_ent") idEnt:Int): Response<ResponseData<DetailNum>>

	@FormUrlEncoded
	@POST("/service/sync_cast_data.php")
	suspend fun synchronizeCastData(@Field("id_user") idUser:Int, @Field("id_ent") idEnt:Int, @Field("details") detailsJson:String): Response<ResponseData<DetailNum>>

	@FormUrlEncoded
	@POST("/service/sync_notnum_data.php")
	suspend fun synchronizeNotNumData(@Field("id_user") idUser:Int, @Field("id_ent") idEnt:Int, @Field("details") detailsJson:String): Response<ResponseData<DetailNotNum>>
*/
}