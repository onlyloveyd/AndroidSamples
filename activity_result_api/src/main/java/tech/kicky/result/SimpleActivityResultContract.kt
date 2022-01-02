package tech.kicky.result

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract


class SimpleActivityResultContract : ActivityResultContract<String, String?>() {
    override fun createIntent(context: Context, input: String): Intent {
        return Intent(context, TargetActivity::class.java).apply {
            putExtra(EXTRA_IN, input)
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        val data = intent?.getStringExtra(EXTRA_OUT)
        return if (resultCode == Activity.RESULT_OK && data != null) data
        else null
    }

    companion object {
        const val EXTRA_IN = "extra_in"
        const val EXTRA_OUT = "extra_out"
    }
}
