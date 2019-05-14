package com.android.code700.di.module;

import android.app.Activity;
import android.content.Context;
import com.android.code700.di.qualifiers.ActivityContext;
import com.android.code700.ui.offers.OffersContracts;
import com.android.code700.ui.offers.OffersPresenter;
import com.android.code700.ui.searchoffers.OffersSearchContracts;
import com.android.code700.ui.searchoffers.OffersSearchPresenter;
import com.android.code700.ui.splash.SplashContracts;
import com.android.code700.ui.splash.SplashPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return activity;
    }

    @Provides
    Activity provideActivity() {
        return activity;
    }

    @Provides
    SplashContracts.Presenter<SplashContracts.View> provideSplashPresenter(SplashPresenter splashPresenter) {
        return splashPresenter;
    }

    @Provides
    OffersContracts.Presenter<OffersContracts.View> provideOffersPresenter(OffersPresenter offersPresenter) {
        return offersPresenter;
    }

    @Provides
    OffersSearchContracts.Presenter<OffersSearchContracts.View> provideOffersSearchPresenter(OffersSearchPresenter offersSearchPresenter) {
        return offersSearchPresenter;
    }
}
