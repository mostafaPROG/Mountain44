package ir.nilgroup.mountain44.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.HorizontalScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.NestedScrollView
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.navigation.NavigationView
import com.santalu.autoviewpager.AutoViewPager
import ir.nilgroup.mountain44.R
import ir.nilgroup.mountain44.adapter.EventFavAdapter
import ir.nilgroup.mountain44.adapter.GroupFavAdapter
import ir.nilgroup.mountain44.adapter.MountFavAdapter
import ir.nilgroup.mountain44.databinding.ActivityMain3Binding
import ir.nilgroup.mountain44.databinding.ActivityMainBinding
import ir.nilgroup.mountain44.databinding.ContentScrollingBinding
import ir.nilgroup.mountain44.fragment.AppFragment
import ir.nilgroup.mountain44.fragment.GroupFragment
import ir.nilgroup.mountain44.fragment.MapFragment
import ir.nilgroup.mountain44.fragment.ProfileFragment
import java.util.logging.Logger
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private lateinit var viewPagerMount: AutoViewPager
    private lateinit var viewPagerGroup: AutoViewPager
    private lateinit var viewPagerEvent: AutoViewPager
    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mNavigation: NavigationView
    private lateinit var mToggle: ActionBarDrawerToggle
    private lateinit var toolbar: Toolbar
    private lateinit var collapsingToolbar: CollapsingToolbarLayout
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var nestedScrollView: NestedScrollView
    private lateinit var constBig: ConstraintLayout
    private lateinit var loginBtn: TextView
    private var countExit = 0
    private lateinit var root:ActivityMainBinding
    private lateinit var root3:ContentScrollingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        root = ActivityMainBinding.inflate(layoutInflater)
        root3 = ContentScrollingBinding.inflate(layoutInflater)
        setContentView(root.root)

        constBig = root!!.layoutConstraintBig
        nestedScrollView = root!!.nestedScrollView
        loginBtn = root!!.registerGroupMain
        appBarLayout = root!!.appbar

        loginBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, RegisterGroup::class.java))
        }

        root.addEventHome.setOnClickListener {
            startActivity(Intent(this@MainActivity,AddEventActivity::class.java))
        }

        root.notificationHome.setOnClickListener {
            startActivity(Intent(this@MainActivity,GroupFragment::class.java).putExtra("item",0))
        }
        root.chatGroupHome.setOnClickListener {
            startActivity(Intent(this@MainActivity,GroupFragment::class.java).putExtra("item",2))
        }

        configureNavigationDrawer()
        configureToolbar()

        viewpagers()

        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, i ->
            Logger.getLogger("offset")
                .info("offset is : $i     total is :${appBarLayout.totalScrollRange}")
            var newOffset = abs(i)
            when {
                newOffset in 501..999 -> {

//                    if (newOffset != appBarLayout.totalScrollRange) {
//                        constSm.visibility = View.VISIBLE
//                        fadeIn(constSm)
//                    }
                    toolbar.visibility = View.INVISIBLE
                }
                newOffset < 100 -> {
//                    constSm.visibility = View.INVISIBLE
                    toolbar.visibility = View.VISIBLE
                }
                newOffset > 1000 -> {
                    toolbar.visibility = View.VISIBLE
                }
            }
        })

        val typeface: Typeface = Typeface.createFromAsset(this.assets, "font/yekan.ttf")

        collapsingToolbar = findViewById(R.id.collapsingToolbarL)
        collapsingToolbar.apply {
            setCollapsedTitleTypeface(typeface)
            setExpandedTitleTypeface(typeface)
        }

        mToggle = ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close)

    }

    private fun viewpagers() {
        viewPagerMount = findViewById(R.id.viewpager_mount)
        viewPagerGroup = findViewById(R.id.viewpager_group)
        viewPagerEvent = findViewById(R.id.viewpager_event)

        viewPagerMount.apply {
            adapter = MountFavAdapter(supportFragmentManager)
            //autoScroll = true
            //indeterminate = true
        }
        viewPagerGroup.apply {
            adapter = GroupFavAdapter(supportFragmentManager)
            //autoScroll = true
            //indeterminate = true
        }
        viewPagerEvent.apply {
            adapter = EventFavAdapter(supportFragmentManager)
            //autoScroll = true
            //indeterminate = true
        }
    }

    private fun configureToolbar() {
        toolbar = root.toolsToolbarL
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        toolbar.setNavigationOnClickListener {
            if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                mDrawerLayout.closeDrawer(Gravity.RIGHT)
            } else {
                mDrawerLayout.openDrawer(Gravity.RIGHT)
            }
        }
    }

    private fun configureNavigationDrawer() {
        mDrawerLayout = root.drawerLayoutL
        mNavigation = root.navigationViewL

        mNavigation.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.checkListDr -> {
                    startActivity(Intent(this@MainActivity, CheckList2::class.java))
                    return@setNavigationItemSelectedListener true
                }
                R.id.loginDr -> {
                    startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                    return@setNavigationItemSelectedListener true
                }
                R.id.addGroup -> {
                    startActivity(Intent(this@MainActivity, RegisterGroup::class.java))
                    return@setNavigationItemSelectedListener true
                }
                R.id.profileDr -> {
                    startActivity(Intent(this@MainActivity, ProfileFragment::class.java))
                    return@setNavigationItemSelectedListener true
                }
                R.id.groupDr -> {
                    startActivity(Intent(this@MainActivity, GroupFragment::class.java).putExtra("item",2))
                    return@setNavigationItemSelectedListener true
                }
                R.id.toolsDr -> {
                    startActivity(Intent(this@MainActivity, AppFragment::class.java))
                    return@setNavigationItemSelectedListener true
                }
                R.id.mapDr -> {
                    startActivity(Intent(this@MainActivity, MapFragment::class.java))
                    return@setNavigationItemSelectedListener true
                }
                R.id.storeDr -> {
                    startActivity(Intent(this@MainActivity, StoreActivity::class.java))
                    return@setNavigationItemSelectedListener true
                }
                R.id.exitAccount -> {
                    finishAffinity()
                    return@setNavigationItemSelectedListener true
                }
                else -> return@setNavigationItemSelectedListener false
            }
        }
    }

    fun menuCardClick(v: View) {
        startActivity(
            when (v.id) {
                R.id.toolsCardJ -> Intent(this@MainActivity, AppFragment()::class.java)
                R.id.toolsCardL -> Intent(this@MainActivity, AppFragment()::class.java)

                R.id.teachCardL -> Intent(this@MainActivity, CourseActivity()::class.java)
                R.id.teachCardJ -> Intent(this@MainActivity, CourseActivity()::class.java)

                R.id.profileCardL -> Intent(this@MainActivity, ProfileFragment()::class.java)
                R.id.profileCardJ -> Intent(this@MainActivity, ProfileFragment()::class.java)

                R.id.groupCardL -> Intent(this@MainActivity, GroupFragment()::class.java)
                R.id.groupCardJ -> Intent(this@MainActivity, GroupFragment()::class.java)

                R.id.mapCardL -> Intent(this@MainActivity, MapFragment()::class.java)
                R.id.mapCardJ -> Intent(this@MainActivity, MapFragment()::class.java)

                else -> Intent(this@MainActivity, StoreActivity()::class.java)
            }
        )
    }

    fun fadeIn(v: View) {
        val fadeIn: Animation = AlphaAnimation(0f, 1f)
        fadeIn.duration = 800
        v.animation = fadeIn
    }

    fun fadeOut(v: View) {
        val fadeOut: Animation = AlphaAnimation(1f, 0f)
        fadeOut.duration = 200
        v.animation = fadeOut
    }

    @SuppressLint("RtlHardcoded")
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null && item.itemId == android.R.id.home) {
            if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                mDrawerLayout.closeDrawer(Gravity.RIGHT)
            } else {
                mDrawerLayout.openDrawer(Gravity.RIGHT)
            }
        }
        return false
    }

    var doubleBackToExitPressedOnce = false

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finishAffinity()
        } else {
            this.doubleBackToExitPressedOnce = true
            Toast.makeText(this, "برای خروج دوباره کلیک کنید", Toast.LENGTH_SHORT).show()
        }

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 1000)
    }

}