package edr.practica.practicafinalpmdm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.google.android.material.floatingactionbutton.FloatingActionButton
import edr.practica.practicafinalpmdm.utils.DatePicker


class RecogidaDatosFragment : Fragment() {

    private lateinit var v: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_recogida_datos, container, false)

        v.findViewById<FloatingActionButton>(R.id.floatingActionEnviarFin).setOnClickListener {
            var nombreCompleto = v.findViewById<TextView>(R.id.editTextNombreCompleto).text.toString()
            var email = v.findViewById<TextView>(R.id.editTextTextEmailAddress).text.toString()
            var numeroTelefono = v.findViewById<TextView>(R.id.editTextPhone).text.toString()

            val fm: FragmentManager = parentFragmentManager
            fm.commit {
                replace(R.id.fragmentContainerView, DefaultFragment.newInstance())
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