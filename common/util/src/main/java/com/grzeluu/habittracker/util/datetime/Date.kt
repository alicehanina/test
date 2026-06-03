package com.grzeluu.habittracker.util.datetime

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock.System


fun getCurrentDate() = System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date