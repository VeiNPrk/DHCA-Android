package com.vnprk.dhca

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.vnprk.dhca.databinding.RvMaketProjectBinding
import com.vnprk.dhca.models.ProjectEntity

class ResearchRecyclerAdapter(val context:Context, private val projectClickListener: ProjectClickListener) : RecyclerView.Adapter<ResearchRecyclerAdapter.ResultsViewHolder>(){

    var data:List<ProjectEntity> = ArrayList<ProjectEntity>()
    interface ProjectClickListener {
        fun onClick(idProject : Int)
    }

    class ResultsViewHolder(var binding: RvMaketProjectBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(project: ProjectEntity) {
            binding.tvNameProject.text = project.name
            binding.tvDateCreate.text = project.strDateCreate()
            binding.tvDateFinish.text = project.strDateFinish()
            binding.tvPercentComplete.text = project.getPercentComplete()
            binding.tvStatus.isVisible = !project.isFinish
        }
    }

    fun setResultsData(results: List<ProjectEntity>){
        data=results
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: RvMaketProjectBinding = RvMaketProjectBinding.inflate(inflater, parent, false)
        return ResultsViewHolder(binding)
    }

    override fun onBindViewHolder(holder : ResultsViewHolder, i : Int ) {
        holder.bind(data[i])
        holder.binding.root.setOnClickListener {
            projectClickListener.onClick(data[i].id)
        }

    }

    override fun getItemCount() = data.size
}

