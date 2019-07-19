package ir.nilgroup.mountain44.activities

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.getbase.floatingactionbutton.FloatingActionButton
import ir.nilgroup.mountain44.R

class ChecklistActivity : AppCompatActivity() {

    private lateinit var addCheck: FloatingActionButton
    private lateinit var clearAll: ImageButton

    private var colorList = ArrayList<String>()
    private var colorDrawable = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_list)

        addCheck = findViewById(R.id.addCheck)
        clearAll = findViewById(R.id.clearAllCheck)

        colorList = arrayListOf("سبز", "صورتی", "آبی", "بنفش", "نارنجی", "زرد")
        colorDrawable = arrayListOf(
            R.color.green,
            R.color.pink,
            R.color.blue,
            R.color.purple,
            R.color.orange,
            R.color.yellow
        )

        addCheck.setOnClickListener {
            
        }

    }

}
