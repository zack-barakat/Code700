package com.android.code700.data.repositories

import com.android.code700.data.model.Offer
import com.android.code700.data.model.OfferResponse
import com.android.code700.data.model.OffersResponse
import com.android.code700.data.network.IApiHelper
import com.android.code700.di.scopes.ApplicationScope
import io.reactivex.Observable
import javax.inject.Inject


interface IOffersRepository {
    fun getOffers(page: Int): Observable<OffersResponse>

    fun getOffers(query: String): Observable<OffersResponse>

    fun getOffer(offerId: String): Observable<OfferResponse>
}

@ApplicationScope
open class OffersRepository @Inject constructor(private val apiHelper: IApiHelper) : IOffersRepository {

    private var mOffers = arrayListOf<Offer>()

    /**
     * get the list of offers
     * @return list of offers
     */
    override fun getOffers(page: Int): Observable<OffersResponse> {
        return apiHelper.getOffers(page)
            .doOnNext {
                mOffers.addAll(it.offers)
            }
    }

    /**
     * search for list of offers that contains the query
     * @return list of offers
     */
    override fun getOffers(query: String): Observable<OffersResponse> {
        return apiHelper.getOffers(query)
    }

    override fun getOffer(offerId: String): Observable<OfferResponse> {
        return apiHelper.getOffer(offerId)
    }
}