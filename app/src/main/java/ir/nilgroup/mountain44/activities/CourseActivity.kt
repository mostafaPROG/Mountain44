package ir.nilgroup.mountain44.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.nilgroup.mountain44.base.Course
import ir.nilgroup.mountain44.base.EndlessRecyclerOnScrollListener
import ir.nilgroup.mountain44.R
import ir.nilgroup.mountain44.adapter.CourseAdapter


class CourseActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: CourseAdapter
    val list: ArrayList<Course> = ArrayList()
    private var isLoading: Boolean = false
    lateinit var layoutManager: LinearLayoutManager
    private lateinit var handler: Handler

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.course_activity)
        toolbar = findViewById(R.id.courseToolbar)

        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        list.add(
            Course(
                "این دوره شامل  13 سرفصل کوهنوردی و سرفصل های دوره طبیعت گردی است، علاقمندان به دریافت گواهینامه راهنمای تخصصی کوهستان (راهنمای کوهستان سطح ملی)  می بایست مدرک \"راهنمای کوهستان سطح باشگاه ها\" و \"مدرک راهنمای طبیعت گردی\" را دریافت نمایند. برای اطلاع دقیق از سرفصل\u200Cها، قسمت «درباره برنامه» را در همین صفحه مطالعه کنید. ",
                "سنگ نوردی"
            )
        )
        list.add(
            Course(
                "این دوره شامل  13 سرفصل کوهنوردی و سرفصل های دوره طبیعت گردی است، علاقمندان به دریافت گواهینامه راهنمای تخصصی کوهستان (راهنمای کوهستان سطح ملی)  می بایست مدرک \"راهنمای کوهستان سطح باشگاه ها\" و \"مدرک راهنمای طبیعت گردی\" را دریافت نمایند. برای اطلاع دقیق از سرفصل\u200Cها، قسمت «درباره برنامه» را در همین صفحه مطالعه کنید. ",
                "کوهنوردی"
            )
        )
        list.add(
            Course(
                "این دوره شامل  13 سرفصل کوهنوردی و سرفصل های دوره طبیعت گردی است، علاقمندان به دریافت گواهینامه راهنمای تخصصی کوهستان (راهنمای کوهستان سطح ملی)  می بایست مدرک \"راهنمای کوهستان سطح باشگاه ها\" و \"مدرک راهنمای طبیعت گردی\" را دریافت نمایند. برای اطلاع دقیق از سرفصل\u200Cها، قسمت «درباره برنامه» را در همین صفحه مطالعه کنید. ",
                "دوره مربی گری"
            )
        )
        list.add(
            Course(
                "این دوره شامل  13 سرفصل کوهنوردی و سرفصل های دوره طبیعت گردی است، علاقمندان به دریافت گواهینامه راهنمای تخصصی کوهستان (راهنمای کوهستان سطح ملی)  می بایست مدرک \"راهنمای کوهستان سطح باشگاه ها\" و \"مدرک راهنمای طبیعت گردی\" را دریافت نمایند. برای اطلاع دقیق از سرفصل\u200Cها، قسمت «درباره برنامه» را در همین صفحه مطالعه کنید. ",
                "دوره مربی گری"
            )
        )
        list.add(
            Course(
                "این دوره شامل  13 سرفصل کوهنوردی و سرفصل های دوره طبیعت گردی است، علاقمندان به دریافت گواهینامه راهنمای تخصصی کوهستان (راهنمای کوهستان سطح ملی)  می بایست مدرک \"راهنمای کوهستان سطح باشگاه ها\" و \"مدرک راهنمای طبیعت گردی\" را دریافت نمایند. برای اطلاع دقیق از سرفصل\u200Cها، قسمت «درباره برنامه» را در همین صفحه مطالعه کنید. ",
                "دوره مربی گری"
            )
        )
        list.add(
            Course(
                "این دوره شامل  13 سرفصل کوهنوردی و سرفصل های دوره طبیعت گردی است، علاقمندان به دریافت گواهینامه راهنمای تخصصی کوهستان (راهنمای کوهستان سطح ملی)  می بایست مدرک \"راهنمای کوهستان سطح باشگاه ها\" و \"مدرک راهنمای طبیعت گردی\" را دریافت نمایند. برای اطلاع دقیق از سرفصل\u200Cها، قسمت «درباره برنامه» را در همین صفحه مطالعه کنید. ",
                "دوره مربی گری"
            )
        )
        list.add(
            Course(
                "این دوره شامل  13 سرفصل کوهنوردی و سرفصل های دوره طبیعت گردی است، علاقمندان به دریافت گواهینامه راهنمای تخصصی کوهستان (راهنمای کوهستان سطح ملی)  می بایست مدرک \"راهنمای کوهستان سطح باشگاه ها\" و \"مدرک راهنمای طبیعت گردی\" را دریافت نمایند. برای اطلاع دقیق از سرفصل\u200Cها، قسمت «درباره برنامه» را در همین صفحه مطالعه کنید. ",
                "دوره مربی گری"
            )
        )
        recyclerView = findViewById(R.id.recyclerCourse)
        recyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        adapter = CourseAdapter(list, this)
        recyclerView.adapter = adapter
        handler = Handler()

        recyclerView.setOnScrollListener(object : EndlessRecyclerOnScrollListener(layoutManager) {
            override fun onLoadMore(current_page: Int) {
                if (list.size < 20) {
                    //add progress item
                    list.add(Course("", ""))
                    adapter.notifyItemInserted(list.size)
                    handler.postDelayed({
                        //remove progress item
                        list.removeAt(list.size - 1)
                        adapter.notifyItemRemoved(list.size)
                        //add items one by one
                        for (i in 0..6) {
                            list.add(list[i])
                            adapter.notifyItemInserted(list.size)
                        }
                        //or you can add all at once but do not forget to call mAdapter.notifyDataSetChanged();
                    }, 2000)
                    println("load")
                }
            }
        })

    }

}


