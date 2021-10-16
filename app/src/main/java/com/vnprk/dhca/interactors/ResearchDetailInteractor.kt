package com.vnprk.dhca.interactors

import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.vnprk.dhca.RepositoryImpl
import com.vnprk.dhca.models.CalculateWorker
import javax.inject.Inject

class ResearchDetailInteractor @Inject constructor(private val repository: RepositoryImpl)  {

    suspend fun getProjectById(id:Int) = repository.getProjectById(id)

    suspend fun startResearchProject(id:Int){
        repository.getDataProjectById(id)

    }

    fun getStateRepositoryFlow() = repository.getStatusFlow()

}
