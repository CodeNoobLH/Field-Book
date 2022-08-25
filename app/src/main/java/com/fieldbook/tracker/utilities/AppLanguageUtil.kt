package com.fieldbook.tracker.utilities

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.preference.PreferenceManager
import com.fieldbook.tracker.preferences.GeneralKeys
import java.util.*

class AppLanguageUtil {

    companion object {

        const val TAG = "TextLangUtil"

        fun setDefaultLanguage(context: Context?) {

            PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putString(GeneralKeys.LANGUAGE_LOCALE_DEFAULT_ID, Locale.getDefault().language)
                .apply()
        }

        /**
         * Uses the preferences from Settings/Appearance/Language and androidx app compat
         * to set app-specific language.
         */
        fun refreshAppText(context: Context?) {

            try {

                val id = PreferenceManager.getDefaultSharedPreferences(context)
                    .getString(GeneralKeys.LANGUAGE_LOCALE_ID, "") ?: ""

                if (id.isNotEmpty()) {

                    Log.d(TAG, "Language set from preferences: $id")

                    AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(id))

                } else {

                    Log.d(TAG, "No language stored in preferences.")

                }

            } catch (e: Exception) {

                Log.e(TAG, "Error when setting language from preferences.", e)

            }
        }
    }
}