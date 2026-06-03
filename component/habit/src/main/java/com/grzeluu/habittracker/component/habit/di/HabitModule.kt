package com.grzeluu.habittracker.component.habit.di

import com.grzeluu.habittracker.component.habit.data.repository.HabitRepositoryImpl
import com.grzeluu.habittracker.component.habit.data.repository.NotificationRepositoryImpl
import com.grzeluu.habittracker.component.habit.domain.repository.HabitRepository
import com.grzeluu.habittracker.component.habit.domain.repository.NotificationRepository
import com.grzeluu.habittracker.component.habit.domain.scheduler.NotificationManager
import com.grzeluu.habittracker.component.habit.domain.scheduler.NotificationManagerImpl
import com.grzeluu.habittracker.component.habit.domain.usecase.*
import com.grzeluu.habittracker.component.habit.infrastructure.NotificationScheduler
import com.grzeluu.habittracker.component.habit.infrastructure.NotificationSchedulerImpl
import org.koin.dsl.module

val habitModule = module {
    single<HabitRepository> { HabitRepositoryImpl(get()) }
    single<NotificationRepository> { NotificationRepositoryImpl(get(), get()) }
    single<NotificationManager> { NotificationManagerImpl() }
    single<NotificationScheduler> { NotificationSchedulerImpl(get()) }

    factory { AddOrUpdateHabitUseCase(get()) }
    factory { CheckIfHabitsAreAddedUseCase(get()) }
    factory { DeleteHabitUseCase(get()) }
    factory { GetDailyHabitInfosUseCase(get()) }
    factory { GetHabitsNotificationsUseCase(get()) }
    factory { GetHabitsUseCase(get()) }
    factory { GetHabitUseCase(get()) }
    factory { MarkHabitAsArchiveUseCase(get()) }
    factory { SaveHabitHistoryEntryUseCase(get()) }
}
