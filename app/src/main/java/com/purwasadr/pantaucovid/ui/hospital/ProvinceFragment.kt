package com.purwasadr.pantaucovid.ui.hospital

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.purwasadr.pantaucovid.adapter.HospitalAdapter
import com.purwasadr.pantaucovid.databinding.FragmentHospitalBinding
import com.purwasadr.pantaucovid.databinding.FragmentProvinceBinding
import com.purwasadr.pantaucovid.ui.main.MainViewModel

class ProvinceFragment : Fragment() {
    private var _binding: FragmentProvinceBinding? = null

    private val binding get() = _binding!!

    private val viewModel by activityViewModels<MainViewModel>()

    private val listAdapter by lazy {
        HospitalAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProvinceBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun setupList() {
        binding.list.setHasFixedSize(true)
        binding.list.layoutManager = LinearLayoutManager(requireContext())
    }

}