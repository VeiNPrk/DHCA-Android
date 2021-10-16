package com.vnprk.dhca.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.vnprk.dhca.R
import com.vnprk.dhca.ResearchRecyclerAdapter
import com.vnprk.dhca.databinding.FragmentMainBinding
import com.vnprk.dhca.databinding.FragmentResearchListBinding
import com.vnprk.dhca.viewmodel.MainViewModel

class ResearchListFragment : Fragment(), ResearchRecyclerAdapter.ProjectClickListener {
    private lateinit var binding: FragmentResearchListBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var mAdapter: ResearchRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResearchListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mAdapter =
            context?.let { ResearchRecyclerAdapter(requireContext(),this) }!!
        binding.rvResearchList.setHasFixedSize(true)
        binding.rvResearchList.layoutManager = mLayoutManager
        binding.rvResearchList.adapter = mAdapter
        binding.rvResearchList.itemAnimator = DefaultItemAnimator()
        viewModel.getProjectsLiveData().observe(viewLifecycleOwner, {
            mAdapter.setResultsData(it)
            binding.rvResearchList.scheduleLayoutAnimation()
            //tvEmpty.visibility = if(it.isEmpty()) View.VISIBLE else View.GONE
        })
        viewModel.initRemoteProjects()
    }

    override fun onClick(idProject: Int) {
        findNavController().navigate(ResearchListFragmentDirections.actionResearchListFragmentToResearchDetailFragment(idProject))
    }

}