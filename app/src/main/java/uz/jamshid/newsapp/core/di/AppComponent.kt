package uz.jamshid.newsapp.core.di

import dagger.Component
import uz.jamshid.newsapp.NewsApp
import uz.jamshid.newsapp.core.di.viewmodel.ViewModelModule
import uz.jamshid.newsapp.ui.fragment.NewsListFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(application: NewsApp)
    fun inject(newsListFragment: NewsListFragment)
}