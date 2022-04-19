package lab.uro.kitori.samplemultimodule.data.remote.response

import kotlinx.serialization.Serializable
import lab.uro.kitori.samplemultimodule.data.entity.UserUrlEntity

@Serializable
data class UsersResponse(
    val urls: List<UserUrlEntity>
)
