package com.vnprk.dhca.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.vnprk.dhca.R
import com.vnprk.dhca.databinding.FragmentMainBinding
import com.vnprk.dhca.databinding.FragmentResearchDetailBinding
import com.vnprk.dhca.models.Status
import com.vnprk.dhca.viewmodel.AuthorizationViewModel
import com.vnprk.dhca.viewmodel.MainViewModel
import com.vnprk.dhca.viewmodel.ResearchDetailViewModel
import kotlinx.coroutines.flow.collect

class ResearchDetailFragment : Fragment() {

    private lateinit var binding:FragmentResearchDetailBinding
    private val args: ResearchDetailFragmentArgs by navArgs()
    private val viewModel: ResearchDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResearchDetailBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getProjectByIdLiveData(args.idProject).observe(viewLifecycleOwner, { project->
            project?.let{
                binding.tvNameProject.text = project.name
                binding.tvDateCreate.text = project.strDateCreate()
                binding.tvDateFinish.text = project.strDateFinish()
                binding.tvPercentComplete.text = project.getPercentComplete()
                binding.tvStatus.isVisible = !project.isFinish
                binding.btnStart.isEnabled = !project.isFinish
            }
        })
        viewModel.getStateRepositoryLiveData().observe(viewLifecycleOwner, { result->
            when(result.status){
                Status.NOTHING -> binding.layoutProgressData.isVisible = false
                Status.LOADING -> binding.layoutProgressData.isVisible = true
                Status.SUCCESS -> binding.layoutProgressData.isVisible = false
                Status.ERROR -> binding.layoutProgressData.isVisible = false
            }
        })
        viewModel.getStateResearchLiveData().observe(viewLifecycleOwner, {

        })

        binding.btnStart.setOnClickListener {
            viewModel.startOnclick(args.idProject)
        }
    }

}