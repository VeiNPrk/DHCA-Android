package com.vnprk.dhca.interactors

import com.vnprk.dhca.RepositoryImpl
import javax.inject.Inject
/*
* Объединяет регистрацию и авторизацию
*/
class AuthorizationInteractor @Inject constructor(private val repository: RepositoryImpl)  {

    suspend fun registration(login: String, password: String, token: String){
        repository.registration(login, password, token)
    }

    suspend fun authorization(login: String, password: String, token: String){
        repository.authorization(login, password, token)
    }

    fun getStateFlow() = repository.getStatusFlow()

}
