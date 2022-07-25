package com.popular.broadcast.presentation.home

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.popular.broadcast.databinding.FragmentHomeBinding
import com.popular.broadcast.presentation.base.BaseFragment
import com.popular.broadcast.presentation.home.adapter.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
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

        getViewBinding().newsRv.run {

            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }
    }

    private fun fetchNews() {

        lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {

                homeViewModel.uiState.collect { state ->

                    when (state) {

                        is HomeViewModel.NewsUiState.Loaded -> onLoaded(state.itemState)
                        is HomeViewModel.NewsUiState.Error -> showError(state.message)
                        else -> showLoading()
                    }
                }
            }
        }
    }

    private fun onLoaded(homeItemUiState: HomeItemUiState) {

        homeItemUiState.run {

            getViewBinding().sectionTv.text = section
            newsAdapter.update(newsList)
        }
    }

    private fun showLoading() {

        Timber.d("showLoading")
    }

    private fun showError(@StringRes stringRes: Int) {

        Toast.makeText(requireContext(), stringRes, Toast.LENGTH_SHORT).show()
    }
}