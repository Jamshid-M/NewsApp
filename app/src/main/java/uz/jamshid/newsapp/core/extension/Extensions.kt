package uz.jamshid.newsapp.core.extension

import android.arch.lifecycle.*
import android.arch.lifecycle.ViewModelProvider.Factory
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import uz.jamshid.newsapp.R
import java.io.Serializable

fun ViewGroup.inflate(layoutRes: Int): View{
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun ImageView.loadImage(url: String?){
    if(!url.isNullOrEmpty())
        Picasso.get().load(url).into(this)
    else Picasso.get().load(R.drawable.noimg).into(this)
}

inline fun <reified T : ViewModel> Fragment.viewModel(factory: Factory, body: T.() -> Unit): T {
    val vm = ViewModelProviders.of(this, factory)[T::class.java]
    vm.body()
    return vm
}

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))

fun <L : LiveData<String>> LifecycleOwner.failure(liveData: L, body: (String?) -> Unit) =
    liveData.observe(this, Observer(body))

fun Fragment.putExtra(key: String, data: Serializable?): Fragment{
    if(data!=null)
        apply { arguments = Bundle().apply { putSerializable(key, data) } }
    return this
}

fun View.gone(){
    visibility = GONE
}

fun View.visible(){
    visibility = VISIBLE
}