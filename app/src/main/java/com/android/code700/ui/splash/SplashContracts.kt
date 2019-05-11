package com.android.code700.ui.splash

import com.android.code700.ui.base.BasePresenter
import com.android.code700.ui.base.BaseView

interface SplashContracts {

    interface View : BaseView {
        fun showMainScreen()
    }

    interface Presenter<V : View> : BasePresenter<V>
}
