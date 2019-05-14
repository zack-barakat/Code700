package com.android.code700.ui.searchoffers

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.android.code700.ui.base.BaseMvpActivity
import com.android.code700.R
import com.android.code700.data.model.Offer
import com.android.code700.ui.base.BasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_offers_search.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class OffersSearchActivity : BaseMvpActivity(), OffersSearchContracts.View, SearchView.OnQueryTextListener {

    @Inject
    lateinit var mPresenter: OffersSearchContracts.Presenter<OffersSearchContracts.View>

    private lateinit var mOffersSearchAdapter: OffersSearchAdapter

    public override fun getPresenter(): BasePresenter<*> {
        return mPresenter
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent?.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offers_search)
        setupLayout()
        mPresenter.onAttachView(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val menuItem = menu.findItem(R.id.action_search)
        val searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        menuItem.expandActionView()
        menuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                return false
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                finish()
                return true
            }
        })
        return true
    }

    override fun sendExtrasToPresenter(extras: Bundle) {

    }

    private fun setupLayout() {
        val disposable = Observable.just("")
            .delay(
                resources.getInteger(R.integer.anim_search_reveal_duration) + 100L,
                TimeUnit.MILLISECONDS,
                Schedulers.io()
            )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ animateShowContent() }, {})
        addToSubscription(disposable)
        setupSearchResultRecyclerView()
    }

    private fun animateShowContent() {
        cvSearchContentContainer.alpha = 0f
        cvSearchContentContainer.visibility = View.VISIBLE
        cvSearchContentContainer.animate()
            .alpha(1f)
            .setDuration(resources.getInteger(android.R.integer.config_shortAnimTime).toLong())
            .setListener(null)
            .start()
    }

    private fun setupSearchResultRecyclerView() {
        mOffersSearchAdapter = OffersSearchAdapter { mPresenter.onOfferClick(it) }
        rvSearchOffers.adapter = mOffersSearchAdapter

        val gridManager = LinearLayoutManager(this)
        rvSearchOffers.layoutManager = gridManager
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {
        mPresenter.onQueryChange(newText)
        return true
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showOffersSearchResult(offers: ArrayList<Offer>) {
        tvNoResult.visibility = View.GONE
        rvSearchOffers.visibility = View.VISIBLE
        mOffersSearchAdapter.refreshOffers(offers)
    }

    override fun showEmptyOffersResults() {
        tvNoResult.visibility = View.VISIBLE
        rvSearchOffers.visibility = View.GONE
    }

    override fun openOfferDetail(offerId: Int) {

    }
}
