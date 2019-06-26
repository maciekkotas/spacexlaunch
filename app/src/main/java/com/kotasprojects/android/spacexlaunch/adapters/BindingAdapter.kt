package com.kotasprojects.android.spacexlaunch.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kotasprojects.android.spacexlaunch.R
import com.kotasprojects.android.spacexlaunch.SpaceXApiStatus
import com.kotasprojects.android.spacexlaunch.network.SpaceXLunchProperty
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<SpaceXLunchProperty>?) {
    val adapter = recyclerView.adapter as RecyclerViewAdapter
    adapter.submitList(data)

}

@BindingAdapter("imgUrl")
fun bindImage(imageView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.broken_image)
                    .override(500, 300)
            )
            .into(imageView)
    } ?: run {
        imageView.setImageResource(R.drawable.space_x_no_photo)
    }
}

@BindingAdapter("spaceXApiStatus")
fun bindStatus(statusImageView: ImageView, status: SpaceXApiStatus?) {
    when (status) {
        SpaceXApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        SpaceXApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.connection_error)
        }
        SpaceXApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("launchStatus")
fun bindLaunchStatus(statusTextView: TextView, launchStatus: Boolean?) {
    when (launchStatus) {
        true -> statusTextView.setText(R.string.success)
        false -> statusTextView.setText(R.string.fail)
        else -> statusTextView.setText(R.string.not_yet_launched)
    }

}

@BindingAdapter("date")
fun bindDate(dateTextView: TextView, date: Long) {
    val sdf = SimpleDateFormat("dd:MM:yyyy")
    val dateS = Date(date * 1000)
    dateTextView.setText(sdf.format(dateS))
}

