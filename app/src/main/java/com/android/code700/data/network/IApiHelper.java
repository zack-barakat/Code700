package com.android.code700.data.network;

import com.android.code700.data.model.OfferResponse;
import com.android.code700.data.model.OffersResponse;
import io.reactivex.Observable;
import org.jetbrains.annotations.NotNull;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zack_barakat
 */

public interface IApiHelper {

    @GET("getoffers.php?action=alloffers")
    Observable<OffersResponse> getOffers(@Query("page") int page);

    @GET("getoffers.php?action=selectOffer")
    Observable<OfferResponse> getOffer(@Query("offerid") String offerId);

    class Factory {
        public static final int NETWORK_CALL_TIMEOUT = 30;

        public static IApiHelper create(Retrofit retrofit) {

            return retrofit.create(IApiHelper.class);
        }
    }
}
