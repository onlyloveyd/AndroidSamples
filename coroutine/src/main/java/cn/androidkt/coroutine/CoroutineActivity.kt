package cn.androidkt.coroutine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

class CoroutineActivity : AppCompatActivity() {


    val mainScope by lazy {
        MainScope()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)

        mainScope.launch {

        }

        lifecycleScope.launch {

        }

        runBlocking {
            coroutineScope {
                delay(10000)
                print("Scope 1")
            }
            coroutineScope {
                delay(5000)
                print("Scope 2")
            }

            coroutineScope {
                delay(2000)
                print("Scope 3")
            }
        }
    }

    suspend fun doWork() = coroutineScope {

    }
}
