package edr.practica.practicafinalpmdm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import com.google.android.material.floatingactionbutton.FloatingActionButton
import edr.practica.practicafinalpmdm.models.DatosCliente
import edr.practica.practicafinalpmdm.models.RecogidaDatosViewModel


class RecogidaDatosFragment : Fragment() {

    private lateinit var v: View
    private val datos: RecogidaDatosViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_recogida_datos, container, false)

        v.findViewById<FloatingActionButton>(R.id.floatingActionEnviarFin).setOnClickListener {
            var nombreCompleto =
                v.findViewById<TextView>(R.id.editTextNombreCompleto).text.toString()
            var email = v.findViewById<TextView>(R.id.editTextTextEmailAddress).text.toString()
            var numeroTelefono = v.findViewById<TextView>(R.id.editTextPhone).text.toString()

            val datosCliente = DatosCliente(
                nombreCompleto,
                email,
                numeroTelefono
            )
            datos.addCliente(datosCliente)

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