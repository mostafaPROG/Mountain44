package ir.nilgroup.mountain44.fragment.groupProfileFragment

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aminography.primecalendar.common.CalendarFactory
import com.aminography.primecalendar.common.CalendarType
import com.aminography.primedatepicker.calendarview.PrimeCalendarView
import com.aminography.primedatepicker.monthview.PrimeMonthView
import ir.nilgroup.mountain44.R
import java.util.*

class CalendarFragment : Fragment() {
    private var calendarType = CalendarType.PERSIAN
    lateinit var calendarView:PrimeCalendarView
    companion object {
        fun newInstance(): CalendarFragment = CalendarFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.calender_fragment, container, false)

        calendarView = view.findViewById(R.id.calendarView)

        calendarView.calendarType = calendarType
        calendarView.typeface = Typeface.createFromAsset(activity!!.assets,"font/iransansweb.ttf")
        calendarView.goto(CalendarFactory.newInstance(calendarType),false)

        calendarView.flingOrientation =PrimeCalendarView.FlingOrientation.HORIZONTAL
        calendarView.locale=CalendarFactory.newInstance(calendarType).locale



        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState?.apply {
            putInt("CALENDAR_TYPE", calendarType.ordinal)
        }
        super.onSaveInstanceState(outState)
    }
}