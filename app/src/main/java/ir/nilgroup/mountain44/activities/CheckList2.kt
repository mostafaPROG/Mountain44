package ir.nilgroup.mountain44.activities

import android.content.res.ColorStateList
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.diegodobelo.expandingview.ExpandingItem
import com.diegodobelo.expandingview.ExpandingList
import com.getbase.floatingactionbutton.FloatingActionButton
import ir.nilgroup.mountain44.R

class CheckList2 : AppCompatActivity() {

    private lateinit var addCheck: FloatingActionButton
    private lateinit var clearAll: ImageButton
    private lateinit var mExpandingList: ExpandingList
    private lateinit var back: ImageButton

    private var colorList = ArrayList<String>()
    private var colorDrawable = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_list2)
        mExpandingList = findViewById(R.id.expanding_list_main2);

        addCheck = findViewById(R.id.addCheck)
        clearAll = findViewById(R.id.clearAllCheck)
        back = findViewById(R.id.backCheck)

        colorList = arrayListOf("سبز", "صورتی", "آبی", "بنفش", "نارنجی", "زرد")
        colorDrawable = arrayListOf(
            R.color.green,
            R.color.pink,
            R.color.blue,
            R.color.purple,
            R.color.orange,
            R.color.yellow
        )

        clearAll.setOnClickListener { mExpandingList.removeAllViews() }
        back.setOnClickListener { onBackPressed() }

        addCheck.setOnClickListener {
            createItems()
        }
    }

    private fun createItems() {
        var adapterMe: ArrayAdapter<String> = ArrayAdapter(
            this@CheckList2, android.R.layout.simple_spinner_item
            , colorList
        )
        adapterMe.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_checklist, null)

        dialogView.findViewById<Spinner>(R.id.spinnerCheck).adapter = adapterMe
        val mDialogItem = AlertDialog.Builder(this).setView(dialogView)
            .setCancelable(true)
            .show()
        var position = 0
        dialogView.findViewById<Spinner>(R.id.spinnerCheck).onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                        val background =      dialogView.findViewById<Spinner>(R.id.spinnerCheck).background
//                        val shapeDrawable = background as ShapeDrawable
//                        shapeDrawable.paint.color = ContextCompat.getColor(this@ChecklistActivity, colorDrawable[p2])

                        dialogView.findViewById<Spinner>(R.id.spinnerCheck).backgroundTintList = ColorStateList.valueOf(
                            ContextCompat.getColor(this@CheckList2,colorDrawable[p2]))
                    }
                    position = p2
                }

            }

        dialogView.findViewById<TextView>(R.id.saveChack).setOnClickListener {

            addItem(
                dialogView.findViewById<TextView>(R.id.titleCheck).text.toString(),
                arrayOf(),
                colorDrawable[position], R.drawable.ic_notifications_black_24dp
            )
            mDialogItem.dismiss()
        }
        dialogView.findViewById<TextView>(R.id.cancelCheck).setOnClickListener {
            mDialogItem.dismiss()
        }

    }

    private fun addItem(title: String, subItems: Array<String>, colorRes: Int, iconRes: Int) {
        //Let's create an item with R.layout.expanding_layout
        val item: ExpandingItem = mExpandingList.createNewItem(R.layout.expanding_layout)

        //If item creation is successful, let's configure it
        if (item != null) {
            item.setIndicatorColorRes(colorRes)
            item.setIndicatorIconRes(iconRes)
            //It is possible to get any view inside the inflated layout. Let's set the text in the item
            (item.findViewById(R.id.title) as TextView).text = title

            //We can create items in batch.
            item.createSubItems(subItems.size)
            for (i in 0 until item.subItemsCount) {
                //Let's get the created sub item by its index
                val view = item!!.getSubItemView(i)

                //Let's set some values in
                configureSubItem(item, view, subItems[i])
            }
            item.findViewById<ImageView>(R.id.add_more_sub_items).setOnClickListener {
                showInsertDialog(object : OnItemCreated {
                    override fun itemCreated(title: String) {
                        val newSubItem = item!!.createSubItem()
                        configureSubItem(item, newSubItem!!, title)
                    }
                })
            }

            item!!.findViewById<ImageView>(R.id.remove_item).setOnClickListener { mExpandingList.removeItem(item) }
        }
    }

    private fun configureSubItem(item: ExpandingItem?, view: View, subTitle: String) {
        (view.findViewById(R.id.sub_title) as TextView).text = subTitle
        view.findViewById<ImageView>(R.id.remove_sub_item).setOnClickListener { item!!.removeSubItem(view) }
    }

    private fun showInsertDialog(positive: OnItemCreated) {
        val mDialog = LayoutInflater.from(this).inflate(R.layout.dialog_subitem,null)
        val builder = AlertDialog.Builder(this).setView(mDialog).show()

        mDialog.findViewById<TextView>(R.id.saveSubChack).setOnClickListener {
            positive.itemCreated(mDialog.findViewById<EditText>(R.id.subtitleCheck).text.toString())
            builder.dismiss()
        }
        mDialog.findViewById<TextView>(R.id.cancelSubCheck).setOnClickListener {
            builder.dismiss()
        }
    }

    internal interface OnItemCreated {
        fun itemCreated(title: String)
    }
}
