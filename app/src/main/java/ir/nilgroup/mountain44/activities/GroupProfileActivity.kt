package ir.nilgroup.mountain44.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import ir.nilgroup.mountain44.R
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import ir.nilgroup.mountain44.adapter.TabAdapter
import iammert.com.view.scalinglib.ScalingLayout
import android.widget.RelativeLayout
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentManager
import iammert.com.view.scalinglib.ScalingLayoutListener
import androidx.core.view.ViewPropertyAnimatorListener
import androidx.core.view.ViewCompat
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import ir.nilgroup.mountain44.fragment.groupProfileFragment.InfoGroupFragment
import iammert.com.view.scalinglib.State
import ir.nilgroup.mountain44.fragment.groupProfileFragment.CalendarFragment
import ir.nilgroup.mountain44.fragment.groupProfileFragment.GalleryFragment


class GroupProfileActivity : AppCompatActivity() {
    private lateinit var chatGroup: RelativeLayout
    private lateinit var chatLeader: RelativeLayout
    /* access modifiers changed from: private */
    lateinit var scalingLayout: ScalingLayout
    private lateinit var tabAdapter: TabAdapter
    private lateinit var tabLayout: TabLayout
    private lateinit var toolbar: Toolbar
    private lateinit var viewPager: ViewPager
    private lateinit var fabIcon: ImageView
    private lateinit var filterLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_profile)

        tabLayout = findViewById(R.id.tabGroupProf)
        viewPager = findViewById(R.id.viewpagerGroupProfile)
        toolbar = findViewById(R.id.toolbarGroupPro)
        val frManneger: FragmentManager = supportFragmentManager
        tabAdapter = TabAdapter(frManneger)
        scalingLayout = findViewById(R.id.scalingLayout)
        chatGroup = findViewById(R.id.chatGroup)
        chatLeader = findViewById(R.id.chatLeader)
        fabIcon = findViewById(R.id.fabIcon)
        filterLayout = findViewById(R.id.filterLayout)

        scalingLayout.setListener(object : ScalingLayoutListener {
            override fun onProgress(progress: Float) {
                if (progress > 0) {
                    fabIcon.setVisibility(View.INVISIBLE);
                }

                if (progress < 1) {
                    filterLayout.setVisibility(View.INVISIBLE);
                }
            }

            override fun onExpanded() {
                ViewCompat.animate(fabIcon).alpha(0f).setDuration(200).start()
                ViewCompat.animate(filterLayout).alpha(1f).setDuration(200)
                    .setListener(object : ViewPropertyAnimatorListener {
                        override fun onAnimationStart(view: View) {
                            filterLayout.visibility = View.VISIBLE
                        }

                        override fun onAnimationEnd(view: View) {
                            fabIcon.visibility = View.INVISIBLE
                        }

                        override fun onAnimationCancel(view: View) {

                        }
                    }).start()
            }

            override fun onCollapsed() {
                ViewCompat.animate(fabIcon).alpha(1f).setDuration(150).start()
                ViewCompat.animate(filterLayout).alpha(0f).setDuration(150)
                    .setListener(object : ViewPropertyAnimatorListener {
                        override fun onAnimationStart(view: View) {
                            fabIcon.visibility = View.VISIBLE
                        }

                        override fun onAnimationEnd(view: View) {
                            filterLayout.visibility = View.INVISIBLE
                        }

                        override fun onAnimationCancel(view: View) {

                        }
                    }).start()
            }

        })

        scalingLayout.setOnClickListener {
            if (scalingLayout.state == State.COLLAPSED) {
                scalingLayout.expand()
            }
        }

        findViewById<ConstraintLayout>(R.id.rootView).setOnClickListener {
            if (scalingLayout.state == State.EXPANDED) {
                scalingLayout.collapse()
            }
        }

        chatLeader.setOnClickListener {
            startActivity(
                Intent(this@GroupProfileActivity, MessageListActivity::class.java)
                    .putExtra("nameSender", "لیدر")
                    .putExtra("state", "آنلاین")
            )
        }
        chatGroup.setOnClickListener {
            startActivity(
                Intent(this@GroupProfileActivity, MessageGroupActivity::class.java)
                    .putExtra("nameGroup", "گروه میلاد")
                    .putExtra("member", "26")
            )
        }

        tabAdapter.addFragment(GalleryFragment(),"گالری")
        tabAdapter.addFragment(CalendarFragment(),"تقویم")
        tabAdapter.addFragment(InfoGroupFragment(),"معرفی")

        viewPager.adapter = tabAdapter
        tabLayout.setupWithViewPager(viewPager)
        viewPager.currentItem = 2
        val tabIcon: ArrayList<Int> = arrayListOf(
            R.drawable.ic_photo_size_select_actual_black_24dp,
            R.drawable.ic_today_black_24dp,
            R.drawable.ic_info_outline_black_24dp
        )

        tabIcon.forEachIndexed { index, _ -> tabLayout.getTabAt(index)!!.setIcon(tabIcon[index]) }

    }

}
