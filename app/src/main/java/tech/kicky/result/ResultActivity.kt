package tech.kicky.result

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import tech.kicky.result.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private val mBinding by lazy {
        ActivityResultBinding.inflate(layoutInflater)
    }

    private val mDialog by lazy {
        ResultSampleDialog(this, activityResultRegistry)
    }

    private val myActivityLauncher =
        registerForActivityResult(SimpleActivityResultContract()) { result ->
            Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
            mBinding.input.setText(result)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        lifecycle.addObserver(mDialog)

        mBinding.jump.setOnClickListener {
            myActivityLauncher.launch(mBinding.input.text.toString())
        }

        mBinding.dialog.setOnClickListener {
            mDialog.show()
        }
    }

}