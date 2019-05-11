package com.android.code700.di.module;


import com.android.code700.data.AppErrorHelper;
import com.android.code700.data.DataManager;
import com.android.code700.data.IAppErrorHelper;
import com.android.code700.data.IDataManager;
import com.android.code700.di.scopes.ApplicationScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by zack_barakat
 */

@Module(includes = ApiModule.class)
public class DataManagerModule {

    @Provides
    @ApplicationScope
    public IDataManager provideDataManager(DataManager manager) {
        return manager;
    }

    @Provides
    @ApplicationScope
    public IAppErrorHelper provideErrorHelper(AppErrorHelper errorHelper) {
        return errorHelper;
    }
}
