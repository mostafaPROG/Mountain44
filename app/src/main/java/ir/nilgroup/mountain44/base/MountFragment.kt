package ir.nilgroup.mountain44.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import ir.nilgroup.mountain44.R
import ir.nilgroup.mountain44.activities.MountActivity

class MountFragment : Fragment() {

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.mount_card, container, false)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<CardView>(R.id.cardViewMount).setOnClickListener {
            startActivity(Intent(view.context,MountActivity::class.java))
        }
    }

        override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)
            arguments?.let {
                val position = it.getInt(ARG_POSITION)
            }
        }

        companion object {
            const val ARG_POSITION = "position"

            fun newInstance(position: Int): MountFragment {
                return MountFragment().apply {
                    arguments = Bundle().apply { putInt(ARG_POSITION, position) }
                }
            }
        }
    }
