package com.example.planner.presentation.custom

import android.app.AlertDialog
import android.app.TimePickerDialog
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TimePicker
import com.example.planner.R
import com.example.planner.data.local.entities.EventEntity
import com.example.planner.databinding.CalendarLayoutBinding
import com.example.planner.databinding.EventDetailsFragmentBinding
import java.text.SimpleDateFormat
import java.util.*

open class CustomCalendarView @JvmOverloads constructor
    (
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

    companion object {
        const val MAX_CALENDAT_DAYS = 42
    }

    private var binding: CalendarLayoutBinding =
        CalendarLayoutBinding.inflate(LayoutInflater.from(context), this, true)
    private var planner: Calendar = Calendar.getInstance(Locale.ENGLISH)
    private var dateFormat: SimpleDateFormat = SimpleDateFormat("MMMM yyyy", Locale.ENGLISH)

    init {
        setupViews()
        setUpCalendar()
    }


    val monthFormat: SimpleDateFormat = SimpleDateFormat("MMMM", Locale.ENGLISH)
    val yearFormat: SimpleDateFormat = SimpleDateFormat("yyyy", Locale.ENGLISH)
    var dates: MutableList<Date> = mutableListOf()
    var eventsList: List<EventEntity> = mutableListOf()

    private fun setupViews() {
        with(binding) {
            butPreviousMounth.setOnClickListener {
                planner.add(Calendar.MONTH, -1)
                setUpCalendar()
            }
            butNextMounth.setOnClickListener {
                planner.add(Calendar.MONTH, 1)
                setUpCalendar()
            }
            gridView.setOnItemClickListener { _, _, position: Int, _ ->

            }
        }
    }

    private fun setUpCalendar() {
        val currentDate: String = dateFormat.format(planner.time)
        binding.tvCurrentDate.text = currentDate

        dates = mutableListOf()
        var monthCalendar: Calendar = planner.clone() as Calendar
        monthCalendar.set(Calendar.DAY_OF_MONTH, 1)
        var firstDayOfMonth: Int = monthCalendar.get(Calendar.DAY_OF_WEEK) - 1
        monthCalendar.add(Calendar.DAY_OF_MONTH, -firstDayOfMonth)

        while (dates.size < MAX_CALENDAT_DAYS) {
            dates.add(monthCalendar.time)
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1)
        }


    }

    fun getMonth(): String {
        return ""
    }

    fun getYear(): String {
        return ""
    }

    private fun createAlertDialog() {
        var builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setCancelable(true)
        var binding: EventDetailsFragmentBinding =
            EventDetailsFragmentBinding.inflate(LayoutInflater.from(context), this, true)
        //val addView: View = LayoutInflater.from(context).inflate(R.layout.event_details_fragment, null)
        binding.ibutSetTime.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            var hours: Int = calendar.get(Calendar.HOUR_OF_DAY)
            var minutes: Int = calendar.get(Calendar.MINUTE)
            var timePickerDialog: TimePickerDialog = TimePickerDialog(
                binding.llayCreatingEvent.context,
                androidx.appcompat.R.style.Theme_AppCompat_Dialog,
                { timePicker: TimePicker, hoursOfDay: Int, minutesOfDay: Int ->
                    val clndr: Calendar = Calendar.getInstance()
                    clndr.set(Calendar.HOUR_OF_DAY, hoursOfDay)
                    clndr.set(Calendar.MINUTE, minutesOfDay)
                    clndr.timeZone = TimeZone.getDefault()
                    val formatDateTime: SimpleDateFormat = SimpleDateFormat("K:mm a", Locale.ENGLISH)
                    binding.tvEventTime.text = formatDateTime.format(clndr.time)
                }, hours, minutes, false)
            timePickerDialog.show()
        }
    }
}