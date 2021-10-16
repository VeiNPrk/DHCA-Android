package com.vnprk.dhca.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.vnprk.dhca.models.ProjectDataEntity
import com.vnprk.dhca.models.ProjectEntity
import com.vnprk.dhca.models.ResponseDataTest

@Database(entities = [
    ProjectEntity::class,
    ProjectDataEntity::class
], version = 1)
abstract class DBRoomDhca : RoomDatabase() {
    abstract fun dhcaDao(): DaoDhca
}
/*
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        //database.execSQL("ALTER TABLE Holiday ADD COLUMN link TEXT")
        //database.execSQL("ALTER TABLE FactoryDetail ADD COLUMN endYear INTEGER")
    }
}*/