package cn.onlyloveyd.androidpractice.motion

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import cn.onlyloveyd.androidpractice.R
import cn.onlyloveyd.androidpractice.databinding.ActivityMotionLayoutBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.InetAddress
import java.net.URL


/**
 * Motion Layout
 *
 * @author yidong
 * @date 2020/7/24
 */
class MotionActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMotionLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_motion_layout
        )
        GlobalScope.launch(Dispatchers.IO) {
            println("yidong = ${getNetworkTime()}")
        }
    }

    /**
     * 获取网络时间，如果获取失败返回系统当前时间
     */
    fun getNetworkTime(): Long {
        try {
            val url = URL("http://8.8.8.8")
            val conn = url.openConnection() as HttpURLConnection
            conn.connectTimeout = 2000
            conn.readTimeout = 5000
            conn.connect()
            val address: InetAddress = InetAddress.getByName(url.host)
            val ip: String = address.hostAddress
            val timestamp = conn.date
            conn.disconnect()
            return timestamp
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return System.currentTimeMillis()
    }
}