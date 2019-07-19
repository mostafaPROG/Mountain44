package ir.nilgroup.mountain44.fragment.loginFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import ir.nilgroup.mountain44.R

class StartLoginFragment : Fragment() {

    companion object {
        fun newInstance(): StartLoginFragment = StartLoginFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.start_login, container, false)
        val fr:FragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
        view.findViewById<Button>(R.id.receivedCode).setOnClickListener {
            fr.replace(R.id.fragmentLogin, ReceivedSmsFragment())
            fr.commit()
        }

        return view
    }

}

