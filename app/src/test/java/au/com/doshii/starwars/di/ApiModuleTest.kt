package au.com.doshii.starwars.di

import android.app.Application
import au.com.doshii.starwars.model.BaseApi

class ApiModuleTest(val mockApi: BaseApi):ApiModule() {
    override fun provideBaseApi(application: Application): BaseApi {
        return mockApi
    }
}