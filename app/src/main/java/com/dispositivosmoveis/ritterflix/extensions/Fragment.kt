package com.dispositivosmoveis.ritterflix.extensions

import android.content.Intent
import androidx.fragment.app.Fragment

fun Fragment.shareContentWithText(text: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(
            Intent.EXTRA_TEXT,
            text
        )
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
}