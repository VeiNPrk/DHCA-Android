package com.vnprk.dhca

import com.vnprk.dhca.models.ProjectDataEntity
import com.vnprk.dhca.models.ProjectEntity
import kotlinx.coroutines.flow.StateFlow
import com.vnprk.dhca.models.Result
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun authorization(login: String, password: String, token: String)

    suspend fun registration(login: String, password: String, token: String)

    suspend fun getAllProjects(): Flow<List<ProjectEntity>>

    suspend fun getProjectById(idProject:Int): Flow<ProjectEntity>

    suspend fun initRemoteProjects()

    suspend fun getDataProjectById(idProject:Int): Flow<List<ProjectDataEntity>>

    fun getStatusFlow() : StateFlow<Result<Any>>
}