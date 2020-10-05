package com.dispositivosmoveis.ritterflix.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.dispositivosmoveis.ritterflix.repository.http.BASE_POST_URL
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(imageUrl: String?) {
    imageUrl.let {
        Picasso.get()
            .load(BASE_POST_URL + it)
            .into(this)
    }
}