package edr.practica.practicafinalpmdm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit

private lateinit var v: View

class RecogidaDatosFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_default, container, false)

        v.findViewById<Button>(R.id.buttonbookNow).setOnClickListener {
            val fm: androidx.fragment.app.FragmentManager = parentFragmentManager
            fm.commit {
                replace(edr.practica.practicafinalpmdm.R.id.fragmentContainerView, edr.practica.practicafinalpmdm.ReservaFragment.newInstance())
                addToBackStack("replacement")
            }
            true
        }
        return v
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            RecogidaDatosFragment().apply {

            }
    }
}