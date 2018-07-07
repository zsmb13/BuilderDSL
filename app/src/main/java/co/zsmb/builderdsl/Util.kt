package co.zsmb.builderdsl

import android.content.Context
import android.widget.Toast

@Suppress("NOTHING_TO_INLINE")
inline fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
}
