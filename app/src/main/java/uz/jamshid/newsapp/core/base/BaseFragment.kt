package uz.jamshid.newsapp.core.base

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.jamshid.newsapp.NewsApp
import uz.jamshid.newsapp.core.di.AppComponent
import uz.jamshid.newsapp.core.extension.inflate
import javax.inject.Inject

abstract class BaseFragment: Fragment() {

    val appComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity?.application as NewsApp).appComponent
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(getLayoutId())
    }

    abstract fun getLayoutId() : Int

}