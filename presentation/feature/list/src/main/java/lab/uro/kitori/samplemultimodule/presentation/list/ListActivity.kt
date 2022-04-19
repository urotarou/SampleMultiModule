package lab.uro.kitori.samplemultimodule.presentation.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import lab.uro.kitori.samplemultimodule.router.Router
import javax.inject.Inject

@AndroidEntryPoint
class ListActivity : AppCompatActivity() {
    companion object {
        fun createIntent(context: Context) = Intent(context, ListActivity::class.java)
    }

    private val viewModel: ListViewModel by viewModels()

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ListScreen()
        }

        viewModel.transition.observe(this) {
            router.toDetail(this, it)
        }

        viewModel.finish.observe(this) {
            finish()
        }
    }
}
