package tech.kicky.result

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import tech.kicky.result.databinding.DialogResultSampleBinding

class ResultSampleDialog(context: Context, private val registry: ActivityResultRegistry) :
    Dialog(context),
    DefaultLifecycleObserver {

    private lateinit var mTargetLauncher: ActivityResultLauncher<String>

    private val mBinding by lazy {
        DialogResultSampleBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super<Dialog>.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        mBinding.btLaunch.setOnClickListener {
            mTargetLauncher.launch(mBinding.etMessage.text.toString())
        }
        val display = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            context.display
        } else {
            window?.windowManager?.defaultDisplay
        }
        val params = window?.attributes
        display?.let {
            params?.width = it.width.times(0.9).toInt()
        }
        window?.attributes = params
    }

    override fun onCreate(owner: LifecycleOwner) {
        super<DefaultLifecycleObserver>.onCreate(owner)
        mTargetLauncher = registry.register("dialog", owner, SimpleActivityResultContract()) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            mBinding.etMessage.setText(it)
        }
    }


}