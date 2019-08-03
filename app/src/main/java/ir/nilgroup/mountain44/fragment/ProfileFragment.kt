package ir.nilgroup.mountain44.fragment

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Menu
import android.widget.*
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
import ir.nilgroup.mountain44.activities.AddEventActivity
import ir.nilgroup.mountain44.adapter.TableViewAdapter
import ir.nilgroup.mountain44.base.RegisterNumber
import ir.nilgroup.mountain44.fragment.profileFragment.*

class ProfileFragment : AppCompatActivity() {

    private lateinit var toolbar: Toolbar

    private lateinit var tabLayoutGroup: TabLayout
    private lateinit var tabLayoutEvent: TabLayout
    private lateinit var frameLayout: FrameLayout
    lateinit var fragment: Fragment
    lateinit var fragmentEvent: Fragment
    var fragmentManager: FragmentManager? = null
    var fragmentTransaction: FragmentTransaction? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_layout)

        tabLayoutGroup = findViewById(R.id.tablayoutInfo)
        tabLayoutEvent = findViewById(R.id.tabLayoutEvent)
        frameLayout = findViewById(R.id.frameInfoProfiel)


        findViewById<Button>(R.id.addEventBtn).setOnClickListener {
            startActivity(Intent(this, AddEventActivity::class.java))
        }


        fragment = MemberProfileFragment()
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction!!.replace(R.id.frameInfoProfiel, fragment)
        fragmentTransaction!!.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        fragmentTransaction!!.commit()

        fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction!!.replace(R.id.frameGalleryGroup, GalleryFragmentHorizonal())
        fragmentTransaction!!.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        fragmentTransaction!!.commit()

        val tab: TabLayout.Tab = tabLayoutGroup.getTabAt(1)!!
        tab.select()

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
            val dialog = LayoutInflater.from(this).inflate(R.layout.image_dialog, null)
            val builder = AlertDialog.Builder(this).setView(dialog)
            dialog.findViewById<ImageView>(R.id.imageDialog).setImageResource(R.drawable.mojavez)
            builder.show()
            builder.setCancelable(true)
        }
        findViewById<Button>(R.id.btnTagdir).setOnClickListener {
            val dialog = LayoutInflater.from(this).inflate(R.layout.image_dialog, null)
            val builder = AlertDialog.Builder(this).setView(dialog)
            dialog.findViewById<ImageView>(R.id.imageDialog).setImageResource(R.drawable.tagdir)
            builder.show()
            builder.setCancelable(true)
        }
        findViewById<ImageButton>(R.id.backProfileFragment).setOnClickListener {
            onBackPressed()
        }
        findViewById<ImageButton>(R.id.optionMenu).setOnClickListener {
            val popupMenu = PopupMenu(this@ProfileFragment, findViewById<ImageButton>(R.id.optionMenu), Gravity.RIGHT)
            menuInflater.inflate(R.menu.option_menu_prof, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                Toast.makeText(this@ProfileFragment, "You Clicked : " + it.title, Toast.LENGTH_SHORT).show()
                return@setOnMenuItemClickListener true
            }
            popupMenu.show()

        }


    }

}