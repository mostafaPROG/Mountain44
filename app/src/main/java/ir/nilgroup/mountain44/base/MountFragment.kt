package ir.nilgroup.mountain44.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.nilgroup.mountain44.R

class MountFragment : Fragment() {

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.mount_card, container, false)
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
