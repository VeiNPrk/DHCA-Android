package com.vnprk.dhca.di

import android.content.Context
import androidx.room.Room
import com.vnprk.dhca.Repository
import com.vnprk.dhca.RepositoryImpl
import com.vnprk.dhca.Room.DBRoomDhca
import com.vnprk.dhca.Room.DaoDhca
import com.vnprk.dhca.Room.LocalDb
import com.vnprk.dhca.Room.RemoteDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class LocalDbModule {

    @Provides
    @Singleton
    fun provideDBRoomInp(context: Context): DBRoomDhca {
        return Room.databaseBuilder(context, DBRoomDhca::class.java, "database")
            //.addMigrations(MIGRATION_1_2)
            .build()
    }

    @Provides
    @Singleton
    fun provideDaoInp(db: DBRoomDhca): DaoDhca {
        return db.dhcaDao()
    }

    @Provides
    @Singleton
    fun provideRepository(localDb: LocalDb, remoteDb: RemoteDb): Repository {
        return RepositoryImpl(localDb, remoteDb)
    }
}