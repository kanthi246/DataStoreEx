package com.example.datastoreex

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

const val PREFERENCE_NAME="mypreference"

class DataStore(var context:Context) {

    private val Context.dataStore by preferencesDataStore(
        name = PREFERENCE_NAME
    )

    fun <T> setValue(key: Preferences.Key<T>, value: T) {
        CoroutineScope(Dispatchers.IO).launch {
            context.dataStore.edit {
                it[key] = value
            }
        }
    }

    suspend fun <T> getValue(
        key: Preferences.Key<T>,
        defaultValue: T
    ): T {
        return context.dataStore.data.first()[key] ?: defaultValue
    }


    companion object{
        val PROFILE_NAME = stringPreferencesKey("PROFILE_NAME")
        val PROFILE_AGE = intPreferencesKey("PROFILE_AGE")
    }

}