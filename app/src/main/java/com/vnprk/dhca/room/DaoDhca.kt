package com.vnprk.dhca.Room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.vnprk.dhca.models.*
import kotlinx.coroutines.flow.Flow

@Dao
/*interface*/abstract class DaoDhca {

    // LiveData is a data holder class that can be observed within a given lifecycle.
    // Always holds/caches latest version of data. Notifies its active observers when the
    // data has changed. Since we are getting all the contents of the database,
    // we are notified whenever any of the database contents have changed.
    /*@get:Query("SELECT * from signal ORDER BY time ASC")
    fun allSignalls: LiveData<List<UserClass>>*/

    @Query("SELECT * FROM projectentity order by id, isFinish")
    abstract fun getAllProjects(): Flow<List<ProjectEntity>>

    @Query("SELECT * FROM projectentity where id=:id order by id, isFinish")
    abstract fun getProjectById(id:Int): Flow<ProjectEntity>

    @Query("SELECT * FROM projectdataentity where idProject=:id order by numPart")
    abstract fun getProjectDataById(id:Int): Flow<List<ProjectDataEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertProjects(projects: List<ProjectEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertProjectData(projectData: List<ProjectDataEntity>)
/*
    @Query("SELECT * FROM holiday WHERE type=:type and ((day=:day and month=:month) or dayOfYear=:dayOfYear) order by ordered")
    abstract fun getHolidaysByType(type :Int, day:Int, month:Int, dayOfYear:Int): LiveData<List<Holiday>>

    @Query("SELECT * FROM holiday WHERE id=:id")
    abstract fun getHolidayById(id :Int): LiveData<Holiday>

    @Query("SELECT * FROM holiday WHERE id=:id")
    abstract fun getHolidayId(id :Int): Holiday

    @Query("SELECT * FROM holiday")
    abstract fun getHolidayForAlarm(): List<Holiday>

    @Query("SELECT * FROM holiday where isAlarm=1")
    abstract fun getHolidayActiveAlarm(): List<Holiday>

    @Query("SELECT * FROM holiday where type in(:types) order by dayOfYear, month, day, type, ordered, id")
    abstract fun getHolidayByType(types:List<Int>): List<Holiday>

    @Query("UPDATE holiday SET isAlarm=:isAlarm WHERE id=:id")
    abstract fun setIsAlarmHolidayById(id :Int, isAlarm:Boolean)

    @Query("UPDATE holiday SET isAlarm=0 WHERE isAlarm=1")
    abstract fun disactiveAllHoliday()

    @Update
    abstract suspend fun updateHolidays(holidays: List<Holiday>)

    @Query("UPDATE privateevent SET isActive=0 WHERE id=:id")
    abstract fun disactiveEventById(id :Int)

    @Query("SELECT * FROM privateevent order by startDateTime")
    abstract fun getAllPrivateEvents(): LiveData<List<PrivateEvent>>

    @Query("SELECT * FROM privateevent where isActive=1")
    abstract fun getPrivateActiveAlarm(): List<PrivateEvent>

    @Query("SELECT * FROM privateevent WHERE id=:id")
    abstract fun getPrivateEventById(id :Int): LiveData<PrivateEvent>

    @Query("SELECT * FROM privateevent WHERE id=:id")
    abstract fun getPrivateById(id :Int): PrivateEvent

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun synchronizeHolidays(holidays: List<Holiday>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertPrivateEvent(privateEvent: PrivateEvent) : Long

    @Update
    abstract suspend fun updatePrivateEvent(privateEvent: PrivateEvent)

    @Delete
    abstract suspend fun deletePrivateEvent(event: PrivateEvent)

    @Transaction
    open suspend fun savePrivateEvent(privateEvent: PrivateEvent):Int =
        if(privateEvent.id>0) {
            updatePrivateEvent(privateEvent)
            privateEvent.id
        }
        else
            insertPrivateEvent(privateEvent).toInt()

    @Transaction
    open suspend fun updateAlarmHolidays(holidays: List<Holiday>) {
        disactiveAllHoliday()
        updateHolidays(holidays)
    }*/
}