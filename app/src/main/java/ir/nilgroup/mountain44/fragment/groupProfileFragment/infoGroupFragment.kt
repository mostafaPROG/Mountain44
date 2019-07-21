package com.example.project1.fragment.groupFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.nilgroup.mountain44.R

class infoGroupFragment : Fragment() {

    companion object {
        fun newInstance(): infoGroupFragment = infoGroupFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.info_fragment, container, false)
}