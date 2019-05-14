package com.android.code700.data.repositories

import com.android.code700.data.model.Offer
import com.android.code700.data.model.OfferResponse
import com.android.code700.data.network.IApiHelper
import com.android.code700.di.scopes.ApplicationScope
import io.reactivex.Observable
import javax.inject.Inject


interface IOffersRepository {
    fun getOffers(page: Int): Observable<ArrayList<Offer>>

    fun getOffers(query: String): Observable<ArrayList<Offer>>

    fun getOffer(offerId: String): Observable<OfferResponse>
}

@ApplicationScope
open class OffersRepository @Inject constructor(private val apiHelper: IApiHelper) : IOffersRepository {

    private var mOffers = arrayListOf<Offer>()

    /**
     * get the list of offers and persist them in memory
     * @return list of offers
     */
    override fun getOffers(page: Int): Observable<ArrayList<Offer>> {
        return apiHelper.getOffers(page)
            .map {
                it.offers
            }
            .doOnNext {
                mOffers.addAll(it)
            }
    }

    /**
     * search for list of offers that contains the query
     * @return list of offers
     */
    override fun getOffers(query: String): Observable<ArrayList<Offer>> {
        return Observable.just(mOffers)
            .map { merchants -> merchants.filter { it.title.contains(query, true) } }
            .map { ArrayList(it) }
    }

    override fun getOffer(offerId: String): Observable<OfferResponse> {
        return apiHelper.getOffer(offerId)
    }
}