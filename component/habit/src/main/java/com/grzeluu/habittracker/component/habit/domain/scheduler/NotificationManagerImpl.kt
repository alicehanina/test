package com.grzeluu.habittracker.component.habit.domain.scheduler

import com.grzeluu.habittracker.component.habit.domain.model.Habit
import com.grzeluu.habittracker.component.habit.domain.model.HabitNotification
import com.grzeluu.habittracker.component.habit.domain.model.HabitNotificationSetting
import com.grzeluu.habittracker.util.enums.Day
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock.System

class NotificationManagerImpl : NotificationManager {

    override suspend fun calculateNextNotificationForHabit(habit: Habit): HabitNotification {
        val currentTime = System.now().toLocalDateTime(TimeZone.currentSystemDefault())

        if (habit.notification !is HabitNotificationSetting.Enabled) throw RuntimeException("Habit notification is already disabled")
        var notificationDateTime = LocalDateTime(currentTime.date, habit.notification.time)

        while (notificationDateTime <= currentTime || !habit.desirableDays.contains(Day.get(notificationDateTime.dayOfWeek.ordinal + 1))) {
            notificationDateTime = LocalDateTime(notificationDateTime.date.plus(1, DateTimeUnit.DAY), habit.notification.time)
        }

        return HabitNotification(
            habit = habit,
            dateTime = notificationDateTime
        )
    }
}
