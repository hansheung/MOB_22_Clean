package com.hansheung.mob_22_clean.presentation.ui.manageTask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hansheung.mob21firebase.ui.base.BaseFragment
import com.hansheung.mob_22_clean.databinding.FragmentManageTaskBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddTaskFragment: BaseFragment() {

    override val viewModel : AddTaskViewModel by viewModels()
    private lateinit var binding: FragmentManageTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManageTaskBinding.inflate(
            inflater,
            container,
            false)

        return binding.root
    }

    override fun setupUiComponents(view: View) {
        super.setupUiComponents(view)

        binding.btnSubmit.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val desc = binding.etDesc.text.toString()
            viewModel.addTask(title, desc)
        }
    }

    override fun setupViewModelObserver() {
        super.setupViewModelObserver()
        lifecycleScope.launch {
            viewModel.success.collect{
                findNavController().popBackStack()
            }
        }
    }
}