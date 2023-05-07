package hu.bme.aut.android.marvelcomicsfinder.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.android.marvelcomicsfinder.network.ApiInterceptor
import hu.bme.aut.android.marvelcomicsfinder.network.MarvelComicsService
import hu.bme.aut.android.marvelcomicsfinder.network.MarvelComicsServiceImpl
import hu.bme.aut.android.marvelcomicsfinder.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideMarvelComicsService(retrofit: Retrofit): MarvelComicsService {
        return retrofit.create(MarvelComicsServiceImpl::class.java)
    }

    @Singleton
    @Provides
    fun providesHttpApiInterceptor() = ApiInterceptor()

    @Singleton
    @Provides
    fun providesOkHttpClient(apiInterceptor: ApiInterceptor): OkHttpClient {
        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return OkHttpClient
            .Builder()
            .addInterceptor(apiInterceptor)
            .addInterceptor(interceptor)
            .build()
    }


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.baseURL)
        .client(okHttpClient)
        .build()
}