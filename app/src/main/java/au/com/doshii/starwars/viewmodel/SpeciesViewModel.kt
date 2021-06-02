package au.com.doshii.starwars.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import au.com.doshii.starwars.di.AppModule
import au.com.doshii.starwars.di.DaggerViewModelComponent
import au.com.doshii.starwars.model.*
import com.android.volley.Response
import com.android.volley.VolleyError
import javax.inject.Inject

open class SpeciesViewModel(application: Application):BaseViewModel(application),
    Response.Listener<SpeciesResult>, Response.ErrorListener {

    constructor(application: Application,test:Boolean=true):this(application) {
        injected = true
    }

    @Inject
    lateinit var baseApi: BaseApi

    @Inject
    lateinit var starWarsApplication: Application

    private var injected = false;

    val speciesResult by lazy { MutableLiveData<SpeciesResult>() }

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

    open fun obtainGSonRequest():GsonRequest<SpeciesResult> =GsonRequest(url!!,SpeciesResult::class.java,this,
        this)

    override fun onResponse(response: SpeciesResult) {
        speciesResult.value = response
        onNoData.value = false
        loading.value = false
    }

    override fun onErrorResponse(error: VolleyError?) {
        speciesResult.value = null
        onNoData.value = true
        loading.value = false
    }
}