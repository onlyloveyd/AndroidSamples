package tech.kicky.libpag

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.libpag.PAGView

class MainActivity : AppCompatActivity() {
//    private val binding by viewBinding<ActivityMainBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val pag = PAGView(this).apply {
            path = "assets://fans.pag"
        }
        setContentView(pag)
        pag.play()
    }
}