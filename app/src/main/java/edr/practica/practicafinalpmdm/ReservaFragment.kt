package edr.practica.practicafinalpmdm

import android.app.AlertDialog
import android.location.Address
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import com.google.android.material.floatingactionbutton.FloatingActionButton
import edr.practica.practicafinalpmdm.models.DatosViaje
import edr.practica.practicafinalpmdm.models.ReservaViewModel
import edr.practica.practicafinalpmdm.utils.*

class ReservaFragment : Fragment() {
    private lateinit var v: View

    private lateinit var fechaSalidaTextView: TextView
    private lateinit var fechaRegresoTextView: TextView
    private lateinit var horaSalidaTextView: TextView
    private lateinit var horaRegresoTextView: TextView
    // Declaraciones para gps
    private lateinit var locationHelper: LocationHelper
    private val viaje: ReservaViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_reserva, container, false)

        // Set up click listeners
        v.findViewById<FloatingActionButton>(R.id.floatingActionEnviar).setOnClickListener {

            var salida = v.findViewById<TextView>(R.id.editTextSalida).text.toString()
            var fechaSalida = v.findViewById<TextView>(R.id.textViewFechaSalida).text.toString()
            var horaSalida = v.findViewById<TextView>(R.id.textViewHoraSalida).text.toString()
            var destino = v.findViewById<TextView>(R.id.editTextDestino).text.toString()
            var fechaRetorno = v.findViewById<TextView>(R.id.textViewFechaRegreso).text.toString()
            var horaRetorno = v.findViewById<TextView>(R.id.textViewHoraRegreso).text.toString()
            var pasajeros = v.findViewById<TextView>(R.id.editTextPasajeros).text.toString()

            val datosViaje = DatosViaje(
                salida,
                fechaSalida,
                horaSalida,
                destino,
                fechaRetorno,
                horaRetorno,
                pasajeros.toInt()
            )
            locationHelper.stopLocationUpdates()
            viaje.addViaje(datosViaje)
            val fm: FragmentManager = parentFragmentManager
            fm.commit {
                replace(R.id.fragmentContainerView, RecogidaDatosFragment.newInstance())
                addToBackStack("replacement")
            }
        }

        locationHelper = LocationHelper(requireContext())
        v.findViewById<Button>(R.id.buttonSetLocation).setOnClickListener {
            locationHelper.getLocation { address ->
                activity?.runOnUiThread {
                    address?.let { setLocation(it) }
                }
            }
        }
        v.findViewById<Button>(R.id.buttonFechaSalida).setOnClickListener {
            val fm = childFragmentManager
            fm.commit {
                val newFragment = DatePicker()
                newFragment.show(fm, "datePickerDeparture")
            }
            true
        }
        v.findViewById<Button>(R.id.buttonFechaRegreso).setOnClickListener {
            val fm = childFragmentManager
            fm.commit {
                val newFragment = DatePicker()
                newFragment.show(fm, "datePickerReturn")
            }
            true
        }
        v.findViewById<Button>(R.id.buttonHoraSalida).setOnClickListener {
            val fm = childFragmentManager
            fm.commit {
                val newFragment = TimePicker()
                newFragment.show(fm, "timePickerDeparture")
            }
            true
        }
        v.findViewById<Button>(R.id.buttonHoraRegreso).setOnClickListener {
            val fm = childFragmentManager
            fm.commit {
                val newFragment = TimePicker()
                newFragment.show(fm, "timePickerReturn")
            }
            true
        }


        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fechaSalidaTextView = view.findViewById(R.id.textViewFechaSalida)
        fechaRegresoTextView = view.findViewById(R.id.textViewFechaRegreso)
        horaSalidaTextView = view.findViewById(R.id.textViewHoraSalida)
        horaRegresoTextView = view.findViewById(R.id.textViewHoraRegreso)
        val editTextSalida = v.findViewById<EditText>(R.id.editTextSalida)
        editTextSalida.setOnClickListener {
            if (editTextSalida.text.toString().isEmpty()) {
                showSetLocationDialog()
            }
        }

    }

    private fun setLocation(address: Address) {
        val addressText = buildString {
            for (i in 0..address.maxAddressLineIndex) {
                append(address.getAddressLine(i)).append("\n")
            }
        }
        v.findViewById<EditText>(R.id.editTextSalida).setText(addressText)
    }

    private fun showSetLocationDialog() {
        AlertDialog.Builder(context)
            .setTitle("Fijar Ubicacion")
            .setMessage("Quieres fijar su ubicacion actual como el sitio de recogida?")
            .setPositiveButton("Si") { dialog, which ->
                locationHelper.getLocation { address ->
                    activity?.runOnUiThread {
                        address?.let { setLocation(it) }
                    }
                }
            }
            .setNegativeButton("No", null)
            .show()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ReservaFragment().apply {

            }
    }
    fun updateDepartureTimeTextView(formattedTime: String) {
        horaSalidaTextView.text = formattedTime
    }

    fun updateReturnTimeTextView(formattedTime: String) {
        horaRegresoTextView.text = formattedTime
    }

    fun updateDepartureDateTextView(formattedDate: String) {
        fechaSalidaTextView.text = formattedDate
    }

    fun updateReturnDateTextView(formattedDate: String) {
        fechaRegresoTextView.text = formattedDate
    }

    fun getDepartureDate(): String {
        return fechaSalidaTextView.text.toString()
    }
}