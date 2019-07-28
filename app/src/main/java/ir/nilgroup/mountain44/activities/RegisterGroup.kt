package ir.nilgroup.mountain44.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import ir.nilgroup.mountain44.R

class RegisterGroup : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_group)


        findViewById<Button>(R.id.saveRegister).setOnClickListener {
            startActivity(Intent(this@RegisterGroup,MainActivity::class.java))
        }
        findViewById<Button>(R.id.cancelRegister).setOnClickListener {
            startActivity(Intent(this@RegisterGroup,MainActivity::class.java))
        }
    }
}
