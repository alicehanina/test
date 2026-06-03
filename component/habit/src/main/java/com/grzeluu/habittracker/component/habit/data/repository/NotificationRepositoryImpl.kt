package com.grzeluu.habittracker.component.habit.data.repository

import android.content.Context
import com.grzeluu.habittracker.component.habit.data.mapper.mapToDomain
import com.grzeluu.habittracker.component.habit.data.mapper.mapToEntity
import com.grzeluu.habittracker.component.habit.domain.model.HabitNotification
import com.grzeluu.habittracker.component.habit.domain.repository.NotificationRepository
import com.grzeluu.habittracker.source.database.data.dao.NotificationDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class NotificationRepositoryImpl(
    private val notificationDao: NotificationDao,
    private val context: Context
) : NotificationRepository {
    override fun getHabitsNotification(): Flow<List<HabitNotification>> {
        return notificationDao.getAllNotifications().map { it.map { notification -> notification.mapToDomain() } }
    }

    override suspend fun addOrUpdateHabitNotification(habitNotification: HabitNotification) {
        notificationDao.addOrUpdateHabitNotification(habitNotification.mapToEntity())
    }

    override suspend fun deleteHabitNotificationByHabitId(notificationId: Long): HabitNotification? {
        val notification = notificationDao.getNotificationByHabitId(notificationId)
            .map { it?.mapToDomain() }
            .firstOrNull()
            ?: return null

        notificationDao.deleteHabitNotificationByHabitId(notificationId)
        return notification
    }
}
