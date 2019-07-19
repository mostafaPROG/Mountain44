package ir.nilgroup.mountain44.fragment.loginFragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ir.nilgroup.mountain44.R
import ir.nilgroup.mountain44.activities.MainActivity

class SignUpFragment : Fragment() {

    companion object {
        fun newInstance(): SignUpFragment = SignUpFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.sign_after_fragment, container, false)

        view.findViewById<FloatingActionButton>(R.id.sendInformationSignUp).setOnClickListener {
            startActivity(Intent(activity!!, MainActivity::class.java))
        }

        return view
    }

}

