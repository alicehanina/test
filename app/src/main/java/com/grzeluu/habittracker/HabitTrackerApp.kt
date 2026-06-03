package com.grzeluu.habittracker

import android.app.Application
import com.grzeluu.habittracker.base.di.baseModule
import com.grzeluu.habittracker.component.habit.di.habitModule
import com.grzeluu.habittracker.component.habit.infrastructure.NotificationScheduler
import com.grzeluu.habittracker.component.settings.di.settingsModule
import com.grzeluu.habittracker.di.appModule
import com.grzeluu.habittracker.source.database.di.databaseModule
import com.grzeluu.habittracker.source.preferences.di.preferencesModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class HabitTrackerApp : Application() {

    val notificationManager: NotificationScheduler by inject()

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@HabitTrackerApp)
            modules(
                baseModule,
                databaseModule,
                preferencesModule,
                habitModule,
                settingsModule,
                appModule
            )
        }

        notificationManager.initNotificationChannel()
    }
}
