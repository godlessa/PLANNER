package com.example.planner.presentation.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.annotation.RequiresApi
import com.example.planner.App
import com.example.planner.R
import com.example.planner.data.local.entities.EventEntity
import com.example.planner.databinding.ItemPlannerBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class PlannerAdapter(
    currentDate: Calendar,
    eventsList: List<EventEntity>,
    dates: List<Date>,
) : BaseAdapter() {
    private val dates = dates
    private val eventsList = eventsList
    private val currentDate = currentDate

    override fun getCount(): Int {
        return dates.size
    }

    override fun getItem(p0: Int): Date {
        return dates.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val binding: ItemPlannerBinding =
            ItemPlannerBinding.inflate(LayoutInflater.from(App.applicationContext()))

        val monthDate = dates[p0]
        val dateCalendar: Calendar = Calendar.getInstance()
        dateCalendar.time = monthDate

        val dayNo = dateCalendar.get(Calendar.DAY_OF_MONTH)
        val displayMonth = dateCalendar.get(Calendar.MONTH) + 1
        val displayYear = dateCalendar.get(Calendar.YEAR)

        var view: View = binding.root
        if (p1 != null)
            view = p1

        if (displayMonth == currentDate.get(Calendar.MONTH) + 1 &&
            displayYear == currentDate.get(Calendar.YEAR)
        )
        //view.setBackgroundColor(App.applicationContext().getColor(R.color.E0EDFF))
            binding.tvDateDay.setTextColor(App.applicationContext().getColor(R.color.E425573))
        else binding.tvDateDay.setTextColor(App.applicationContext().getColor(R.color.E85A8DC))

        binding.tvDateDay.text = dayNo.toString()
        val eventCalendar: Calendar = Calendar.getInstance()
        var countEventsForIsDay = 0
        for (event: EventEntity in eventsList) {
            eventCalendar.time = convertStringToDate(event.date)
            if (dayNo == eventCalendar.get(Calendar.DAY_OF_MONTH) &&
                displayMonth == eventCalendar.get(Calendar.MONTH) + 1 &&
                displayYear == eventCalendar.get(Calendar.YEAR)
            ) {
                countEventsForIsDay++
            }

        }
        if(countEventsForIsDay != 0)
            binding.tvCountEventsDay.text = countEventsForIsDay.toString() + "ev"
        else
            binding.tvCountEventsDay.text = " "
        return view
    }

    private fun convertStringToDate(dateInString: String): Date? {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        var date: Date? = null
        try {
            date = format.parse(dateInString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date
    }
}