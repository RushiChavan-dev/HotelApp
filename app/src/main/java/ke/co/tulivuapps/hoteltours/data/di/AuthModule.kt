package ke.co.tulivuapps.hoteltours.data.di

import androidx.compose.runtime.Stable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ke.co.tulivuapps.hoteltours.data.remote.api.AuthService
import ke.co.tulivuapps.hoteltours.data.remote.source.AuthRemoteDataSource
import ke.co.tulivuapps.hoteltours.data.remote.source.impl.AuthRemoteDataSourceImpl
import ke.co.tulivuapps.hoteltours.data.repository.AuthRemoteRepositoryImpl
import ke.co.tulivuapps.hoteltours.data.repository.AuthRepositoryGoogleImpl
import ke.co.tulivuapps.hoteltours.domain.repository.AuthRemoteRepository
import ke.co.tulivuapps.hoteltours.domain.repository.AuthRepositoryGoogle
import retrofit2.Retrofit

/**
 * Created by Rushi on 12.03.2023
 */
@Stable
@Module
@InstallIn(ViewModelComponent::class)
class AuthModule {
    @Provides
    fun provideAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)
    @Provides
    fun provideAuthRepositoryGoogle( ): AuthRepositoryGoogle =
        AuthRepositoryGoogleImpl()
    @Provides
    fun provideAuthRemoteRepository(authRemoteDataSource: AuthRemoteDataSource): AuthRemoteRepository =
        AuthRemoteRepositoryImpl(authRemoteDataSource)

    @Provides
    fun provideAuthRemoteDataSource(authService: AuthService): AuthRemoteDataSource =
        AuthRemoteDataSourceImpl(authService)
}