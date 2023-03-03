package com.example.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.tvshow.databinding.FragmentTVShowDetailBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TVShowDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TVShowDetailFragment : Fragment() {
    private val viewModel: TVShowViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTVShowDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        // Inflate the layout for this fragment
        return binding.root
    }

}