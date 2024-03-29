package ke.co.tulivuapps.hoteltours.data.di

import androidx.compose.runtime.Stable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ke.co.tulivuapps.hoteltours.data.local.dao.FavoriteDao
import ke.co.tulivuapps.hoteltours.data.remote.api.CharacterService
import ke.co.tulivuapps.hoteltours.data.remote.source.CharacterRemoteDataSource
import ke.co.tulivuapps.hoteltours.data.remote.source.impl.CharacterRemoteDataSourceImpl
import ke.co.tulivuapps.hoteltours.data.repository.CharacterRepositoryImpl
import ke.co.tulivuapps.hoteltours.domain.repository.CharacterRepository
import retrofit2.Retrofit

/**
 * Created by Rushi on 12.03.2023
 */
@Stable
@Module
@InstallIn(ViewModelComponent::class)
class CharacterModule {
    @Provides
    fun provideCharacterService(retrofit: Retrofit): CharacterService =
        retrofit.create(CharacterService::class.java)
    @Provides
    fun provideCharacterRemoteDataSource(
        characterService: CharacterService,
        dao: FavoriteDao
    ): CharacterRemoteDataSource =
        CharacterRemoteDataSourceImpl(characterService, dao)
    @Provides
    fun provideCharacterRepository(
        accountRemoteDataSource: CharacterRemoteDataSource,
        dao: FavoriteDao
    ): CharacterRepository =
        CharacterRepositoryImpl(accountRemoteDataSource, dao)
}