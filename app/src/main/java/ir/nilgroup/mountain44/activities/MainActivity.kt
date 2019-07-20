package ir.nilgroup.mountain44.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.NestedScrollView
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.navigation.NavigationView
import ir.nilgroup.mountain44.R
import ir.nilgroup.mountain44.fragment.AppFragment
import ir.nilgroup.mountain44.fragment.GroupFragment
import ir.nilgroup.mountain44.fragment.MapFragment
import ir.nilgroup.mountain44.fragment.ProfileFragment
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mNavigation: NavigationView
    private lateinit var mToggle: ActionBarDrawerToggle
    private lateinit var toolbar: Toolbar
    private lateinit var collapsingToolbar: CollapsingToolbarLayout
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var nestedScrollView: NestedScrollView
    private lateinit var constBig: ConstraintLayout
    private lateinit var constSm: ConstraintLayout
    private lateinit var loginBtn: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        constSm = findViewById(R.id.layout_constraintSm)
        constBig = findViewById(R.id.layout_constraintBig)
        nestedScrollView = findViewById(R.id.nestedScrollView)
        loginBtn = findViewById(R.id.loginMain)
        appBarLayout = findViewById(R.id.appbar)

        loginBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        }

        configureNavigationDrawer()
        configureToolbar()

        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, i ->
            Logger.getLogger("offset").info("offset is : $i")
            var newOffset = Math.abs(i)
            when {
                newOffset > 500 -> {
//                    constBig.visibility = View.GONE
//                    constSm.visibility = View.VISIBLE
//                    fadeOut(constBig)
                    constSm.visibility = View.VISIBLE
                    fadeIn(constSm)
                }
                newOffset < 100 -> {
//                    constBig.visibility = View.VISIBLE
//                    constSm.visibility = View.GONE
//                fadeIn(constBig)
                    constSm.visibility = View.INVISIBLE
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

    private fun configureToolbar() {
        toolbar = findViewById(R.id.tools_toolbarL)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                mDrawerLayout.closeDrawer(Gravity.RIGHT)
            } else {
                mDrawerLayout.openDrawer(Gravity.RIGHT)
            }
        }
    }

    private fun configureNavigationDrawer() {
        mDrawerLayout = findViewById(R.id.drawer_layoutL)
        mNavigation = findViewById(R.id.navigation_viewL)

        mNavigation.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.checkListDr -> {
                    startActivity(Intent(this@MainActivity,ChecklistActivity::class.java))
                    return@setNavigationItemSelectedListener true
                }
//                R.id.tools_drw -> {
//                    val intent = Intent(this@MainActivity, AppFragment()::class.java)
//                    startActivity(intent)
//                    return@setNavigationItemSelectedListener true
//                }
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

                else -> Intent(this@MainActivity, AppFragment()::class.java)
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


    override fun onBackPressed() {
        finishAffinity()
    }
}