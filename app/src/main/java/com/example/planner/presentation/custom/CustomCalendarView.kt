package com.example.planner.presentation.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.planner.data.local.entities.EventEntity
import com.example.planner.databinding.CalendarLayoutBinding
import java.text.SimpleDateFormat
import java.util.*

open class CustomCalendarView @JvmOverloads constructor
    (context: Context,
     attrs: AttributeSet? = null,
     defStyleAttr: Int = 0,
     defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {
    private var binding: CalendarLayoutBinding =
        CalendarLayoutBinding.inflate(LayoutInflater.from(context),this,true)
    private var planner: Calendar = Calendar.getInstance(Locale.ENGLISH)
    private var dateFormat: SimpleDateFormat = SimpleDateFormat("MMMM yyyy", Locale.ENGLISH)

    init{
        setupViews()
        setUpCalendar()
    }


    val monthFormat: SimpleDateFormat = SimpleDateFormat("MMMM", Locale.ENGLISH);
    val yearFormat: SimpleDateFormat = SimpleDateFormat("yyyy", Locale.ENGLISH);
    var dates: List<Date> = mutableListOf()
    var eventsList: List<EventEntity> = mutableListOf()

    private fun setupViews() {
        with(binding) {
            butPreviousMounth.setOnClickListener {
                planner.add(Calendar.MONTH, -1)
                setUpCalendar()
            }
            butNextMounth.setOnClickListener{
                planner.add(Calendar.MONTH, 1)
                setUpCalendar()
        }
    }
    }

    private fun setUpCalendar(){
        val currentDate: String = dateFormat.format(planner.time)
        binding.tvCurrentDate.text = currentDate
    }

    fun getMonth(): String{
        return ""
    }

    fun getYear(): String{
        return ""
    }
}