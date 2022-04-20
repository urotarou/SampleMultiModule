package lab.uro.kitori.samplemultimodule.domain.usecase

import lab.uro.kitori.samplemultimodule.data.repository.IGitHubRepository
import lab.uro.kitori.samplemultimodule.domain.model.User
import javax.inject.Inject

interface IGitHubUseCase {
    suspend fun getUsers(): List<User>
    suspend fun getUser(name: String): User
}

class GitHubUseCase @Inject constructor(
    private val repository: IGitHubRepository
) : IGitHubUseCase {
    override suspend fun getUsers(): List<User> = repository.getUsers().map {
        User(it.name, it.url)
    }

    override suspend fun getUser(name: String): User = repository.getUser(name).let {
        User(it.name, it.url)
    }
}
