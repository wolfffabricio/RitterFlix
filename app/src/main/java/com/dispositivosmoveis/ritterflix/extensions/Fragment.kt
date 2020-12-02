package com.dispositivosmoveis.ritterflix.extensions

import android.content.Intent
import android.net.Uri
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

fun Fragment.shareContentWithImage(uriToImage: Uri, text: String) {
    val shareIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_STREAM, uriToImage)
        putExtra(Intent.EXTRA_TEXT, text)
        type = "image/jpeg"
        type = "text/plain"
    }
    startActivity(Intent.createChooser(shareIntent, text))
}