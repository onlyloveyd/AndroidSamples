package tech.kicky.visibility

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btGet = findViewById<TextView>(R.id.tv_get)
        val etPackage = findViewById<TextView>(R.id.et_package)
        btGet.setOnClickListener {
            getPackageInfo(etPackage.text.toString())
        }
    }

    fun getPackageInfo(pkgName: String) {
        val packageManager: PackageManager = this.packageManager
        try {
            packageManager.getApplicationInfo(pkgName, 0)
            Toast.makeText(this, "Got You", Toast.LENGTH_SHORT).show()
        } catch (e: PackageManager.NameNotFoundException) {
            Toast.makeText(this, "NameNotFoundException", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }

}