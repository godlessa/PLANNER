package com.example.planner.presentation.main

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.planner.data.local.entities.EventEntity
import com.example.planner.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: MainFragmentBinding
    private val viewModel: MainViewModel by activityViewModels()

    private var currentMonth: String = ""
    private var currentYear: String = ""

    companion object {
        fun newInstance() = MainFragment()
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = MainFragmentBinding.inflate(inflater)
        setupViews()

        var dateFormatMonth = SimpleDateFormat("MMMM", Locale.ENGLISH)
        var dateFormatYear = SimpleDateFormat("yyyy", Locale.ENGLISH)
        var planner: Calendar = Calendar.getInstance(Locale.ENGLISH)
        Log.d("DATABASE PLANNER ", dateFormatMonth.format(planner.time))
        Log.d("DATABASE PLANNER ", dateFormatYear.format(planner.time))

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.getEventsCurrentMonthFomDB(dateFormatMonth.format(planner.time),
                dateFormatYear.format(planner.time))
                .collect { uploadResult ->
                    Log.d("DATABASE PLANNER ", uploadResult.size.toString())
                }
        }

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun setupViews() {
        binding.calendarView.visibility = View.VISIBLE
        currentMonth = binding.calendarView.getMonth()
        currentYear = binding.calendarView.getYear()
    }

}