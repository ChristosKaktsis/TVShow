package com.example.tvshow.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tvshow.R
import com.example.tvshow.databinding.FragmentTVShowListBinding
import com.example.tvshow.network.TVShow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TVShowListFragment : Fragment() {
private val viewModel: TVShowViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentTVShowListBinding.inflate(inflater)
        //viewModel.getTVShows()
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter =
            TVShowListAdapter(TVShowListener
            { tvShow: TVShow ->
                viewModel.onTVShowClicked(tvShow)
                val action = TVShowListFragmentDirections.actionTVShowListFragmentToTVShowDetailFragment(tvShow.id)
                findNavController().navigate(action)
            })
        return binding.root
       // return inflater.inflate(R.layout.fragment_t_v_show_list, container, false)
    }
}