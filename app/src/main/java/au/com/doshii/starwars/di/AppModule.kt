package au.com.doshii.starwars.di

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class AppModule(val application: Application) {

    @Provides
    open fun provideApplication():Application = application
}