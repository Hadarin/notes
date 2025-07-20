package com.domain.notification

import com.domain.model.PersonModel

class HandlePersonNotificationUseCase(
    private val notificationHandler: NotificationHandler
) {
    suspend operator fun invoke(person: PersonModel) {
        notificationHandler.handlePersonNotification(person)
    }
}