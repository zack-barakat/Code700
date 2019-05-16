package com.android.code700.ui.offerdetail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.android.code700.R
import com.android.code700.data.model.OfferDetail
import com.android.code700.extensions.loadImage
import com.android.code700.ui.base.BaseMvpActivity
import com.android.code700.ui.base.BasePresenter
import kotlinx.android.synthetic.main.activity_offer_details.*
import org.jetbrains.anko.intentFor
import java.util.*
import javax.inject.Inject


class OfferDetailActivity : BaseMvpActivity(), OfferDetailContracts.View {

    @Inject
    lateinit var mPresenter: OfferDetailContracts.Presenter<OfferDetailContracts.View>

    companion object {
        const val EXTRA_OFFER_ID = "EXTRA_OFFER_ID"
        fun openActivity(context: Context, offerId: Int) {
            val intent = context.intentFor<OfferDetailActivity>(Pair(EXTRA_OFFER_ID, offerId))
            context.startActivity(intent)
        }
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent?.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offer_details)
        setupLayout()
        mPresenter.onAttachView(this)
    }

    override fun sendExtrasToPresenter(extras: Bundle) {
        val offerId = extras[EXTRA_OFFER_ID] as Int
        mPresenter.initPresenter(offerId)
    }

    private fun setupLayout() {
        supportActionBar?.title = getString(R.string.title_offer_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        tvAddress.setOnClickListener { mPresenter.onAddressClick() }
    }

    public override fun getPresenter(): BasePresenter<*> {
        return mPresenter
    }

    override fun showOfferDetails(offerDetail: OfferDetail) {
        with(offerDetail) {
            tvOfferTitle.text = title
            tvOfferDescription.text = description
            tvBusinessName.text = businessName ?: ""
            ivOfferImage.loadImage(images)
        }
    }

    override fun showAddress(show: Boolean, address: String) {
        tvAddress.visibility = if (show) View.VISIBLE else View.GONE
        tvAddress.text = address
    }

    override fun openAddressLocation(lat: Float, lng: Float) {
        val uri = String.format(Locale.ENGLISH, "geo:%f,%f", lat, lng)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        startActivity(intent)
    }
}
