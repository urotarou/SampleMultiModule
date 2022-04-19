package lab.uro.kitori.samplemultimodule.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import lab.uro.kitori.samplemultimodule.PresentationRouter
import lab.uro.kitori.samplemultimodule.router.Router
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Singleton
    @Binds
    abstract fun provideRouter(
        router: PresentationRouter
    ): Router
}
