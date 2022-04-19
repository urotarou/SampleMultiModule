package lab.uro.kitori.samplemultimodule.data.repository

import android.net.Uri
import lab.uro.kitori.samplemultimodule.data.entity.UserEntity
import lab.uro.kitori.samplemultimodule.data.remote.api.GitHubApi
import javax.inject.Inject

class GitHubRepository @Inject constructor(
    private val api: GitHubApi
) {
    suspend fun getUsers() = api.getUsers().map { urlEntity ->
        val name = Uri.parse(urlEntity.url).lastPathSegment ?: ""
        UserEntity(name, urlEntity.url)
    }

    suspend fun getUser(name: String) = api.getUser(name).let {
        UserEntity(it.name, it.url)
    }
}
