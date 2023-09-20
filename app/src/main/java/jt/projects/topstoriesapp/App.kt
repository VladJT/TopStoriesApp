package jt.projects.topstoriesapp

import android.app.Application
import android.content.Context
import android.content.res.Resources
import jt.projects.topstoriesapp.di.appModule
import jt.projects.topstoriesapp.di.repoModule
import jt.projects.topstoriesapp.di.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    companion object {
        private var mContext: Context? = null

        fun getResources(): Resources {
            return mContext!!.resources
        }

        fun getContext(): Context {
            return mContext!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    appModule, repoModule, vmModule
                )
            )
        }
    }
}
