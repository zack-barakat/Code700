package com.android.code700.data.prefs

import android.content.Context
import android.content.SharedPreferences
import com.android.code700.di.qualifiers.ApplicationContext
import com.android.code700.di.scopes.ApplicationScope
import javax.inject.Inject

interface IPreferencesHelper {
}

@ApplicationScope
class AppPreferencesHelper @Inject
constructor(@ApplicationContext context: Context) : IPreferencesHelper {

    companion object {
        private const val PREF_FILE_NAME = "CODE_700"
    }

    private val mSharedPreferences: SharedPreferences
    private val mPreferenceEditors
        get() = mSharedPreferences.edit()

    init {
        mSharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
    }
}
