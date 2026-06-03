package com.grzeluu.habittracker.di

import androidx.lifecycle.SavedStateHandle
import com.grzeluu.habittracker.activity.ui.MainActivityViewModel
import com.grzeluu.habittracker.feature.addhabit.ui.AddHabitViewModel
import com.grzeluu.habittracker.feature.calendar.ui.HabitsListViewModel
import com.grzeluu.habittracker.feature.details.ui.DetailsViewModel
import com.grzeluu.habittracker.feature.home.ui.HomeViewModel
import com.grzeluu.habittracker.feature.home.ui.page.DailyViewModel
import com.grzeluu.habittracker.feature.onboarding.ui.OnboardingViewModel
import com.grzeluu.habittracker.feature.settings.ui.SettingsViewModel
import kotlinx.datetime.LocalDate
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainActivityViewModel(get(), get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { (date: LocalDate) -> DailyViewModel(date, get(), get()) }
    viewModel { HabitsListViewModel(get()) }
    viewModel { SettingsViewModel(get(), get()) }
    viewModel { OnboardingViewModel(get(), get()) }
    viewModel { (savedStateHandle: SavedStateHandle) ->
        AddHabitViewModel(get(), get(), get(), savedStateHandle)
    }
    viewModel { (savedStateHandle: SavedStateHandle) ->
        DetailsViewModel(get(), get(), get(), get(), savedStateHandle)
    }
}
