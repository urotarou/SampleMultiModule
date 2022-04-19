package lab.uro.kitori.samplemultimodule.data.remote.api

import lab.uro.kitori.samplemultimodule.data.entity.UserUrlEntity
import lab.uro.kitori.samplemultimodule.data.remote.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("/users")
    suspend fun getUsers(): List<UserUrlEntity>

    @GET("/users/{name}")
    suspend fun getUser(
        @Path("name") name: String
    ): UserResponse
}
