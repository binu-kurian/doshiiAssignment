package au.com.doshii.starwars.di

import android.app.Application
import au.com.doshii.starwars.model.BaseApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class ApiModule {

    @Provides
    @Singleton
    open fun provideBaseApi(application: Application):BaseApi {
        return BaseApi.getInstance(application)
    }
}