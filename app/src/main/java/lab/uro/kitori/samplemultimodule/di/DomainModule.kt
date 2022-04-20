package lab.uro.kitori.samplemultimodule.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import lab.uro.kitori.samplemultimodule.domain.usecase.GitHubUseCase
import lab.uro.kitori.samplemultimodule.domain.usecase.IGitHubUseCase

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModuleBinder {
    @Binds
    abstract fun bindGitHubUseCase(
        useCase: GitHubUseCase
    ): IGitHubUseCase
}
