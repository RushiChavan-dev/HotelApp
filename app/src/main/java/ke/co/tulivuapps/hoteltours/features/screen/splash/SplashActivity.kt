package ke.co.tulivuapps.hoteltours.features.screen.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import ke.co.tulivuapps.hoteltours.features.screen.main.MainActivity
import ke.co.tulivuapps.hoteltours.utils.Utility.launchActivity
import kotlinx.coroutines.launch

/**
 * Created by Rushi on 3.05.2023
 */

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { true }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collect {
                    when (it) {
                        is SplashViewEvent.DirectToDashBoard -> {
                            startMainActivity()
                            finish()
                        }
                    }
                }
            }
        }
    }


    private fun startMainActivity() {
        launchActivity(
            packageName = packageName,
            className = MainActivity::class.java.name
        )
    }
}