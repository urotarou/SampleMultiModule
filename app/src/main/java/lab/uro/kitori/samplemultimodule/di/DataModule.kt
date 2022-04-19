package lab.uro.kitori.samplemultimodule.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import lab.uro.kitori.samplemultimodule.data.remote.api.GitHubApi
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        addInterceptor(loggingInterceptor)

        connectTimeout(30, TimeUnit.SECONDS)
        callTimeout(30, TimeUnit.SECONDS)
    }.build()

    @Singleton
    @Provides
    fun provideGithubApi(
        client: OkHttpClient
    ): GitHubApi {
        val json = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.github.com")
            .addConverterFactory(
                @OptIn(ExperimentalSerializationApi::class)
                json.asConverterFactory("application/json".toMediaType())
            )
            .build()
            .create(GitHubApi::class.java)
    }
}
