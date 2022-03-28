package com.example.planner

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.P)
class DBOpenHelper(
    context: Context?,
    name: String?,
    version: Int,
    openParams: SQLiteDatabase.OpenParams
) : SQLiteOpenHelper(context, name, version, openParams) {

    companion object{
        val DB_Name = "EVENTS_DB"
        val DB_Version = 1
        val EVENT_TABLE_NAME = "events_table"
        val EVENT = "event"
        val TIME = "time"
        val DATE = "date"
        val MONTH = "month"
        val YEAR = "year"
    }
    private val CREATE_EVENT_TABLE: String =
        "CREATE TABLE" + EVENT_TABLE_NAME +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT,  " +
                EVENT + " TEXT, " +
                TIME + " TEXT, " +
                DATE + " TEXT, " +
                MONTH + " TEXT, " +
                YEAR + " TEXT) "

    private val DROP_EVENT_TABBLE = "DROP TABLE IF EXISTS" + EVENT_TABLE_NAME



    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(CREATE_EVENT_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL(DROP_EVENT_TABBLE)
        onCreate(p0)
    }

    public fun saveEvent(event: String,
                         time: String,
                         date: String,
                         month: String,
                         year: String,
                         db: SQLiteDatabase
    ){
        val contentValues: ContentValues = ContentValues()
        contentValues.put(EVENT, event)
        contentValues.put(TIME, time)
        contentValues.put(DATE, date)
        contentValues.put(MONTH, month)
        contentValues.put(YEAR, year)
        db.insert(EVENT_TABLE_NAME, null, contentValues)
    }

    public fun readEvents(date: String, db: SQLiteDatabase): Cursor? {
        val Projections = {EVENT;TIME;DATE;MONTH;YEAR } as Array<String>
        val Selection= DATE + "=?"
        val SelectionArgs = {date} as Array<String>

        return db.query(EVENT_TABLE_NAME, Projections, Selection, SelectionArgs,null, null, null )
    }

    public fun readEventsMonth(month: String, year: String, db: SQLiteDatabase): Cursor? {
        val Projections = {EVENT;TIME;DATE;MONTH;YEAR } as Array<String>
        val Selection= MONTH + "=? and " + YEAR + "=?"
        val SelectionArgs = {month; year} as Array<String>

        return db.query(EVENT_TABLE_NAME, Projections, Selection, SelectionArgs,null, null, null )
    }
}