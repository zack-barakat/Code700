package com.android.code700.ui.splash

import android.os.Bundle
import com.android.code700.R
import com.android.code700.ui.base.BaseMvpActivity
import com.android.code700.ui.base.BasePresenter
import com.android.code700.ui.offers.OffersActivity
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class SplashActivity : BaseMvpActivity(), SplashContracts.View {

    @Inject
    lateinit var mPresenter: SplashContracts.Presenter<SplashContracts.View>

    public override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent?.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupLayout()
        mPresenter.onAttachView(this)
    }

    override fun sendExtrasToPresenter(extras: Bundle) {

    }

    protected fun setupLayout() {

    }

    public override fun getPresenter(): BasePresenter<*> {
        return mPresenter
    }

    override fun showMainScreen() {
        startActivity<OffersActivity>()
    }
}
