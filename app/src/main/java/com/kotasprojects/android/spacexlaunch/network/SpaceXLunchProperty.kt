package com.kotasprojects.android.spacexlaunch.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SpaceXLunchProperty(
    @Json(name = "mission_name") val name: String,
    @Json(name = "launch_success") val status: Boolean?,
    val links: Links?,
    @Json(name = "launch_site") val launch: LL?,
    @Json(name = "tentative_max_precision") val img: String?,
    @Json(name = "launch_date_unix") val date: Long,
    val details: String?
) : Parcelable

@Parcelize
data class Links(@Json(name = "mission_patch") val imgSrcUrl: String?) : Parcelable

@Parcelize
data class LL(@Json(name = "site_name") val localization: String?) : Parcelable

