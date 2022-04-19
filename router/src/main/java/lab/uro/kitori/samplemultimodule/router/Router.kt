package lab.uro.kitori.samplemultimodule.router

import android.content.Context

interface Router {
    fun toList(context: Context)
    fun toDetail(context: Context, name: String)
}
