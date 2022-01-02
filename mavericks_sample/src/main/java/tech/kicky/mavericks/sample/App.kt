package tech.kicky.mavericks.sample

import android.app.Application
import com.airbnb.mvrx.Mavericks
import com.airbnb.mvrx.navigation.DefaultNavigationViewModelDelegateFactory

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Mavericks.initialize(
            this,
            viewModelDelegateFactory = DefaultNavigationViewModelDelegateFactory()
        )
    }
}