package cn.onlyloveyd.androidpractice.result

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.onlyloveyd.androidpractice.databinding.ActivityTargetBinding

class TargetActivity : AppCompatActivity() {

    private val mBinding by lazy {
        ActivityTargetBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = intent.getStringExtra("name")
        mBinding.etMessage.setText(name)

        mBinding.btReturn.setOnClickListener {
            val intent = Intent().apply {
                putExtra("result", "Hello，我是回传的数据！")
            }
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}