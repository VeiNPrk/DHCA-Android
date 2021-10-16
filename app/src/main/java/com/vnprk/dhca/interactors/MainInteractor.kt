package com.vnprk.dhca.interactors

import com.vnprk.dhca.RepositoryImpl
import javax.inject.Inject
/*
* Объединяет главный экран и детальный фрагмент
*/
class MainInteractor @Inject constructor(private val repository: RepositoryImpl)  {

    suspend fun getAllProjects() = repository.getAllProjects()

    suspend fun initRemoteProjects(){
        repository.initRemoteProjects()
    }

    fun getStateFlow() = repository.getStatusFlow()

}
