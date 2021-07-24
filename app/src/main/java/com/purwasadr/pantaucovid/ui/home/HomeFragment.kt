package com.purwasadr.pantaucovid.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.purwasadr.pantaucovid.data.Resource
import com.purwasadr.pantaucovid.databinding.FragmentHomeBinding
import com.purwasadr.pantaucovid.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getCovidData()
    }

    private fun getCovidData() {
        viewModel.getCovidData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    it.data?.also { covid ->
                        binding.covid = covid
                    }

                }
            }
        }
    }
}