package com.android.code700.di.component;

import com.android.code700.di.module.FragmentModule;
import com.android.code700.di.scopes.FragmentScope;
import com.android.code700.ui.base.BaseMvpFragment;
import dagger.Component;


@FragmentScope
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(BaseMvpFragment baseMvpFragment);

}