package ke.co.tulivuapps.hoteltours.data.di

import android.content.Context
import androidx.compose.runtime.Stable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ke.co.tulivuapps.hoteltours.HobbyHorseToursApp
import javax.inject.Singleton

/**
 * Created by Rushi on 01.09.2023
 */

@Stable
@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): HobbyHorseToursApp {
        return app as HobbyHorseToursApp
    }
}