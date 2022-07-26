package com.popular.broadcast.presentation.detail

import androidx.navigation.fragment.navArgs
import androidx.viewbinding.ViewBinding
import com.popular.broadcast.databinding.FragmentDetailBinding
import com.popular.broadcast.presentation.base.BaseFragment

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private val args: DetailFragmentArgs by navArgs()

    override fun constructViewBinding(): ViewBinding = FragmentDetailBinding.inflate(layoutInflater)

    override fun init(viewBinding: ViewBinding) {

        getViewBinding().news = args.newsItem
    }
}