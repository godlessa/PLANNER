package com.example.planner.di

import android.arch.persistence.room.Room
import android.content.Context
import com.example.planner.data.local.database.AppDatabase
import com.example.planner.data.repository.MainRepository
import com.example.planner.domain.repository.IMainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provide main repository
     */
    @Singleton
    @Provides
    fun provideMainRepository(
        db: AppDatabase
    ): IMainRepository =
        MainRepository(db)

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }
}