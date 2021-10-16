package com.vnprk.dhca.di

import android.content.Context
import com.vnprk.dhca.viewmodel.AuthorizationViewModel
import com.vnprk.dhca.viewmodel.MainViewModel
import com.vnprk.dhca.viewmodel.ResearchDetailViewModel

import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, LocalDbModule::class])
interface ApplicationComponent {
    // This tells Dagger that LoginActivity requests injection so the graph needs to
    // satisfy all the dependencies of the fields that LoginActivity is requesting.
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder
        fun build(): ApplicationComponent
    }

    fun inject(viewModel: AuthorizationViewModel)
    fun inject(viewModel: MainViewModel)
    fun inject(viewModel: ResearchDetailViewModel)
    /*fun inject(viewModel: MainViewModel)
    fun inject(viewModel: CelebrationListViewModel)
    fun inject(viewModel: PrivateListViewModel)
    fun inject(viewModel: HolidayViewModel)
    fun inject(viewModel: PrivateEventViewModel)
    fun inject(alarmService:AlarmService)
    fun inject(alarmService: AlarmRebootService)
    fun inject(settingsFragment: SettingsFragment)
    fun inject(app:App)*/

}