package ir.nilgroup.mountain44.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import ir.nilgroup.mountain44.R
import ir.nilgroup.mountain44.fragment.loginFragment.StartLoginFragment

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val fr: FragmentTransaction = supportFragmentManager.beginTransaction()
        fr.replace(R.id.fragmentLogin, StartLoginFragment())
        fr.commit()
    }
}
