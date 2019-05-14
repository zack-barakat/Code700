package com.android.code700.ui.searchoffers

import com.android.code700.data.IDataManager
import com.android.code700.data.model.Offer
import com.android.code700.ui.base.BaseMvpPresenter
import com.android.code700.ui.base.ErrorView
import com.android.code700.ui.searchoffers.OffersSearchContracts
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class OffersSearchPresenter @Inject
constructor(dataManager: IDataManager) : BaseMvpPresenter<OffersSearchContracts.View>(dataManager),
    OffersSearchContracts.Presenter<OffersSearchContracts.View> {

    private var mOffers = arrayListOf<Offer>()
    private val searchQuerySubject = PublishSubject.create<String>()

    override fun onAttachView(view: OffersSearchContracts.View) {
        super.onAttachView(view)

        configureRxSearch()
    }

    override fun onQueryChange(query: String) {
        searchQuerySubject.onNext(query)
    }

    private fun configureRxSearch() {
        val disposable = searchQuerySubject
            .debounce(300, TimeUnit.MILLISECONDS, Schedulers.io())
            .filter { it.isNotEmpty() }
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                searchAndDisplayOffers(it)
            }, {
                it.printStackTrace()
            })
        addToSubscription(disposable)
    }

    override fun onOfferClick(position: Int) {
        if (mOffers.size >= position) {
            val offer = mOffers[position]
            view.openOfferDetail(offer.id)
        }
    }

    private fun searchAndDisplayOffers(query: String) {
        view.showProgress()
        val disposable = mOffersRepository.getOffers(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mOffers = it.offers
                view.hideProgress()
                if (it.offers.isEmpty()) {
                    view.showEmptyOffersResults()
                } else {
                    view.showOffersSearchResult(it.offers)
                }
            }, {
                view.hideProgress()
                handleApiError(it, ErrorView.ERROR_DIALOG)
            })
        addToSubscription(disposable)

    }
}
