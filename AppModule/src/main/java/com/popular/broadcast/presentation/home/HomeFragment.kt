package com.popular.broadcast.presentation.home

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
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

        homeViewModel.getNews()
        initUi()
        fetchNews()
    }

    private fun initUi() {

        newsAdapter.registerForItemClick(this)

        getViewBinding().newsRv.run {

            adapter = newsAdapter
        }
    }

    private fun fetchNews() {

        lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {

                homeViewModel.uiState.collect { state ->

                    when (state) {

                        is UiState.Loaded -> onLoaded(state.itemState as HomeItemUiState)
                        is UiState.Error -> showError(state.message)
                        else -> showLoading(true)
                    }
                }
            }
        }
    }

    private fun onLoaded(homeItemUiState: HomeItemUiState) {

        homeItemUiState.run {

            newsAdapter.update(newsList)
        }

        showLoading(false)
    }

    private fun showError(@StringRes stringRes: Int) {

        Toast.makeText(requireContext(), stringRes, Toast.LENGTH_SHORT).show()

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

        val action = HomeFragmentDirections.actionNavigationHomeToNavigationDetail(news)
        findNavController().navigate(action)
    }
}