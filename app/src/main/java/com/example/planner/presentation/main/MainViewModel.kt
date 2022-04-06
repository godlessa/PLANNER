package com.example.planner.presentation.main

import androidx.lifecycle.ViewModel
import com.example.planner.data.repository.MainRepository
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class MainViewModel @Inject constructor(
    val repository: MainRepository
) : ViewModel() {
    // TODO: Implement the ViewModel
}