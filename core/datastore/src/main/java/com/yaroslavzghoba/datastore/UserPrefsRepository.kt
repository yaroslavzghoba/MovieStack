package com.yaroslavzghoba.datastore

import com.yaroslavzghoba.model.UserPreferences
import kotlinx.coroutines.flow.Flow

/**Provides possibility to store user preferences locally*/
interface UserPrefsRepository {

    /**
     * Get user preferences
     *
     * @return a flow of [UserPreferences]
     * that contains user preferences saved before
     */
    fun getUserPreferences(): Flow<UserPreferences>

    /**
     * Update user preferences
     *
     * @param userPreferences a class that contains user preferences
     * those must replace old preferences
     */
    suspend fun updateUserPreferences(userPreferences: UserPreferences)
}