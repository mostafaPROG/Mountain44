package ir.nilgroup.mountain44.fragment.loginFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import ir.nilgroup.mountain44.R

class ReceivedSmsFragment : Fragment() {

    companion object {
        fun newInstance(): ReceivedSmsFragment = ReceivedSmsFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.start_after_login, container, false)
        val fr:FragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
        view.findViewById<Button>(R.id.loginCode).setOnClickListener {
            fr.replace(R.id.fragmentLogin, SignUpFragment())
            fr.commit()
        }
        view.findViewById<TextView>(R.id.changenumber).setOnClickListener {
            fr.replace(R.id.fragmentLogin, StartLoginFragment())
            fr.commit()
        }

        return view
    }

}

