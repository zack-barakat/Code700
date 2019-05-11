package com.android.code700.di.component;

import android.app.Application;
import android.content.Context;
import com.android.code700.App;
import com.android.code700.data.IAppErrorHelper;
import com.android.code700.data.IDataManager;
import com.android.code700.di.module.ApiModule;
import com.android.code700.di.module.ApplicationModule;
import com.android.code700.di.module.DataManagerModule;
import com.android.code700.di.qualifiers.ApplicationContext;
import com.android.code700.di.scopes.ApplicationScope;
import dagger.BindsInstance;
import dagger.Component;

@ApplicationScope
@Component(modules = {
        DataManagerModule.class,
        ApiModule.class,
        ApplicationModule.class})
public interface ApplicationComponent {

    void inject(App app);

    @ApplicationContext
    Context context();

    Application application();

    IDataManager getDataManager();

    IAppErrorHelper getErrorHelper();

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }
}