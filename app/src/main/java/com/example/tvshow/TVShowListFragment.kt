package com.example.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tvshow.databinding.FragmentTVShowListBinding
import com.example.tvshow.network.TVShow


class TVShowListFragment : Fragment() {
private val viewModel:TVShowViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentTVShowListBinding.inflate(inflater)
        //viewModel.getTVShows()
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = TVShowListAdapter(TVShowListener
        { tvShow: TVShow ->
            viewModel.onTVShowClicked(tvShow)
            findNavController().navigate(R.id.action_TVShowListFragment_to_TVShowDetailFragment)
        })
        return binding.root
       // return inflater.inflate(R.layout.fragment_t_v_show_list, container, false)
    }
}