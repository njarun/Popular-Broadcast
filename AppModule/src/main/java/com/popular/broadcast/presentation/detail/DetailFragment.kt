package com.popular.broadcast.presentation.detail

import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.popular.broadcast.databinding.FragmentDetailBinding
import com.popular.broadcast.presentation.base.BaseFragment

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private lateinit var detailViewModel: DetailViewModel

    override fun constructViewBinding(): ViewBinding = FragmentDetailBinding.inflate(layoutInflater)

    override fun init(viewBinding: ViewBinding) {

        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        initUi()
    }

    private fun initUi() {

        /*detailViewModel.text.observe(viewLifecycleOwner, Observer {
            getViewBinding().textDetail.text = it
        })*/
    }
}