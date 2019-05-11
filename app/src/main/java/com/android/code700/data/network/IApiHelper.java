package com.android.code700.data.network;

import retrofit2.Retrofit;

/**
 * Created by zack_barakat
 */

public interface IApiHelper {


    class Factory {
        public static final int NETWORK_CALL_TIMEOUT = 30;

        public static IApiHelper create(Retrofit retrofit) {

            return retrofit.create(IApiHelper.class);
        }
    }
}
