package ke.co.tulivuapps.hoteltours.data.di

import androidx.compose.runtime.Stable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ke.co.tulivuapps.hoteltours.data.local.dao.TravelStyleDao
import ke.co.tulivuapps.hoteltours.data.remote.api.TravelStyleService
import ke.co.tulivuapps.hoteltours.data.remote.source.TravelStyleRemoteDataSource
import ke.co.tulivuapps.hoteltours.data.remote.source.impl.TravelStyleRemoteDataSourceImpl
import ke.co.tulivuapps.hoteltours.data.repository.TravelStyleRepositoryImpl
import ke.co.tulivuapps.hoteltours.domain.repository.TravelStyleRepository
import retrofit2.Retrofit

/**
 * Created by Rushi on 12.03.2023
 */
@Stable
@Module
@InstallIn(ViewModelComponent::class)
class TravelStyleModule {
    @Provides
    fun provideTravelStyleService(retrofit: Retrofit): TravelStyleService =
        retrofit.create(TravelStyleService::class.java)

    @Provides
    fun provideTravelStyleRemoteDataSource(
        travelStyleService: TravelStyleService,
        dao: TravelStyleDao
    ): TravelStyleRemoteDataSource =
        TravelStyleRemoteDataSourceImpl(travelStyleService, dao)

    @Provides
    fun provideTravelStyleRepository(
        accountRemoteDataSource: TravelStyleRemoteDataSource,
        dao: TravelStyleDao
    ): TravelStyleRepository =
        TravelStyleRepositoryImpl(accountRemoteDataSource, dao)
}