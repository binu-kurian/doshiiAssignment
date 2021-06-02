package au.com.doshii.starwars.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import au.com.doshii.starwars.model.GsonRequest
import au.com.doshii.starwars.model.PeopleResult

abstract class BaseViewModel(application: Application):AndroidViewModel(application) {
    var url:String? = null
    abstract fun inject()
    abstract fun getData()
    val onNoData by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }
    open fun refresh() {
        inject()
        getData()
    }

}