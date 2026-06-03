package com.grzeluu.habittracker.component.settings.di

import com.grzeluu.habittracker.component.settings.data.repository.SettingsRepositoryImpl
import com.grzeluu.habittracker.component.settings.domain.repository.SettingsRepository
import com.grzeluu.habittracker.component.settings.domain.usecase.CheckIsSettingsDefinedUseCase
import com.grzeluu.habittracker.component.settings.domain.usecase.GetSettingsUseCase
import com.grzeluu.habittracker.component.settings.domain.usecase.SaveSettingsUseCase
import org.koin.dsl.module

val settingsModule = module {
    single<SettingsRepository> { SettingsRepositoryImpl(get()) }

    factory { CheckIsSettingsDefinedUseCase(get()) }
    factory { GetSettingsUseCase(get()) }
    factory { SaveSettingsUseCase(get()) }
}