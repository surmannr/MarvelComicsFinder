package hu.bme.aut.android.marvelcomicsfinder.utils

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

class FirebaseExtensions {
    companion object {
        fun logAnalytics(eventName: String, context: Context, bundleProperties: Map<String, String>) {
            val bundle = Bundle()
            for (bun in bundleProperties) {
                bundle.putString(bun.key, bun.value)
            }
            Firebase.analytics
                .logEvent(eventName, bundle)
        }
        const val ADD_FAVOURITE = "add_favourite"
        const val REMOVE_FAVOURITE = "remove_favourite"
    }
}