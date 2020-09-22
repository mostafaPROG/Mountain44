package ir.nilgroup.mountain44.activities

import android.annotation.SuppressLint
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.aminography.primecalendar.PrimeCalendar
import com.aminography.primecalendar.common.CalendarFactory
import com.aminography.primecalendar.common.CalendarType
import com.aminography.primedatepicker.PickType
import com.aminography.primedatepicker.fragment.PrimeDatePickerBottomSheet
import ir.nilgroup.mountain44.R
import ir.nilgroup.mountain44.databinding.ActivityAddEventBinding
import java.util.*

class AddEventActivity : AppCompatActivity(), PrimeDatePickerBottomSheet.OnDayPickedListener {

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var datePicker: PrimeDatePickerBottomSheet? = null
    }
    lateinit var time: EditText
    lateinit var btn: ImageButton
    private lateinit var root:ActivityAddEventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        root = ActivityAddEventBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_add_event)

        time = findViewById(R.id.timeRunEvent)
        btn = findViewById(R.id.btnRunEventTime)
        findViewById<Button>(R.id.saveEvent).setOnClickListener {
            Toast.makeText(this,"با موفقیت اضافه شد", Toast.LENGTH_SHORT)
            finish()
        }
        findViewById<Button>(R.id.cancelEvent).setOnClickListener {
            finish()
        }
        btn.setOnClickListener {
            val calendarType = CalendarType.PERSIAN
            val pickType = PickType.RANGE_START
            val minDateCalendar: PrimeCalendar?
            minDateCalendar = CalendarFactory.newInstance(calendarType)
            minDateCalendar[Calendar.MONTH] -= 5
            val maxDateCalendar: PrimeCalendar?
            maxDateCalendar = CalendarFactory.newInstance(calendarType)
            maxDateCalendar[Calendar.MONTH] += 5
            val typeface = "font/iransansweb.ttf"
            val today = CalendarFactory.newInstance(calendarType)
            datePicker = PrimeDatePickerBottomSheet.newInstance(
                currentDateCalendar = today,
                minDateCalendar = minDateCalendar,
                maxDateCalendar = maxDateCalendar,
                pickType = pickType,
                typefacePath = typeface
            )
            datePicker?.setOnDateSetListener(this)
            datePicker?.show(supportFragmentManager)
        }
        datePicker?.setOnDateSetListener(this)
    }

    override fun onRangeDaysPicked(startDay: PrimeCalendar, endDay: PrimeCalendar) {
        time.setText("${startDay.longDateString}  تا  ${endDay.longDateString}")
    }

    override fun onSingleDayPicked(singleDay: PrimeCalendar) {
    }

}