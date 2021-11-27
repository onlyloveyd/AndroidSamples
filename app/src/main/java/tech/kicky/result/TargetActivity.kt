package tech.kicky.result

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tech.kicky.result.databinding.ActivityTargetBinding

class TargetActivity : AppCompatActivity() {

    private val mBinding by lazy {
        ActivityTargetBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        val name = intent.getStringExtra(SimpleActivityResultContract.EXTRA_IN)
        mBinding.etMessage.setText(name)

        mBinding.btReturn.setOnClickListener {
            val intent = Intent().apply {
                putExtra(SimpleActivityResultContract.EXTRA_OUT, mBinding.etMessage.text.toString())
            }
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}