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
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import edr.practica.practicafinalpmdm.models.DatosViaje
import edr.practica.practicafinalpmdm.utils.*

class ReservaFragment : Fragment() {
    private lateinit var v: View
    private lateinit var direccionDestino: String
    private lateinit var direccionSalida: String
    private lateinit var cantidadPasajeros: String
    private lateinit var fechaSalida: String
    private lateinit var fechaRegreso: String
    private lateinit var horaSalida: String
    private lateinit var horaRegreso: String
    private lateinit var fechaSalidaTextView: TextView
    private lateinit var fechaRegresoTextView: TextView
    private lateinit var horaSalidaTextView: TextView
    private lateinit var horaRegresoTextView: TextView
    // Declaraciones para gps
    private lateinit var locationHelper: LocationHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_reserva, container, false)
        // Initialize your views
        val editTextDestino = v.findViewById<EditText>(R.id.editTextDestino)
        val editTextSalida = v.findViewById<EditText>(R.id.editTextSalida)
        val editTextPasajeros = v.findViewById<EditText>(R.id.editTextPasajeros)
        val textViewFechaSalida = v.findViewById<TextView>(R.id.textViewFechaSalida)
        val textViewFechaRegreso = v.findViewById<TextView>(R.id.textViewFechaRegreso)
        val textViewHoraSalida = v.findViewById<TextView>(R.id.textViewHoraSalida)
        val textViewHoraRegreso = v.findViewById<TextView>(R.id.textViewHoraRegreso)
        // Set up click listeners
        v.findViewById<FloatingActionButton>(R.id.floatingActionEnviar).setOnClickListener {
            // Get text from EditText fields
            direccionDestino = editTextDestino.text.toString()
            direccionSalida = editTextSalida.text.toString()
            cantidadPasajeros = editTextPasajeros.text.toString()
            fechaSalida = textViewFechaSalida.text.toString()
            fechaRegreso = textViewFechaRegreso.text.toString()
            horaSalida = textViewHoraSalida.text.toString()
            horaRegreso = textViewHoraRegreso.text.toString()

            // Create a new DatosViaje object
            val newDatosViaje = DatosViaje(
                direccionSalida,
                fechaSalida,
                horaSalida,
                direccionDestino,
                fechaRegreso,
                horaRegreso,
                cantidadPasajeros.toIntOrNull() ?: 0
            )
            // Call interface method to pass the data to the activity
            (activity as? ReservaListener)?.onDatosViajeAdded(newDatosViaje)
            val fm: FragmentManager = parentFragmentManager
            fm.commit {
                replace(R.id.fragmentContainerView, RecogidaDatosFragment.newInstance())
                addToBackStack("replacement")
            }
            true
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
    interface ReservaListener {
        fun onDatosViajeAdded(datosViaje: DatosViaje)
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            ReservaFragment().apply {

            }
    }
}