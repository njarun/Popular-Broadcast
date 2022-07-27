package com.popular.broadcast.presentation.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.popular.broadcast.R
import com.popular.broadcast.databinding.FragmentHomeBinding
import com.popular.broadcast.domain.schedule.model.News
import com.popular.broadcast.presentation.base.BaseFragment
import com.popular.broadcast.presentation.base.state.UiState
import com.popular.broadcast.presentation.home.adapter.NewsAdapter
import com.popular.broadcast.presentation.home.state.HomeItemUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    @Inject
    lateinit var newsAdapter: NewsAdapter

    private val homeViewModel: HomeViewModel by viewModels()

    override fun constructViewBinding(): ViewBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun init(viewBinding: ViewBinding) {

        initUi()
        loadNewsToUi()
    }

    override fun onResume() {

        super.onResume()

        refreshNewsList(false)
    }

    private fun initUi() {

        newsAdapter.registerForItemClick(this)

        getViewBinding().run {

            newsRv.adapter = newsAdapter
            swipeRefreshLayout.setOnRefreshListener {

                refreshNewsList(true)
            }
        }
    }

    private fun loadNewsToUi() {

        lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {

                homeViewModel.uiState.collect { state ->

                    when (state) {

                        is UiState.Loaded -> onLoaded(state.itemState as HomeItemUiState)
                        is UiState.Error -> {

                            showToast(state.message)
                            showLoading(false)
                        }
                        else -> {

                            hideToast()
                            showLoading(true)
                        }
                    }
                }
            }
        }
    }

    private fun refreshNewsList(alwaysNotJustError: Boolean) {

        if(alwaysNotJustError || homeViewModel.uiState.value is UiState.Error) {

            homeViewModel.fetchNews()
            showToast(R.string.fetching_news_from_server)
        }
    }

    private fun onLoaded(homeItemUiState: HomeItemUiState) {

        homeItemUiState.run {

            val recyclerViewState = getViewBinding().newsRv.layoutManager?.onSaveInstanceState()
            newsAdapter.update(newsList)
            getViewBinding().newsRv.layoutManager?.onRestoreInstanceState(recyclerViewState)
        }

        showLoading(false)
    }

    private fun showLoading(state: Boolean) {

        getViewBinding().run {
            loading = state
        }
    }

    override fun onCallback(vararg any: Any) {

        if(any.isNotEmpty()) {

            val news = any[0] as News
            openNewsDetail(news)
        }
    }

    private fun openNewsDetail(news: News) {

        hideToast()

        val action = HomeFragmentDirections.actionNavigationHomeToNavigationDetail(news)
        findNavController().navigate(action)
    }
}