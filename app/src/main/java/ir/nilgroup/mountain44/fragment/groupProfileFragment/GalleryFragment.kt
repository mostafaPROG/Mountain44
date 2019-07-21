package com.example.project1.fragment.groupFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import ir.nilgroup.mountain44.Base.GalleryData
import ir.nilgroup.mountain44.R
import ir.nilgroup.mountain44.adapter.GalleryAdapter

class GalleryFragment : Fragment() {

    lateinit var adapter: GalleryAdapter
    lateinit var recyclerView: RecyclerView

    companion object {
        fun newInstance(): GalleryFragment = GalleryFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.gallery_fragment, container, false)
        var arrylist:ArrayList<GalleryData>

        return view
    }
}

