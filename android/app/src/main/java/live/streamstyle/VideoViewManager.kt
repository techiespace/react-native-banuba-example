import android.Manifest
import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.view.SurfaceView
import com.banuba.sdk.effect_player.Effect
import com.facebook.react.uimanager.SimpleViewManager
import com.banuba.sdk.manager.BanubaSdkManager
import com.banuba.sdk.manager.BanubaSdkTouchListener
import com.facebook.react.uimanager.ThemedReactContext // package  live.streamstyle;
import expo.modules.av.video.VideoView
import live.streamstyle.MainActivity

class VideoViewManager : SimpleViewManager<SurfaceView>() {
//    private val banubaSdk: BanubaSdkManager? = null
    private var effect: Effect? = null
    private lateinit var context: Context
    override fun getName(): String {
        return REACT_CLASS
    }
    companion object {
        private const val MASK_NAME = "UnluckyWitch"

        private const val REQUEST_CODE_APPLY_MASK_PERMISSION = 1001
        const val REACT_CLASS = "VideoView"
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }
    private val banubaSdkManager by lazy(LazyThreadSafetyMode.NONE) {
        BanubaSdkManager(context)
    }
    private val maskUri by lazy(LazyThreadSafetyMode.NONE) {
//        println("Shreyas: "+Uri.parse(BanubaSdkManager.getResourcesBase())
//                .buildUpon()
//                .appendPath("effects")
//                .appendPath(MASK_NAME)
//                .build())
//        "file:///home/shreyas/workspace/codecommit/Native/Banuba/live-consumer-mobile/android/app/src/main/assets/bnb-resources/effects/"+MASK_NAME
         Uri.parse(BanubaSdkManager.getResourcesBase())
                 .buildUpon()
                 .appendPath("effects")
                 .appendPath(MASK_NAME)
                 .build()
//        "./bnb-resources/effects/UnluckyWitch"
    }

    override fun createViewInstance(reactContext: ThemedReactContext): SurfaceView {
        context = reactContext
        val videoView = SurfaceView(reactContext)
        videoView.alpha = 0.5f
        videoView.setBackgroundColor(Color.RED)
        videoView.setOnTouchListener(BanubaSdkTouchListener(reactContext, banubaSdkManager.effectPlayer))
        println("Banuba-Debug 1: : "+banubaSdkManager)
        banubaSdkManager.attachSurface(videoView)
        println("Banuba-Debug 2: : "+banubaSdkManager.toString())
        banubaSdkManager.openCamera()
        println("Banuba-Debug 3: : "+banubaSdkManager.toString())
        println("Banuba-Debug uri: : "+maskUri.toString())
        effect = banubaSdkManager.effectManager.loadAsync(maskUri.toString())
        banubaSdkManager.effectPlayer.playbackPlay()
        println("Banuba-Debug 4: : "+banubaSdkManager.toString())
        println("Banuba-Debug 5: : "+effect.toString())
        //    videoView.setOnTouchListener(new BanubaSdkTouchListener(reactContext, banubaSdk.getEffectPlayer()));
        return videoView
    } 
    //   @ReactProp(name="url")

    //   public void setVideoPath(VideoView videoView, String urlPath) {
    //     Uri uri = Uri.parse(urlPath);
    //     videoView.setVideoURI(uri);
    //     videoView.start();
    //   }
}