package com.vnprk.dhca.di

import com.vnprk.dhca.DhcaApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vnprk.dhca.BuildConfig
import com.vnprk.dhca.BuildConfig.SERVER_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder()./*excludeFieldsWithoutExposeAnnotation().*/setLenient().create()

    @Provides
    fun provideInpApi(gson:Gson): DhcaApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(DhcaApi::class.java)
    }
}