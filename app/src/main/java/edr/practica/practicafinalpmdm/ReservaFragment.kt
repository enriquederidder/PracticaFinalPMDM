package edr.practica.practicafinalpmdm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.commit
import edr.practica.practicafinalpmdm.utils.*
import android.widget.EditText
import android.app.AlertDialog
import android.content.DialogInterface
import android.location.Address
import android.text.TextUtils
import android.view.View


class ReservaFragment : Fragment() {
    private lateinit var v: View
    private lateinit var fechaSalidaTextView: TextView
    private lateinit var fechaRegresoTextView: TextView
    private lateinit var horaSalidaTextView: TextView
    private lateinit var horaRegresoTextView: TextView
    // Declaraciones para gps
    private lateinit var locationHelper: LocationHelper
    private lateinit var location : String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_reserva, container, false)

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
        // TODO: esto no funciona, sale el dialog igualmente :(
        val editTextSalida = v.findViewById<EditText>(R.id.editTextSalida)
        if (editTextSalida.text.toString().isEmpty()) {
            editTextSalida.setOnClickListener {
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
    fun getDepartureDate(): String? {
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
    companion object {
        @JvmStatic
        fun newInstance() =
            ReservaFragment().apply {

            }
    }
}