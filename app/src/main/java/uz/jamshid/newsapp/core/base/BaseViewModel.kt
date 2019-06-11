package uz.jamshid.newsapp.core.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import uz.jamshid.newsapp.core.manager.DataManager

abstract class BaseViewModel(val mDataManager: DataManager): ViewModel() {

    var compositeDisposable: CompositeDisposable = CompositeDisposable()
    var failure: MutableLiveData<String> = MutableLiveData()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}