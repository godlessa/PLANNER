package com.example.planner.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.planner.data.local.entities.EventEntity
import com.example.planner.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: MainFragmentBinding
    private val viewModel: MainViewModel by activityViewModels()

    private var currentMonth: String = ""
    private var currentYear: String = ""

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = MainFragmentBinding.inflate(inflater)
        setupViews()
        return binding.root
    }

    private fun setupViews() {
        binding.calendarView.visibility = View.VISIBLE
        currentMonth = binding.calendarView.getMonth()
        currentYear = binding.calendarView.getYear()
    }

}