package uz.jamshid.newsapp

import android.app.Application
import uz.jamshid.newsapp.core.di.AppComponent
import uz.jamshid.newsapp.core.di.AppModule
import uz.jamshid.newsapp.core.di.DaggerAppComponent
import io.reactivex.plugins.RxJavaPlugins



class NewsApp: Application() {

    val appComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
        RxJavaPlugins.setErrorHandler { throwable -> }
    }
}