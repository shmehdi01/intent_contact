package sh.plugins.intent_contact

import android.app.Activity
import android.content.Intent
import android.provider.ContactsContract
import android.util.Log
import androidx.loader.content.CursorLoader
import io.flutter.plugin.common.MethodChannel
import org.json.JSONObject

class ContactPageHelper {


    fun openContactPage(activity: Activity) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.setDataAndType(ContactsContract.Contacts.CONTENT_URI, ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE)
        activity.startActivityForResult(intent, 1)
    }

    fun setResult(activity: Activity, data: Intent?, result: MethodChannel.Result) {
        data?.let {
            val contactData = it.data
            val loader = CursorLoader(activity, contactData!!, null, null, null, null)


            val cursor = loader.loadInBackground()!!

            if (cursor.moveToFirst()) {
                val name =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val hasPhone =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))
                val number =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NORMALIZED_NUMBER))


                val jsonObj = JSONObject().apply {
                    put("name", name)
                    put("number", number)
                    put("hasNumber", hasPhone == "1")
                }

                result.success(jsonObj.toString())
            } else {
                result.success("{}")
                Log.i("DATA NOT FOUND", "No Data cursor")
            }
        } ?: kotlin.run {
            result.success(JSONObject().toString())
            Log.i("DATA NOT FOUND", "{}")
        }

    }


}