package com.example.planner.presentation.custom

import android.app.AlertDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import com.example.planner.R
import com.example.planner.data.local.entities.EventEntity
import com.example.planner.databinding.CalendarLayoutBinding
import com.example.planner.databinding.EventDetailsFragmentBinding
import com.example.planner.databinding.TimePickerLayoutBinding
import com.example.planner.presentation.adapter.PlannerAdapter
import com.example.planner.presentation.main.MainViewModel
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

    val monthFormat: SimpleDateFormat = SimpleDateFormat("MMMM", Locale.ENGLISH)
    val yearFormat: SimpleDateFormat = SimpleDateFormat("yyyy", Locale.ENGLISH)
    var dates: MutableList<Date> = mutableListOf()
    var eventsList: List<EventEntity> = mutableListOf()

    lateinit var alertDialog:AlertDialog

    private var binding: CalendarLayoutBinding =
        CalendarLayoutBinding.inflate(LayoutInflater.from(context), this, true)
    private var planner: Calendar = Calendar.getInstance(Locale.ENGLISH)
    private var dateFormat: SimpleDateFormat = SimpleDateFormat("MMMM yyyy", Locale.ENGLISH)

    private lateinit var plannerAdapter: PlannerAdapter

    init {
        setUpCalendar()
        setupViews()

    }

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
                createAlertDialog()
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
    private fun createAlertDialog() {
        Log.d("PLANNER", "alert dialog open please")
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setCancelable(true)
        //var bindingTP: TimePickerLayoutBinding = TimePickerLayoutBinding.inflate(LayoutInflater.from(context), this, false)
        var binding: EventDetailsFragmentBinding =
            EventDetailsFragmentBinding.inflate(LayoutInflater.from(context), this, false)
        binding.ibutSetTime.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            var hours: Int = calendar.get(Calendar.HOUR_OF_DAY)
            var minutes: Int = calendar.get(Calendar.MINUTE)

            var timePickerDialog: TimePickerDialog = TimePickerDialog(
                binding.llayCreatingEvent.context,
                R.style.BlueTimePickerStyle,
                { view, hoursOfDay, minutesOfDay ->
                    val clndr: Calendar = Calendar.getInstance()
                    clndr[Calendar.HOUR_OF_DAY] = hoursOfDay
                    clndr[Calendar.MINUTE] = minutesOfDay
                    clndr.timeZone = TimeZone.getDefault()
                    val formatDateTime: SimpleDateFormat =
                        SimpleDateFormat("K:mm a", Locale.ENGLISH)
                    binding.tvEventTime.text = formatDateTime.format(clndr.time)
                }, hours, minutes, false
            )
            timePickerDialog.show()
        }
        binding.mbutAddEvent.setOnClickListener{
            alertDialog.dismiss()
        }
        builder.setView(binding.root)
        alertDialog = builder.create()
        alertDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.show()

    }
}