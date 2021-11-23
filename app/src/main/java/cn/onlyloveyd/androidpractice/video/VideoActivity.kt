package cn.onlyloveyd.androidpractice.video

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import cn.onlyloveyd.androidpractice.databinding.ActivityVideoBinding
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.utils.OrientationUtils


class VideoActivity : AppCompatActivity() {
    var orientationUtils: OrientationUtils? = null
    private val mBinding: ActivityVideoBinding by lazy {
        ActivityVideoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        init()
    }

    private fun init() {
        val source1 =
            "http://172.16.105.164:8003/rtp/52010200441310000001_52010200441310000001.live.mp4"
        mBinding.videoPlayer.setUp(source1, true, "")
        mBinding.videoPlayer.titleTextView.visibility = View.GONE
        mBinding.videoPlayer.startPlayLogic()
    }

    override fun onPause() {
        super.onPause()
        mBinding.videoPlayer.onVideoPause()
    }

    override fun onResume() {
        super.onResume()
        mBinding.videoPlayer.onVideoResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoManager.releaseAllVideos()
        if (orientationUtils != null) orientationUtils?.releaseListener()
    }

    override fun onBackPressed() {
        //先返回正常状态
        if (orientationUtils?.screenType === ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            mBinding.videoPlayer.fullscreenButton.performClick()
            return
        }
        //释放所有
        mBinding.videoPlayer.setVideoAllCallBack(null)
        super.onBackPressed()
    }

}

