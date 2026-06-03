package com.grzeluu.habittracker.base.di

import com.grzeluu.habittracker.base.ui.state.LoadingState
import org.koin.dsl.module

val baseModule = module {
    factory { LoadingState() }
}