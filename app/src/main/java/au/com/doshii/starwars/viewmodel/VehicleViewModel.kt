package au.com.doshii.starwars.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import au.com.doshii.starwars.di.AppModule
import au.com.doshii.starwars.di.DaggerViewModelComponent
import au.com.doshii.starwars.model.*
import com.android.volley.Response
import com.android.volley.VolleyError
import javax.inject.Inject

open class VehicleViewModel(application: Application):BaseViewModel(application),
    Response.Listener<VehiclesResult>, Response.ErrorListener {

    constructor(application: Application,test:Boolean=true):this(application) {
        injected = true
    }

    @Inject
    lateinit var baseApi: BaseApi

    @Inject
    lateinit var starWarsApplication: Application

    private var injected = false;

    val vehiclesResult by lazy { MutableLiveData<VehiclesResult>() }

    override fun inject() {
        if(!injected) {
            DaggerViewModelComponent
                .builder()
                .appModule(AppModule(getApplication()))
                .build()
                .inject(this)
        }
    }

    override fun getData() {
        loading.value = true
        baseApi.addToRequestQueue(obtainGSonRequest())
    }

    open fun obtainGSonRequest():GsonRequest<VehiclesResult> =GsonRequest(url!!,VehiclesResult::class.java,this,
        this)

    override fun onResponse(response: VehiclesResult) {
        vehiclesResult.value = response
        onNoData.value = false
        loading.value = false
    }

    override fun onErrorResponse(error: VolleyError?) {
        vehiclesResult.value = null
        onNoData.value = true
        loading.value = false
    }
}