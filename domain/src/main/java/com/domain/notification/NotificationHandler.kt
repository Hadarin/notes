package com.domain.notification

import com.domain.model.PersonModel

interface NotificationHandler {
    suspend fun handlePersonNotification(person: PersonModel)
}