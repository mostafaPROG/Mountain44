package ir.nilgroup.mountain44.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import ir.nilgroup.mountain44.R
import ir.nilgroup.mountain44.adapter.TableViewAdapter
import ir.nilgroup.mountain44.base.RegisterNumber
import ir.nilgroup.mountain44.fragment.profileFragment.*

class ProfileFragment : AppCompatActivity() {

    private lateinit var toolbar: Toolbar

    private lateinit var tabLayoutGroup: TabLayout
    private lateinit var tabLayoutEvent: TabLayout
    private lateinit var frameLayout: FrameLayout
    lateinit var fragment:Fragment
    lateinit var fragmentEvent:Fragment
    var fragmentManager: FragmentManager? = null
    var fragmentTransaction: FragmentTransaction? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_layout)

        tabLayoutGroup = findViewById(R.id.tablayoutInfo)
        tabLayoutEvent = findViewById(R.id.tabLayoutEvent)
        frameLayout = findViewById(R.id.frameInfoProfiel)


        fragment = InfoFragmentProfile()
        fragmentManager =supportFragmentManager
        fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction!!.replace(R.id.frameInfoProfiel, fragment)
        fragmentTransaction!!.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        fragmentTransaction!!.commit()

        fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction!!.replace(R.id.frameGalleryGroup, GalleryFragmentHorizonal())
        fragmentTransaction!!.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        fragmentTransaction!!.commit()

        tabLayoutGroup!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                // creating cases for fragment
                when (tab.position) {
                    0 -> fragment = InfoFragmentProfile()
                    1 -> fragment = MemberProfileFragment()
                }
                val fm = supportFragmentManager
                val ft = fm.beginTransaction()
                ft.replace(R.id.frameInfoProfiel, fragment)
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                ft.commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        val fragmentManager2 = supportFragmentManager
        val fragmentTransaction2 = fragmentManager2!!.beginTransaction()
        fragmentTransaction2.replace(R.id.frameEvent, CurrentEvent.newInstance())
        fragmentTransaction2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        fragmentTransaction2.commit()

        tabLayoutEvent!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                // creating cases for fragment
                when (tab.position) {
                    0 -> fragmentEvent = CurrentEvent.newInstance()
                    1 -> fragmentEvent = RuningEvent.newInstance()
                    2 -> fragmentEvent = FinishEvent.newInstance()
                }
                val fm = supportFragmentManager
                val ft = fm.beginTransaction()
                ft.replace(R.id.frameEvent, fragmentEvent)
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                ft.commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })


        findViewById<Button>(R.id.btnCertificate).setOnClickListener {

        }

    }





}