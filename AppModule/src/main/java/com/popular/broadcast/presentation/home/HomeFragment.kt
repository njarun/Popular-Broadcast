package com.popular.broadcast.presentation.home

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.popular.broadcast.databinding.FragmentHomeBinding
import com.popular.broadcast.domain.schedule.model.News
import com.popular.broadcast.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun constructViewBinding(): ViewBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun init(viewBinding: ViewBinding) {

        getViewBinding().apply {

            lifecycleOwner = activity

            viewModel = homeViewModel
            adapter = NewsAdapter(listOf(), homeViewModel)
        }

        homeViewModel.getInteractor().observe(this) {

            it.getContentIfNotHandled()?.let { transportData ->

                when(transportData) {

                    is HomeViewModel.Interactor.UserClick -> {

                        openNewsDetail(transportData.news)
                    }
                    is HomeViewModel.Interactor.Message -> {

                        showToast(transportData.message)
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.checkIfDataHasToBeFetched()
    }

    private fun openNewsDetail(news: News) {

        val action = HomeFragmentDirections.actionNavigationHomeToNavigationDetail(news)
        findNavController().navigate(action)
    }
}