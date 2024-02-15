package edr.practica.practicafinalpmdm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import edr.practica.practicafinalpmdm.databinding.FragmentConsultarReservaBinding
import edr.practica.practicafinalpmdm.databinding.FragmentDefaultBinding

class DefaultFragment : Fragment() {

    private lateinit var v: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_default, container, false)

        v.findViewById<Button>(R.id.buttonConsultarViaje).setOnClickListener {
            val fm: FragmentManager = parentFragmentManager
            fm.commit {
                replace(R.id.fragmentContainerView, ConsultarReservaFragment.newInstance())
                addToBackStack("replacement")
            }
            true
        }

        v.findViewById<Button>(R.id.buttonbookNow).setOnClickListener {
            val fm: FragmentManager = parentFragmentManager
            fm.commit {
                replace(R.id.fragmentContainerView, ReservaFragment.newInstance())
                addToBackStack("replacement")
            }
            true
        }
        return v
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            DefaultFragment().apply {

            }
    }
}