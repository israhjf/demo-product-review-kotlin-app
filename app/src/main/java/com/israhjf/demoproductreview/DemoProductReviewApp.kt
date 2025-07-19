package com.israhjf.demoproductreview

import android.app.Application
import android.content.ContextWrapper
import com.israhjf.demoproductreview.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

open class DemoProductReviewApp: Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialize Prefs
//        Prefs.Builder()
//            .setContext(this)
//            .setMode(ContextWrapper.MODE_PRIVATE)
//            .setPrefsName(PreferencesConstants.PREFERENCES_NAME)
//            .setUseDefaultSharedPreference(true)
//            .build()

        // Initialize Koin
        startKoin{
            androidLogger()
            androidContext(this@DemoProductReviewApp)
            modules(appModule)
        }
    }
}