package live.streamstyle

import android.os.Bundle
import com.banuba.sdk.manager.BanubaSdkManager
import com.facebook.react.ReactActivity
import com.facebook.react.ReactActivityDelegate
import com.facebook.react.ReactRootView
import com.swmansion.gesturehandler.react.RNGestureHandlerEnabledRootView
import expo.modules.splashscreen.SplashScreenImageResizeMode
import expo.modules.splashscreen.singletons.SplashScreen.show

// import com.reactlibrary.RNVoxeetFirebasePackage;
class MainActivity : ReactActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BanubaSdkManager.initialize(
                this,
                "<add_token_here>"
        )
        // FirebaseApp.initializeApp();
        // SplashScreen.show(...) has to be called after super.onCreate(...)
        // Below line is handled by '@expo/configure-splash-screen' command and it's discouraged to modify it manually
        show(this, SplashScreenImageResizeMode.CONTAIN, ReactRootView::class.java, false)
    }

    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    override fun getMainComponentName(): String? {
        return "main"
    }

    override fun createReactActivityDelegate(): ReactActivityDelegate {
        return object : ReactActivityDelegate(this, mainComponentName) {
            override fun createRootView(): ReactRootView {
                return RNGestureHandlerEnabledRootView(this@MainActivity)
            }
        }
    }
}