package sh.plugins.intent_contact

import android.app.Activity
import android.content.Intent
import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry

/** IntentContactPlugin */
class IntentContactPlugin: FlutterPlugin, MethodCallHandler, ActivityAware, PluginRegistry.ActivityResultListener {

  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel
  private lateinit var activity: Activity
  private var contactPageHelper: ContactPageHelper? = null
  lateinit var result: Result

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "intent_contact")
    channel.setMethodCallHandler(this)
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    if (call.method == "openContactPage") {
      this.result = result;
      contactPageHelper = ContactPageHelper()
      contactPageHelper?.openContactPage(activity)
    } else {
      result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }

  override fun onAttachedToActivity(binding: ActivityPluginBinding) {
    binding.addActivityResultListener(this)
    this.activity = binding.activity
  }

  override fun onDetachedFromActivityForConfigChanges() {

  }

  override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
    this.activity = binding.activity
  }

  override fun onDetachedFromActivity() {

  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
    contactPageHelper?.setResult(activity, data, result)
    return true
  }
}
