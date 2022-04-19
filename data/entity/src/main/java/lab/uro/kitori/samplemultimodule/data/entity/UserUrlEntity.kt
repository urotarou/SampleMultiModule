package lab.uro.kitori.samplemultimodule.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserUrlEntity(
    @SerialName("url") val url: String
)
