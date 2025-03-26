package com.hansheung.mob_22_clean.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hansheung.mob_22_clean.domain.model.Task
import com.hansheung.mob_22_clean.databinding.ItemLayoutTaskBinding

class TasksAdapter(
    private var tasks: List<Task>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutTaskBinding.inflate(
            inflater,
            parent,
            false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = tasks[position]
        if(holder is TaskViewHolder){
            holder.bind(item)
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    fun setTasks(Tasks: List<Task>){
        this.tasks = Tasks
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(
        private var binding: ItemLayoutTaskBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(task: Task){
            binding.tvTitle.text = task.title
            binding.tvDesc.text = task.desc
        }
    }
}