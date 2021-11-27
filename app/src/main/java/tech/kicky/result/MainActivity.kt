package tech.kicky.result

import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.DividerItemDecoration
import tech.kicky.result.databinding.ActivityMainBinding
import java.io.File

/**
 * 主界面
 * author: yidong
 * 2021-11-26
 */
class MainActivity : AppCompatActivity() {

    private val mBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mAdapter by lazy {
        TitleAdapter(this)
    }

    private val mStartActivityForResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            it?.let {
                val message = it.data?.getStringExtra("extra_out")
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }

    private val mStartIntentSenderForResult =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) {
            it?.let {
                val message = it.data?.getStringExtra("extra_out")
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }

    private val mRequestMultiplePermissionsLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            it?.let {
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    private val mRequestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            it?.let {
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    private val mTakePicturePreviewLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            it?.let {
                Toast.makeText(this, it.byteCount, Toast.LENGTH_SHORT).show()
            }
        }

    private val mTakePictureLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicture()) {
            it?.let {
                Toast.makeText(this, if (it) "拍照成功" else "拍照失败", Toast.LENGTH_SHORT).show()
            }
        }

    // 返回一个缩略图
    private val mTakeVideoLauncher =
        registerForActivityResult(ActivityResultContracts.TakeVideo()) {
            it?.let {
                Toast.makeText(this, it.byteCount, Toast.LENGTH_SHORT).show()
            }
        }

    private val mPickContactLauncher =
        registerForActivityResult(ActivityResultContracts.PickContact()) {
            it?.let {
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }
        }

    private val mGetContentLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) {
            it?.let {
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }
        }

    private val mGetMultipleContentLauncher =
        registerForActivityResult(ActivityResultContracts.GetMultipleContents()) {
            it?.let {
                Toast.makeText(this, it.joinToString(), Toast.LENGTH_SHORT).show()
            }
        }

    private val mCreateDocumentLauncher =
        registerForActivityResult(ActivityResultContracts.CreateDocument()) {
            it?.let {
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }
        }


    private val mOpenMultipleDocumentsLauncher =
        registerForActivityResult(ActivityResultContracts.OpenMultipleDocuments()) {
            it?.let {
                Toast.makeText(this, it.joinToString(), Toast.LENGTH_SHORT).show()
            }
        }

    private val mOpenDocumentLauncher =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) {
            it?.let {
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }
        }

    private val mOpenDocumentTreeLauncher =
        registerForActivityResult(ActivityResultContracts.OpenDocumentTree()) {
            it?.let {
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }
        }

    private val menus = mutableListOf<MenuOpt>().apply {
        add(MenuOpt("简单使用") {
            startActivity(Intent(this@MainActivity, ResultActivity::class.java))
        })
        add(MenuOpt("StartActivityForResult") {
            mStartActivityForResultLauncher.launch(
                Intent(
                    this@MainActivity,
                    TargetActivity::class.java
                ).apply {
                    putExtra("extra_in", "StartActivityForResult Message")
                })
        })
        add(MenuOpt("StartIntentSenderForResult") {
            val flag = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                PendingIntent.FLAG_UPDATE_CURRENT
            } else {
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            }
            val pendingIntent = PendingIntent.getActivity(
                this@MainActivity, 100, Intent(
                    this@MainActivity,
                    TargetActivity::class.java
                ).apply {
                    putExtra("extra_in", "StartActivityForResult Message")
                }, flag
            )
            mStartIntentSenderForResult.launch(
                IntentSenderRequest.Builder(pendingIntent.intentSender).build()
            )
        })
        add(MenuOpt("RequestMultiplePermissions") {
            mRequestMultiplePermissionsLauncher.launch(
                arrayOf(
                    android.Manifest.permission.CAMERA,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            )
        })
        add(MenuOpt("RequestPermission") {
            mRequestPermissionLauncher.launch(
                android.Manifest.permission.CAMERA
            )
        })
        add(MenuOpt("TakePicturePreview") {
            mTakePicturePreviewLauncher.launch(null)
        })
        add(MenuOpt("TakePicture") {
            val tmp = File(externalCacheDir, "${System.currentTimeMillis()}.png")
            val uri = FileProvider.getUriForFile(
                this@MainActivity,
                BuildConfig.APPLICATION_ID + ".fileProvider",
                tmp
            )
            mTakePictureLauncher.launch(uri)
        })
        add(MenuOpt("TakeVideo") {
            val tmp = File(externalCacheDir, "${System.currentTimeMillis()}.png")
            val uri = FileProvider.getUriForFile(
                this@MainActivity,
                BuildConfig.APPLICATION_ID + ".fileProvider",
                tmp
            )
            mTakeVideoLauncher.launch(uri)
        })
        add(MenuOpt("PickContact") {
            mPickContactLauncher.launch(null)
        })
        add(MenuOpt("GetContent") {
            mGetContentLauncher.launch("image/*")
        })
        add(MenuOpt("GetMultipleContents") {
            mGetMultipleContentLauncher.launch("image/*")
        })
        add(MenuOpt("CreateDocument") {
            mCreateDocumentLauncher.launch("请选择文件")
        })
        add(MenuOpt("OpenDocument") {
            mOpenMultipleDocumentsLauncher.launch(arrayOf("image/*", "video/*"))
        })
        add(MenuOpt("OpenMultipleDocuments") {
            mOpenMultipleDocumentsLauncher.launch(arrayOf("image/*", "video/*"))
        })
        add(MenuOpt("OpenDocumentTree") {
            mOpenDocumentTreeLauncher.launch(null)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        mAdapter.setData(menus)
        mBinding.rvMenu.adapter = mAdapter
        mBinding.rvMenu.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
    }
}