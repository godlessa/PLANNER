package com.example.planner.presentation.custom

import android.app.AlertDialog
import android.app.TimePickerDialog
import android.content.Context
import android.graphics.Color
import android.graphics.Color.*
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.core.view.get
import androidx.fragment.app.activityViewModels
import com.example.planner.App
import com.example.planner.App.Companion.applicationContext
import com.example.planner.R
import com.example.planner.data.local.entities.EventEntity
import com.example.planner.databinding.CalendarLayoutBinding
import com.example.planner.databinding.EventDetailsFragmentBinding
import com.example.planner.databinding.TimePickerLayoutBinding
import com.example.planner.presentation.adapter.PlannerAdapter
import com.example.planner.presentation.main.MainViewModel
import java.text.SimpleDateFormat
import java.util.*


@RequiresApi(Build.VERSION_CODES.Q)
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

    val monthFormat: SimpleDateFormat = SimpleDateFormat("MMMM", Locale.ENGLISH)
    val yearFormat: SimpleDateFormat = SimpleDateFormat("yyyy", Locale.ENGLISH)
    var dates: MutableList<Date> = mutableListOf()
    var eventsList: List<EventEntity> = mutableListOf()

    lateinit var alertDialog: AlertDialog
    lateinit var alertDialogTP: AlertDialog

    private var binding: CalendarLayoutBinding =
        CalendarLayoutBinding.inflate(LayoutInflater.from(context), this, true)
    private var planner: Calendar = Calendar.getInstance(Locale.ENGLISH)
    private var dateFormat: SimpleDateFormat = SimpleDateFormat("MMMM yyyy", Locale.ENGLISH)

    private lateinit var plannerAdapter: PlannerAdapter

    init {
        setUpCalendar()
        setupViews()

    }

    private var positionCurrent = 0

    @RequiresApi(Build.VERSION_CODES.Q)
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
                positionCurrent = position
                createAlertDialogAddEvent()
            }
        }
        with(binding.gridView) {
            adapter = plannerAdapter
        }
    }

    private fun setUpCalendar() {
        val currentDate: String = dateFormat.format(planner.time)
        binding.tvCurrentDate.text = currentDate

        dates = mutableListOf()
        var monthCalendar: Calendar = planner.clone() as Calendar
        monthCalendar.set(Calendar.DAY_OF_MONTH, 1)
        var firstDayOfMonth: Int = monthCalendar.get(Calendar.DAY_OF_WEEK) - 2
        monthCalendar.add(Calendar.DAY_OF_MONTH, -firstDayOfMonth)

        while (dates.size < MAX_CALENDAT_DAYS) {
            dates.add(monthCalendar.time)
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        plannerAdapter = PlannerAdapter(
            currentDate = planner,
            eventsList = eventsList,
            dates = dates
        )

    }

    fun getMonth(): String {
        return ""
    }

    fun getYear(): String {
        return ""
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun createAlertDialogAddEvent() {
        Log.d("PLANNER", "alert dialog AddEvent open please")
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setCancelable(true)
        //var bindingTP: TimePickerLayoutBinding = TimePickerLayoutBinding.inflate(LayoutInflater.from(context), this, false)
        var bindingE: EventDetailsFragmentBinding =
            EventDetailsFragmentBinding.inflate(LayoutInflater.from(context), this, false)
        bindingE.ibutSetTime.setOnClickListener {
            createAlertDialogAddTime(bindingE)
        }
        bindingE.mbutAddEvent.setOnClickListener {
            alertDialog.dismiss()
            binding.gridView.get(positionCurrent).background = App.applicationContext().getDrawable(android.R.color.holo_purple)
            return@setOnClickListener
        }
        builder.setView(bindingE.root)
        alertDialog = builder.create()
        alertDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.show()

    }

    private fun createAlertDialogAddTime(bindingED: EventDetailsFragmentBinding) {
        Log.d("PLANNER", "alert dialog AddTime open please")
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setCancelable(true)
        var bindingTP: TimePickerLayoutBinding =
            TimePickerLayoutBinding.inflate(LayoutInflater.from(context), this, false)

        val calendar: Calendar = Calendar.getInstance()
        var hours: Int = calendar.get(Calendar.HOUR_OF_DAY)
        var minutes: Int = calendar.get(Calendar.MINUTE)

        bindingTP.tmTimePiker.setOnTimeChangedListener { timePicker, hoursOfDay, minutesOfDay ->
            val clndr: Calendar = Calendar.getInstance()
            clndr[Calendar.HOUR_OF_DAY] = hoursOfDay
            clndr[Calendar.MINUTE] = minutesOfDay
            clndr.timeZone = TimeZone.getDefault()
            val formatDateTime: SimpleDateFormat =
                SimpleDateFormat("K:mm a", Locale.ENGLISH)
            bindingED.tvEventTime.text = formatDateTime.format(clndr.time)
        }

        bindingTP.mbutOkEvent.setOnClickListener {
            alertDialogTP.dismiss()
        }
        bindingTP.mbutCancelEvent.setOnClickListener {
            alertDialogTP.dismiss()
        }

        builder.setView(bindingTP.root)
        alertDialogTP = builder.create()
        alertDialogTP.window!!.setBackgroundDrawableResource(android.R.color.transparent);
        alertDialogTP.show()

    }
}