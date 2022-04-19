package lab.uro.kitori.samplemultimodule.presentation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    companion object {
        private const val KEY_EXTRA_NAME = "key_extra_name"

        fun createIntent(
            context: Context,
            name: String
        ) = Intent(context, DetailActivity::class.java).apply {
            putExtra(KEY_EXTRA_NAME, name)
        }
    }

    private val viewModel: DetailViewModel by viewModels()

    private val name by lazy {
        intent.getStringExtra(KEY_EXTRA_NAME) ?: ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DetailScreen(name = name)
        }

        viewModel.finish.observe(this) {
            finish()
        }
    }
}
