package ir.nilgroup.mountain44.activities

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.hanks.htextview.base.HTextView
import ir.nilgroup.mountain44.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        val textview: HTextView = findViewById(R.id.textview2)
        textview.animateText("کـــوهنورد")
        textview.typeface = Typeface.createFromAsset(assets, "font/iransansweb_medium.ttf")

        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        }, 3000)

    }
}
