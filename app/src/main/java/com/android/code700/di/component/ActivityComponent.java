package com.android.code700.di.component;


import com.android.code700.di.module.ActivityModule;
import com.android.code700.di.scopes.ActivityScope;
import com.android.code700.ui.base.BaseMvpActivity;
import com.android.code700.ui.offers.OffersActivity;
import com.android.code700.ui.splash.SplashActivity;
import dagger.Component;


@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(BaseMvpActivity activity);

    void inject(SplashActivity activity);

    void inject(OffersActivity activity);
}