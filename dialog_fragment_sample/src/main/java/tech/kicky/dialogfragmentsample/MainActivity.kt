package tech.kicky.dialogfragmentsample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import tech.kicky.dialogfragmentsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<SampleViewModel>()

    private val mBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        mBinding.btShow.setOnClickListener {
            val dialog = MessageDialog()
            dialog.show(supportFragmentManager, "Message Dialog")
        }

        lifecycleScope.launchWhenCreated {
            viewModel.count.collect {
                Toast.makeText(this@MainActivity, it.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

}