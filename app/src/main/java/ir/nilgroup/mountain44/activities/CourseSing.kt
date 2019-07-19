package ir.nilgroup.mountain44.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import ir.nilgroup.mountain44.R


class CourseSing : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cours_sing)

        val string = intent.getStringExtra("description").toString()
        val pic = intent.getIntExtra("pic", 1)
        val titlePage = intent.getStringExtra("title")
        findViewById<TextView>(R.id.descriptionCourseActivity).text = string
        val image = findViewById<ImageView>(R.id.app_bar_image)
        val title = findViewById<TextView>(R.id.titleSing)
        title.text = titlePage

        val stringBuilder: String = "p$pic"
        val id = resources.getIdentifier(stringBuilder, "drawable", packageName)
        Glide.with(this@CourseSing).load(id).into(image)
    }

}
