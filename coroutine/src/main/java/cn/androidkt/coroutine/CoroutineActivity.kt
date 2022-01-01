package cn.androidkt.coroutine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class CoroutineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)

        case1()
//        case2()
    }
}

private fun case1() {
    val scope = MainScope()
    scope.launch(Job()) {
        launch {
            delay(2000L)
            println("CancelJobActivity job1 finished")
            scope.cancel()

        }
        launch {
            delay(3000L)
            println("CancelJobActivity job2 finished")
        }
    }
}

private fun case2() {
    val scope = MainScope()
    scope.launch {
        launch {
            delay(2000L)
            println("CancelJobActivity job1 finished")
            scope.cancel()

        }
        launch {
            delay(3000L)
            println("CancelJobActivity job2 finished")
        }
    }
}