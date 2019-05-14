package com.android.code700.ui.offers

import com.android.code700.data.IDataManager
import com.android.code700.data.model.Offer
import com.android.code700.ui.base.BaseMvpPresenter
import com.android.code700.ui.base.ErrorView
import javax.inject.Inject

class OffersPresenter @Inject
constructor(dataManager: IDataManager) : BaseMvpPresenter<OffersContracts.View>(dataManager),
    OffersContracts.Presenter<OffersContracts.View> {

    private var mOffers = arrayListOf<Offer>()
    private var mPage = 0
    private var mTotalPages = 0

    override fun onAttachView(view: OffersContracts.View) {
        super.onAttachView(view)
        fetchAndShowOffers()
    }

    private fun fetchAndShowOffers() {
        if (mPage > mTotalPages) {
            view.hideLoadingMore()
            return
        }
        if (mOffers.isEmpty()) {
            view.showProgress()
        } else {
            view.showLoadingMore()
        }
        val disposable = mOffersRepository.getOffers(mPage)
            .subscribe({ offersResponse ->
                mTotalPages = offersResponse.totalPages
                view.hideProgress()
                if (offersResponse.offers.isNotEmpty()) {
                    if (mOffers.size > 0) {
                        view.hideLoadingMore()
                    }
                    mOffers.addAll(offersResponse.offers)
                    view.showOffers(mOffers)
                    mPage++
                }
            }, {
                view.hideProgress()
                handleApiError(it, ErrorView.ERROR_TOAST)
            })
        addToSubscription(disposable)
    }

    override fun loadMoreOffers() {
        fetchAndShowOffers()
    }

    override fun onSearchClick() {
        view.openSearchScreen()
    }

    override fun onOfferClick(position: Int) {
        if (mOffers.size >= position) {
            val offer = mOffers[position]
            view.openOfferScreen(offer.id)
        }
    }
}
