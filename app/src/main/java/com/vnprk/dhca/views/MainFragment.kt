package com.vnprk.dhca.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.vnprk.dhca.R
import com.vnprk.dhca.SharedPreferencesUtils
import com.vnprk.dhca.databinding.FragmentMainBinding
import com.vnprk.dhca.viewmodel.MainViewModel

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvMyLogin.text = SharedPreferencesUtils.getMyLogin(requireContext())
        binding.btnReserchList.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToResearchListFragment())
        }
        binding.btnHistory.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToHistoryFragment())
        }
    }

}