package cn.onlyloveyd.androidpractice.result

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cn.onlyloveyd.androidpractice.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private val mBinding by lazy {
        ActivityResultBinding.inflate(layoutInflater)
    }

    private val myActivityLauncher =
        registerForActivityResult(JumpActivityResultContract()) { result ->
            Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
            mBinding.tvMessage.text = "回传数据：$result"
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        mBinding.btJump.setOnClickListener {
            myActivityLauncher.launch("Hello Target")
        }
    }

}