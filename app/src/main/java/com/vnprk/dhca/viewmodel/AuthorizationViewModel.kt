package com.vnprk.dhca.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.vnprk.dhca.App
import com.vnprk.dhca.SharedPreferencesUtils
import com.vnprk.dhca.interactors.AuthorizationInteractor
import com.vnprk.dhca.models.Status
import com.vnprk.dhca.models.Result
import com.vnprk.dhca.models.UserClass
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthorizationViewModel(application: Application) : AndroidViewModel(application) {
    /*@Inject
    lateinit var repository : Repository*/
    @Inject
    lateinit var authorizationInteractor : AuthorizationInteractor
    companion object {
        const val STATE_UI_LOGIN_NOT_VALID = "state_login_not_valid"
        const val STATE_UI_PASSWORD_NOT_VALID = "state_password_not_valid"
        const val STATE_UI_CANCEL_CLICK = "state_code_not_valid"
        const val STATE_UI_PASW_REPEATE_NOT_VALID = "state_pasw_repeate_not_valid"
        const val STATE_UI_TOKEN_IS_EMPTY = "state_token_is_empty"
        const val STATE_UI_LOADING = "state_loading"
        const val STATE_LAST_NAME_NOT_VALID = "state_last_name_not_valid"
        const val STATE_UI_GOTO_MAIN = "state_goto_main"
        const val STATE_UI_GOTO_AUTHORIZATION = "state_goto_auth"
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

    fun authorizationOnClick(login:String, password:String, token:String){
        if(login.isEmpty()){
            _stateUi.value = STATE_UI_LOGIN_NOT_VALID
            return
        }
        if(password.isEmpty()){
            _stateUi.value = STATE_UI_PASSWORD_NOT_VALID
            return
        }
        if(token.isEmpty()){
            _stateUi.value = STATE_UI_TOKEN_IS_EMPTY
            return
        }
        viewModelScope.launch {
            authorizationInteractor.authorization(login, password, token)
        }
    }

    fun observeState(context: Context, state:Result<Any>){
        when(state.status) {
            Status.LOADING -> _stateUi.value= STATE_UI_LOADING
            Status.SUCCESS ->
                if(state.data==null)_stateUi.value= STATE_UI_GOTO_AUTHORIZATION
            else {
                        val user = (state.data as List<UserClass>)
                        SharedPreferencesUtils.setMyId(context, user[0].id)
                        SharedPreferencesUtils.setMyLogin(context, user[0].login)
                        SharedPreferencesUtils.setMyToken(context, user[0].token)
                        _stateUi.value = STATE_UI_GOTO_MAIN
                    }
            Status.ERROR -> {
                _errors.value = state.message?:""
                //_stateUi.value = STATE_UI_ERROR
            }
            else -> {

            }
        }
    }

    fun getLoadState() = authorizationInteractor.getStateFlow().asLiveData()

    fun cancelOnClick(){
        _stateUi.value = STATE_UI_CANCEL_CLICK
    }

    /*fun goToRegistrationOnClick(){
        _stateUi.value = STATE_UI_GOTO_REGISTRATION
    }*/

    fun registrationOnClick(login: String, password: String, passwordRecovery: String, token: String){
        if(login.isEmpty()){
            _stateUi.value = STATE_UI_LOGIN_NOT_VALID
            return
        }
        if(password.isEmpty()){
            _stateUi.value = STATE_UI_PASSWORD_NOT_VALID
            return
        }
        if(passwordRecovery.isEmpty() || passwordRecovery != password){
            _stateUi.value = STATE_UI_PASW_REPEATE_NOT_VALID
            return
        }
        viewModelScope.launch {
            authorizationInteractor.registration(login, password, token)
        }
    }
}