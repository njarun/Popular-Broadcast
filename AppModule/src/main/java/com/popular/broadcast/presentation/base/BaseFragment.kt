package com.popular.broadcast.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.popular.broadcast.presentation.MainActivity
import com.popular.broadcast.presentation.base.handler.AppInterface
import com.popular.broadcast.presentation.home.HomeFragment

abstract class BaseFragment<T> : Fragment(), AppInterface {

    private var viewBinding: ViewBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = constructViewBinding()
        viewBinding?.let { init(it) }
        return viewBinding?.root
    }

    @Suppress("UNCHECKED_CAST")
    fun getViewBinding(): T = viewBinding as T

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    abstract fun constructViewBinding(): ViewBinding
    abstract fun init(viewBinding: ViewBinding)

    override fun onStart() {

        super.onStart()

        if(activity is MainActivity) {
            (activity as MainActivity).binding.actionBar.rightIcon.visibility =
                determineActionItemsVisibility()
        }
    }

    private fun determineActionItemsVisibility() =
        if (this is HomeFragment) View.VISIBLE else View.GONE

    override fun onCallback(vararg any: Any) {

    }
}