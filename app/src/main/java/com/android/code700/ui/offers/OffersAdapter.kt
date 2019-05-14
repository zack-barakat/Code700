package com.android.code700.ui.offers

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


class OffersAdapter(val onOfferClick: (position: Int) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mOffers = arrayListOf<Offer>()

    private val TYPE_LOADING = 1
    private val TYPE_OFFER = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_LOADING) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_footer, parent, false)
            LoadingViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_offer, parent, false)
            return OfferHolder(view)
        }
    }

    override fun getItemCount() = mOffers.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_OFFER) {
            (holder as OfferHolder).bind(mOffers[position])
        } else {
            (holder as LoadingViewHolder).bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (mOffers[position].type == "LOADING") {
            TYPE_LOADING
        } else {
            TYPE_OFFER
        }
    }

    fun addItems(offers: ArrayList<Offer>) {
        val positionStart = offers.size + 1
        this.mOffers.addAll(offers)

        notifyItemRangeInserted(positionStart, offers.size)
    }

    fun addLoadingItem(offer: Offer) {
        mOffers.add(offer)
        notifyItemInserted(mOffers.size - 1)
    }

    fun removeLoadingItem() {
        mOffers.removeAt(mOffers.size - 1)
        notifyItemRemoved(mOffers.size - 1)
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

    private inner class MyDiffCallback(
        private val newOffers: ArrayList<Offer>,
        private val oldOffers: ArrayList<Offer>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldOffers.size
        }

        override fun getNewListSize(): Int {
            return newOffers.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldOffers[oldItemPosition].id == newOffers[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldOffers[oldItemPosition].id == newOffers[newItemPosition].id
        }

        @Nullable
        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
            return super.getChangePayload(oldItemPosition, newItemPosition)
        }
    }
}