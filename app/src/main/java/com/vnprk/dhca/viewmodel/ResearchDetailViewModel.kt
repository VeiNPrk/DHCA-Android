package com.vnprk.dhca.viewmodel

import android.app.Application
import androidx.lifecycle.*
import androidx.work.*
import com.vnprk.dhca.App
import com.vnprk.dhca.interactors.MainInteractor
import com.vnprk.dhca.interactors.ResearchDetailInteractor
import com.vnprk.dhca.models.CalculateWorker
import com.vnprk.dhca.models.ProjectEntity
import com.vnprk.dhca.models.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ResearchDetailViewModel(application: Application) : AndroidViewModel(application) {
    @Inject
    lateinit var interactor: ResearchDetailInteractor
    private val _stateResearchFlow = MutableStateFlow(Result.nothing<Any>())
    private val stateResearchFlow: StateFlow<Result<Any>> = _stateResearchFlow.asStateFlow()
    private val workManager = WorkManager.getInstance(application)
    /*private val project: LiveData<List<ProjectEntity>> = liveData {
        emitSource(interactor.getProject().asLiveData())
    }*/
/*
    private val _stateUi = MutableLiveData<String>()
    val stateUiLiveData: LiveData<String>
        get() = _stateUi

    private val _errors = MutableLiveData<String>()
    val errorsLiveData: LiveData<String>
        get() = _errors
*/
    init{
        getApplication<App>().appComponent.inject(this)
    }

    fun getProjectByIdLiveData(idProject : Int) = liveData {
        emitSource(interactor.getProjectById(idProject).asLiveData())
    }

    fun getStateRepositoryLiveData() = interactor.getStateRepositoryFlow().asLiveData()

    fun getStateResearchLiveData() = stateResearchFlow.asLiveData()

    fun startOnclick(idProject:Int) {
       /* viewModelScope.launch {
            interactor.startResearchProject(idProject)
        }*/
        /*val constraints =
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()*/
        val cancelWorkRequest = OneTimeWorkRequestBuilder<CalculateWorker>()
            //.setConstraints(constraints)
            //.setInputData(cancelData)
            .build()
        workManager
            //.getInstance(context)
            .enqueue(cancelWorkRequest)
    }


    /*fun initRemoteProjects(){
        viewModelScope.launch {
            interactor.initRemoteProjects()
        }
    }*/

}