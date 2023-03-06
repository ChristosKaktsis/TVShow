package com.example.tvshow.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tvshow.databinding.FragmentTVShowDetailBinding

/**
 * A simple [Fragment] subclass.
 * Use the [TVShowDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TVShowDetailFragment : Fragment() {

    private val viewModel: TVShowDetailViewModel by viewModels()

    companion object {
        val SHOWID = "showId"
    }
    private lateinit var showId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            showId = it.getString(SHOWID).toString()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTVShowDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.getTVShow(showId)
        // Inflate the layout for this fragment
        return binding.root
    }

}