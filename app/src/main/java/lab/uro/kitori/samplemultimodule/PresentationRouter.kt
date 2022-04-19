package lab.uro.kitori.samplemultimodule

import android.content.Context
import lab.uro.kitori.samplemultimodule.presentation.detail.DetailActivity
import lab.uro.kitori.samplemultimodule.presentation.list.ListActivity
import lab.uro.kitori.samplemultimodule.router.Router
import javax.inject.Inject

class PresentationRouter @Inject constructor() : Router {
    override fun toList(context: Context) {
        context.startActivity(
            ListActivity.createIntent(context)
        )
    }

    override fun toDetail(
        context: Context,
        name: String
    ) {
        context.startActivity(
            DetailActivity.createIntent(context, name)
        )
    }
}
