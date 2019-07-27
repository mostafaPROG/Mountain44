package ir.nilgroup.mountain44.fragment.profileFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.nilgroup.mountain44.R

class InfoFragmentProfile : Fragment() {

    companion object {
        fun newInstance(): InfoFragmentProfile = InfoFragmentProfile()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.introduction_group, container, false)

        return view
    }

}

