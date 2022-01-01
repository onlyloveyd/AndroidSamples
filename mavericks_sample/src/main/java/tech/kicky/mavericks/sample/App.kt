package tech.kicky.mavericks.sample

import android.app.Application
import android.os.StrictMode
import com.airbnb.mvrx.Mavericks
import com.airbnb.mvrx.MavericksViewModelConfigFactory
import com.airbnb.mvrx.asContextElement
import kotlin.coroutines.EmptyCoroutineContext

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val policy = StrictMode.ThreadPolicy.Builder()
            .detectNetwork()
            .penaltyDialog()
            .build()

        Mavericks.initialize(
            this, viewModelConfigFactory = MavericksViewModelConfigFactory(
                context = this,
                storeContextOverride = if (BuildConfig.DEBUG) policy.asContextElement() else EmptyCoroutineContext
            )
        )
    }
}