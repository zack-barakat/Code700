package com.android.code700.ui.searchoffers

import com.android.code700.data.model.Offer
import com.android.code700.ui.base.BasePresenter
import com.android.code700.ui.base.BaseView

interface OffersSearchContracts {

    interface View : BaseView {
        fun showOffersSearchResult(offers: ArrayList<Offer>)

        fun showEmptyOffersResults()

        fun openOfferDetail(offerId: Int)
    }

    interface Presenter<V : View> : BasePresenter<V> {

        fun onQueryChange(query: String)

        fun onOfferClick(position: Int)
    }
}
