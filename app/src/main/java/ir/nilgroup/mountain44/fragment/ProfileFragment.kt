package ir.nilgroup.mountain44.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import ir.nilgroup.mountain44.R

class ProfileFragment:AppCompatActivity() {

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_layout)

        toolbar = findViewById(R.id.toolbarProfile)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }
}