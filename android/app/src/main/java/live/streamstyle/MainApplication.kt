package live.streamstyle

import android.app.Application
import android.content.Context
import com.facebook.react.*
import com.facebook.soloader.SoLoader
import expo.modules.updates.UpdatesController
import live.streamstyle.generated.BasePackageList
import org.unimodules.adapters.react.ModuleRegistryAdapter
import org.unimodules.adapters.react.ReactModuleRegistryProvider
import java.lang.reflect.InvocationTargetException

// import com.voxeet.rn.firebase.RNVoxeetFirebasePackage;
//import com.voxeet.RNVoxeetConferencekitPackage;
class MainApplication : Application(), ReactApplication {
    private val mModuleRegistryProvider = ReactModuleRegistryProvider(
            BasePackageList().packageList
    )
    private val mReactNativeHost: ReactNativeHost = object : ReactNativeHost(this) {
        override fun getUseDeveloperSupport(): Boolean {
            return BuildConfig.DEBUG
        }

        override fun getPackages(): List<ReactPackage> {
            val packages: MutableList<ReactPackage> = PackageList(this).packages
            packages.add(ModuleRegistryAdapter(mModuleRegistryProvider))
            packages.add(VideoViewPackage())
            // packages.add(new RNVoxeetFirebasePackage());
            return packages
        }

        override fun getJSMainModuleName(): String {
            return "index"
        }

        override fun getJSBundleFile(): String? {
            return if (BuildConfig.DEBUG) {
                super.getJSBundleFile()
            } else {
                UpdatesController.getInstance().launchAssetFile
            }
        }

        override fun getBundleAssetName(): String? {
            return if (BuildConfig.DEBUG) {
                super.getBundleAssetName()
            } else {
                UpdatesController.getInstance().bundleAssetName
            }
        }
    }

    override fun getReactNativeHost(): ReactNativeHost {
        return mReactNativeHost
    }

    override fun onCreate() {
        super.onCreate()
        SoLoader.init(this,  /* native exopackage */false)
        if (!BuildConfig.DEBUG) {
            UpdatesController.initialize(this)
        }
        initializeFlipper(this, reactNativeHost.reactInstanceManager)
    }

    companion object {
        /**
         * Loads Flipper in React Native templates. Call this in the onCreate method with something like
         * initializeFlipper(this, getReactNativeHost().getReactInstanceManager());
         *
         * @param context
         * @param reactInstanceManager
         */
        private fun initializeFlipper(
                context: Context, reactInstanceManager: ReactInstanceManager) {
            if (BuildConfig.DEBUG) {
                try {
                    /*
         We use reflection here to pick up the class that initializes Flipper,
        since Flipper library is not available in release mode
        */
                    val aClass = Class.forName("live.streamstyle.ReactNativeFlipper")
                    aClass
                            .getMethod("initializeFlipper", Context::class.java, ReactInstanceManager::class.java)
                            .invoke(null, context, reactInstanceManager)
                } catch (e: ClassNotFoundException) {
                    e.printStackTrace()
                } catch (e: NoSuchMethodException) {
                    e.printStackTrace()
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                } catch (e: InvocationTargetException) {
                    e.printStackTrace()
                }
            }
        }
    }
}