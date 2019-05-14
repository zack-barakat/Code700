package com.android.code700.ui.offers

import com.android.code700.data.model.Offer
import com.android.code700.ui.base.BasePresenter
import com.android.code700.ui.base.BaseView

interface OffersContracts {

    interface View : BaseView {
        fun showOffers(offers: ArrayList<Offer>)

        fun openSearchScreen()

        fun openOfferScreen(offerId: Int)

        fun showLoadingMore()

        fun hideLoadingMore()
    }

    interface Presenter<V : View> : BasePresenter<V> {
        fun onSearchClick()

        fun loadMoreOffers()

        fun onOfferClick(position: Int)
    }
}
