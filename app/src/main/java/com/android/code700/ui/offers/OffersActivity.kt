package com.android.code700.ui.offers

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import com.android.code700.R
import com.android.code700.data.model.Offer
import com.android.code700.ui.base.BaseMvpActivity
import com.android.code700.ui.base.BasePresenter
import com.android.code700.ui.searchoffers.OffersSearchActivity
import kotlinx.android.synthetic.main.activity_offers.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class OffersActivity : BaseMvpActivity(), OffersContracts.View {

    @Inject
    lateinit var mPresenter: OffersContracts.Presenter<OffersContracts.View>

    private lateinit var mAdapter: OffersAdapter

    private var isLoading: Boolean = false

    public override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent?.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offers)
        setupLayout()
        mPresenter.onAttachView(this)
    }

    override fun sendExtrasToPresenter(extras: Bundle) {

    }

    private fun setupLayout() {
        supportActionBar?.title = getString(R.string.title_offers)
        setupOffersRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val menuItem = menu.findItem(R.id.action_open_search)
        menuItem?.setOnMenuItemClickListener {
            mPresenter.onSearchClick()
            true
        }
        return true
    }

    private fun setupOffersRecyclerView() {
        mAdapter = OffersAdapter { position ->
            mPresenter.onOfferClick(position)
        }
        rvOffers.adapter = mAdapter
        val linearLayoutManager = LinearLayoutManager(this)
        rvOffers.layoutManager = linearLayoutManager

        rvOffers.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = linearLayoutManager.childCount
                val totalItemCount = linearLayoutManager.itemCount
                val pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + pastVisibleItems >= totalItemCount && !isLoading) {
                    isLoading = true
                    mPresenter.loadMoreOffers()
                }
            }
        })

    }

    public override fun getPresenter(): BasePresenter<*> {
        return mPresenter
    }

    override fun showOffers(offers: ArrayList<Offer>) {
        if (offers.size > 0) {
            mAdapter.addItems(offers)
            isLoading = false
        }
    }

    override fun hideLoadingMore() {
        mAdapter.removeLoadingItem()
    }

    override fun showLoadingMore() {
        val offer = Offer(0, "", "", 0, "")
        offer.type = "LOADING"
        mAdapter.addLoadingItem(offer)
    }

    override fun openSearchScreen() {
        startActivity<OffersSearchActivity>()
        overridePendingTransition(R.anim.enter_search_activiaty_animation, android.R.anim.fade_out)
    }

    override fun openOfferScreen(offerId: Int) {

    }
}
