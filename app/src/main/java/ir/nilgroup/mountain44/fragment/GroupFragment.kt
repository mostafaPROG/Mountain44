package ir.nilgroup.mountain44.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.viewpager.widget.ViewPager
import ir.nilgroup.mountain44.fragment.groupFragments.NotificationFragment
import com.google.android.material.tabs.TabLayout
import ir.nilgroup.mountain44.R
import ir.nilgroup.mountain44.adapter.TabAdapter
import ir.nilgroup.mountain44.fragment.groupFragments.TeamFragment
import ir.nilgroup.mountain44.fragment.groupFragments.PersonFragment

class GroupFragment : AppCompatActivity() {

    private lateinit var tabAdapter: TabAdapter
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var title: TextView
    private lateinit var searchView: SearchView
    private lateinit var backMenu: ImageButton
    private var itemIndex = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.group_layout)

        itemIndex=intent.getIntExtra("item",1)

        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewpager_group)
        toolbar = findViewById(R.id.toolbar_group)
        title = findViewById(R.id.titleGroup)
        searchView = findViewById(R.id.searchGroup)
        backMenu = findViewById(R.id.backMessageApp)
        backMenu.setOnClickListener { onBackPressed() }

        tabAdapter = TabAdapter(supportFragmentManager)
        tabAdapter.addFragment(NotificationFragment(), "رویداد ها")
        tabAdapter.addFragment(TeamFragment(), "گروه ها")
        tabAdapter.addFragment(PersonFragment(), "چت ها")

        val tabIcon: ArrayList<Int> = arrayListOf(
            R.drawable.ic_info_outline_black_24dp,
            R.drawable.ic_people_black_24dp,
            R.drawable.ic_001_chat
        )

        viewPager.adapter = tabAdapter
        viewPager.currentItem = itemIndex

        tabLayout.setupWithViewPager(viewPager)

        tabIcon.forEachIndexed { index, i -> tabLayout.getTabAt(index)!!.setIcon(tabIcon[index]) }

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
//                higLightCurrentTab(position)
                tabLayout.animation = null
                title.text = tabAdapter.getTitle(position)
            }

            @SuppressLint("RestrictedApi")
            override fun onPageSelected(position: Int) {

            }

        })
    }


}