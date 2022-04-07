package com.example.planner.presentation.adapter

import android.content.Context
import android.util.AttributeSet
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import com.example.planner.data.local.entities.EventEntity
import java.util.*

class plannerAdapter @JvmOverloads constructor
    (
    context: Context,
    resource: Int,
    objects: Array<EventEntity>
) : ArrayAdapter(context, resource, objects) {
}