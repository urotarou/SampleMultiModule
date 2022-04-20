package lab.uro.kitori.samplemultimodule.data.repository

import android.net.Uri
import lab.uro.kitori.samplemultimodule.data.entity.UserEntity
import lab.uro.kitori.samplemultimodule.data.remote.api.GitHubApi
import javax.inject.Inject

interface IGitHubRepository {
    suspend fun getUsers(): List<UserEntity>
    suspend fun getUser(name: String): UserEntity
}

class GitHubRepository @Inject constructor(
    private val api: GitHubApi
) : IGitHubRepository {
    override suspend fun getUsers(): List<UserEntity> = api.getUsers().map { urlEntity ->
        val name = Uri.parse(urlEntity.url).lastPathSegment ?: ""
        UserEntity(name, urlEntity.url)
    }

    override suspend fun getUser(name: String): UserEntity = api.getUser(name).let {
        UserEntity(it.name, it.url)
    }
}
