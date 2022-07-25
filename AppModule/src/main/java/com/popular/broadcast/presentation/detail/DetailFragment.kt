package com.popular.broadcast.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.popular.broadcast.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var detailViewModel: DetailViewModel
    private var _binding: FragmentDetailBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDetail
        detailViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        return root
    }

    override fun onDestroyView() {

        super.onDestroyView()
        _binding = null
    }
}