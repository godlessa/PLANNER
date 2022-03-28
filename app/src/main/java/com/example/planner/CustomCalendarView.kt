package com.example.planner

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import java.text.SimpleDateFormat
import java.util.*

class CustomCalendarView(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    val planner: Calendar = Calendar.getInstance(Locale.ENGLISH)
    val cnxt: Context? = context
    val dateFormat: SimpleDateFormat = SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
    val monthFormat: SimpleDateFormat = SimpleDateFormat("MMMM", Locale.ENGLISH);
    val yearFormat: SimpleDateFormat = SimpleDateFormat("yyyy", Locale.ENGLISH);

    var dates: List<Date> = mutableListOf()
    var eventsList: List<Event> = mutableListOf()

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    private fun setupViews(){

    }
}