package cn.androidkt.coroutine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class CoroutineActivity : AppCompatActivity() {

    val seq = sequence {
        yield(1)
        yield(2)
        yield(3)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)
    }
}