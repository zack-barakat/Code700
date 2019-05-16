package com.android.code700.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OffersResponse(
    var error: Boolean,
    var total: Int,
    @SerializedName("totalpages") var totalPages: Int,
    @SerializedName("per_page") var perPage: Int,
    @SerializedName("current_page") var currentPage: Int,
    @SerializedName("last_page") var lastPage: Int,
    var offset: Int,
    @SerializedName("next_page_url") var nextPageUrl: String,
    @SerializedName("prev_page_url") var prevPageUrl: String,
    @SerializedName("data") var offers: ArrayList<Offer>
) : Parcelable

@Parcelize
data class OfferResponse(
    var error: Boolean,
    @SerializedName("data") var offer: OfferDetail
) : Parcelable

@Parcelize
data class Offer(
    val id: Int,
    val title: String,
    @SerializedName("business_name") val businessName: String,
    @SerializedName("company_id") val companyId: Int,
    @SerializedName("images") val images: String
) : Parcelable {
    var type: String = "OFFER"
}

@Parcelize
data class OfferDetail(
    val id: Int,
    @SerializedName("package") val packageId: Int,
    val title: String,
    val url: String?,
    val description: String,
    val name: String,
    val email: String,
    @SerializedName("link_category") val linkCategory: String?,
    val status: Int,
    val date: Long?,
    val featured: Int?,
    val dofollow: Boolean?,
    val ip: String?,
    @SerializedName("user_type") val userType: Int?,
    val location: String?,
    val images: String?,
    @SerializedName("start_date") val startDate: String?,
    val expires: Number?,
    val code: String?,
    val phone: String?,
    val fields: String?,
    val address: String?,
    val longitude: Float?,
    val latitude: Float?,
    @SerializedName("email_verified") val emailVerified: Number?,
    val username: String?,
    @SerializedName("company_id") val companyId: Int?,
    val visits: Int?,
    @SerializedName("offer_type") val offerType: Int?,
    val coupon: String?,
    @SerializedName("business_name") val businessName: String?,
    @SerializedName("offer_link") val offerLink: String?,
    @SerializedName("offer_expires") val offerExpires: String?,
    val rating: Int?,
    val price: Double?,
    @SerializedName("old_price") val oldPrice: Double?,
    @SerializedName("voucher_price") val voucherPrice: Double?,
    @SerializedName("num_uses_per_custm") val numUsesPerCustm: Int?,
    @SerializedName("total_uses") val totalUses: Long?,
    val points: Int?,
    val percentage: Int?,
    @SerializedName("savingamount") val savingAmount: Double?,
    val Commission: Int?,
    @SerializedName("commission_type") val commissionType: Int?,
    @SerializedName("fixed_amount") val fixedAmount: Double?
) : Parcelable
