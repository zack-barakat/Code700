package com.android.code700.ui.offerdetail

import com.android.code700.data.IDataManager
import com.android.code700.data.model.OfferDetail
import com.android.code700.ui.base.BaseMvpPresenter
import com.android.code700.ui.base.ErrorView
import javax.inject.Inject

class OfferDetailPresenter @Inject
constructor(dataManager: IDataManager) : BaseMvpPresenter<OfferDetailContracts.View>(dataManager),
    OfferDetailContracts.Presenter<OfferDetailContracts.View> {

    private var mOfferId = 0
    private var mOfferDetail: OfferDetail? = null

    override fun onAttachView(view: OfferDetailContracts.View) {
        super.onAttachView(view)
        val disposable = mOffersRepository.getOffer(mOfferId)
            .doOnSubscribe { view.showProgress() }
            .doOnNext { view.hideProgress() }
            .subscribe({
                mOfferDetail = it.offer
                processOfferDetailResponse(it.offer)
            }, {
                view.hideProgress()
                handleApiError(it, ErrorView.ERROR_DIALOG)
            })
        addToSubscription(disposable)
    }

    override fun initPresenter(offerId: Int) {
        mOfferId = offerId
    }

    private fun processOfferDetailResponse(offerDetail: OfferDetail) {
        with(offerDetail) {
            view.showOfferDetails(this)
            val showAddress = address?.isNotEmpty() ?: false
            view.showAddress(showAddress, address ?: "")
        }
    }

    override fun onAddressClick() {
        mOfferDetail?.let { offerDetail ->
            if (offerDetail.latitude != null && offerDetail.longitude != null) {
                view.openAddressLocation(offerDetail.latitude, offerDetail.longitude)
            }
        }
    }
}
