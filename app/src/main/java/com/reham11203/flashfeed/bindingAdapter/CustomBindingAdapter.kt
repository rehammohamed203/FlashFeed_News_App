package com.reham11203.flashfeed.bindingAdapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.github.marlonlom.utilities.timeago.TimeAgo

@BindingAdapter("app:imageUrl")
fun bindImage(imageView: ImageView, imageUrl: String?) {
    Glide.with(imageView)
        .load(imageUrl)
        .into(imageView)
}

@BindingAdapter("app:timeAgo")
fun extractTimeAgoText(textView: TextView, timeInMillis: Long) {
    textView.text = TimeAgo.using(timeInMillis ?: 0)
}