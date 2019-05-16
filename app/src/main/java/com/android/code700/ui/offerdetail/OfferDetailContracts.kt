package com.android.code700.ui.offerdetail

import com.android.code700.data.model.OfferDetail
import com.android.code700.ui.base.BasePresenter
import com.android.code700.ui.base.BaseView

interface OfferDetailContracts {

    interface View : BaseView {
        fun showOfferDetails(offerDetail: OfferDetail)

        fun showAddress(show: Boolean, address: String)

        fun openAddressLocation(lat: Float, lng: Float)
    }

    interface Presenter<V : View> : BasePresenter<V> {

        fun initPresenter(offerId: Int)

        fun onAddressClick()
    }
}
