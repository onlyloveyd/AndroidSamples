package cn.onlyloveyd.androidpractice.result

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import cn.onlyloveyd.androidpractice.databinding.DialogResultSampleBinding

class ResultSampleDialog(context: Context, val registry: ActivityResultRegistry) : Dialog(context),
    DefaultLifecycleObserver {

    lateinit var mTargetLauncher: ActivityResultLauncher<String>

    private val mBinding by lazy {
        DialogResultSampleBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super<Dialog>.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.btLaunch.setOnClickListener {
            mTargetLauncher.launch(mBinding.etMessage.text.toString())
        }
    }

    override fun onCreate(owner: LifecycleOwner) {
        super<DefaultLifecycleObserver>.onCreate(owner)
        mTargetLauncher = registry.register("dialog", owner, JumpActivityResultContract()) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }


}