package au.com.doshii.starwars.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import au.com.doshii.starwars.di.AppModule
import au.com.doshii.starwars.di.DaggerViewModelComponent
import au.com.doshii.starwars.model.BaseApi
import au.com.doshii.starwars.model.GsonRequest
import au.com.doshii.starwars.model.PeopleResult
import com.android.volley.Response
import com.android.volley.VolleyError
import javax.inject.Inject

open class PeopleViewModel(application: Application):BaseViewModel(application),
    Response.Listener<PeopleResult>, Response.ErrorListener {

    constructor(application: Application,test:Boolean=true):this(application) {
        injected = true
    }

    @Inject
    lateinit var baseApi: BaseApi

    @Inject
    lateinit var starWarsApplication: Application


    private var injected = false;

    val peopleResult by lazy { MutableLiveData<PeopleResult>() }

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

    open fun obtainGSonRequest(): GsonRequest<PeopleResult> =
        GsonRequest(url!!, PeopleResult::class.java,this,
            this)

    override fun onResponse(response: PeopleResult) {
        peopleResult.value = response
        onNoData.value = false
        loading.value = false
    }

    override fun onErrorResponse(error: VolleyError?) {
        peopleResult.value = null
        onNoData.value = true
        loading.value = false
    }
}