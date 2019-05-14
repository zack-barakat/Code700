package com.android.code700.ui.searchoffers

import android.support.annotation.Nullable
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.code700.R
import com.android.code700.data.model.Offer
import com.android.code700.extensions.loadImage
import kotlinx.android.synthetic.main.item_offer.view.*


class OffersSearchAdapter(val onOfferClick: (position: Int) -> Unit) :
    RecyclerView.Adapter<OffersSearchAdapter.OfferHolder>() {

    private var mOffers = arrayListOf<Offer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffersSearchAdapter.OfferHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_offer, parent, false)
        return OfferHolder(view)
    }

    override fun getItemCount() = mOffers.size

    override fun onBindViewHolder(holder: OffersSearchAdapter.OfferHolder, position: Int) {
        holder.bind(mOffers[position])
    }

    fun refreshOffers(offers: ArrayList<Offer>) {
        mOffers = offers
        notifyDataSetChanged()
    }

    inner class OfferHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(offer: Offer) {
            with(itemView) {
                tvOfferTitle.text = offer.title
                tvBusinessName.text = offer.businessName
                ivOfferImage.loadImage(offer.images)
                setOnClickListener { onOfferClick.invoke(adapterPosition) }
            }
        }
    }

    inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {}
    }
}