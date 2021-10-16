package com.vnprk.dhca.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.vnprk.dhca.App
import com.vnprk.dhca.interactors.MainInteractor
import com.vnprk.dhca.models.ProjectEntity
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel(application: Application) : AndroidViewModel(application) {
    @Inject
    lateinit var interactor: MainInteractor
    private val projects: LiveData<List<ProjectEntity>> = liveData {
        emitSource(interactor.getAllProjects().asLiveData())
    }

    private val _stateUi = MutableLiveData<String>()
    val stateUiLiveData: LiveData<String>
        get() = _stateUi

    private val _errors = MutableLiveData<String>()
    val errorsLiveData: LiveData<String>
        get() = _errors

    init{
        getApplication<App>().appComponent.inject(this)
    }

    fun getProjectsLiveData() = projects

    fun initRemoteProjects(){
        viewModelScope.launch {
            interactor.initRemoteProjects()
        }
    }

}