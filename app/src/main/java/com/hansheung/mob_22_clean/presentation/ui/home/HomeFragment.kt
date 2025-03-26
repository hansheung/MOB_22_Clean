package com.hansheung.mob_22_clean.presentation.ui.home

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.hansheung.mob21firebase.ui.base.BaseFragment
import com.hansheung.mob_22_clean.presentation.adapter.TasksAdapter
import com.hansheung.mob_22_clean.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    override val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: TasksAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun setupUiComponents(view: View) {
        super.setupUiComponents(view)
        setupAdapter()

        binding.fabAdd.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToAddTaskFragment())
        }
    }

    override fun setupViewModelObserver() {
        super.setupViewModelObserver()

        lifecycleScope.launch {
            viewModel.state.collect{

                adapter.setTasks(it.tasks)

            }
        }
    }

    private fun setupAdapter(){
        adapter = TasksAdapter(emptyList())

        binding.rvTasks.adapter = adapter
        binding.rvTasks.layoutManager = LinearLayoutManager(requireContext())

    }
}