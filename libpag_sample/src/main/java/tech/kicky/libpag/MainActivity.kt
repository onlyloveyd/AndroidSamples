package tech.kicky.libpag

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.libpag.PAGView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pagView = PAGView(this)
        pagView.path = "assets://like.pag"
        setContentView(pagView)

        pagView.play()

    }
}