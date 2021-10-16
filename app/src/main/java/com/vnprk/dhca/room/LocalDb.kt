package com.vnprk.dhca.Room

import com.vnprk.dhca.models.ProjectEntity
import javax.inject.Inject

class LocalDb @Inject constructor(private val inpDao: DaoDhca) {
    //private var inpDao: DaoInp
    init {
        //val db = App.instance.getDatabase()
        //inpDao = db!!.inpDao()

        //initTestData()
    }

    fun getAllProjects() = inpDao.getAllProjects()

    fun getProjectById(id:Int) = inpDao.getProjectById(id)

    fun getDataProjectById(id:Int) = inpDao.getProjectDataById(id)

    suspend fun insertProjects(projects : List<ProjectEntity>) = inpDao.insertProjects(projects)
/*
    fun getAllHolidays(day:Int, month:Int, dayOfYear:Int) = inpDao.getAllHolidays(day, month, dayOfYear)

    fun getAllPrivateEvents() = inpDao.getAllPrivateEvents()

    fun disactiveEventById(id :Int){
        inpDao.disactiveEventById(id)
    }

    suspend fun updateAlarmHolidays(holidays: List<Holiday>){
        inpDao.updateAlarmHolidays(holidays)
    }

    fun getHolidayByType(types:List<Int>) = inpDao.getHolidayByType(types)

    fun getHolidayActiveAlarm() = inpDao.getHolidayActiveAlarm()

    fun getHolidaysByType(type:Int, day:Int, month:Int, dayOfYear:Int) = inpDao.getHolidaysByType(type, day, month, dayOfYear)

    fun getHolidayById(id:Int) = inpDao.getHolidayById(id)

    fun getHolidayId(id:Int) = inpDao.getHolidayId(id)

    fun getPrivateActiveAlarm() = inpDao.getPrivateActiveAlarm()

    fun getPrivateEventById(id:Int) = inpDao.getPrivateEventById(id)

    fun getPrivateById(id:Int) = inpDao.getPrivateById(id)

    suspend fun synchronizeHolidays(holidays: List<Holiday>){
        inpDao.synchronizeHolidays(holidays)
    }

    suspend fun savePrivateEvent(event: PrivateEvent) = inpDao.savePrivateEvent(event)

    suspend fun deletePrivateEvent(event: PrivateEvent){ inpDao.deletePrivateEvent(event) }
*/
}