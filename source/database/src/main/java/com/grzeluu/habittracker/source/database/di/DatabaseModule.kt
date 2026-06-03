package com.grzeluu.habittracker.source.database.di

import androidx.room.Room
import com.grzeluu.habittracker.source.database.HabitTrackerDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            HabitTrackerDatabase::class.java,
            "Habit_tracker_database"
        ).build()
    }

    single { get<HabitTrackerDatabase>().habitDao() }
    single { get<HabitTrackerDatabase>().notificationDao() }
}