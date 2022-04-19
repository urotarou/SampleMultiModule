package lab.uro.kitori.samplemultimodule.domain.usecase

import lab.uro.kitori.samplemultimodule.data.repository.GitHubRepository
import lab.uro.kitori.samplemultimodule.domain.model.User
import javax.inject.Inject

class GitHubUseCase @Inject constructor(
    private val repository: GitHubRepository
) {
    suspend fun getUsers() = repository.getUsers().map {
        User(it.name, it.url)
    }

    suspend fun getUser(name: String) = repository.getUser(name).let {
        User(it.name, it.url)
    }
}
