package ir.nilgroup.mountain44.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.smarteist.autoimageslider.SliderView
import ir.nilgroup.mountain44.R
import ir.nilgroup.mountain44.adapter.SliderAdapter
import ir.nilgroup.mountain44.base.SliderData
import ir.nilgroup.mountain44.databinding.ActivityMountBinding

private lateinit var root: ActivityMountBinding
private val mountGallery = listOf(SliderData(image = R.drawable.koh1), SliderData(image = R.drawable.koh2),SliderData(image = R.drawable.koh3))
private lateinit var sliderView:SliderView
private lateinit var sliderAdapter: SliderAdapter

class MountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        root = ActivityMountBinding.inflate(layoutInflater)
        setContentView(root!!.root)
        setSupportActionBar(findViewById(R.id.toolbar))

        sliderAdapter = SliderAdapter(this, mountGallery)
        root.slideMountView.setSliderAdapter(sliderAdapter)
    }
}