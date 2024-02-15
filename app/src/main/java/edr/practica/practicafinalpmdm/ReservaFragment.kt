package edr.practica.practicafinalpmdm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.commit
import edr.practica.practicafinalpmdm.utils.*

class ReservaFragment : Fragment() {
    private lateinit var v: View
    private lateinit var fechaSalidaTextView: TextView
    private lateinit var fechaRegresoTextView: TextView
    private lateinit var horaSalidaTextView: TextView
    private lateinit var horaRegresoTextView: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_reserva, container, false)

        v.findViewById<Button>(R.id.buttonFechaSalida).setOnClickListener {
            val fm = childFragmentManager
            fm.commit {
                val newFragment = DatePicker()
                newFragment.show(fm, "datePicker")
            }
            true
        }
        v.findViewById<Button>(R.id.buttonFechaRegreso).setOnClickListener {
            val fm = childFragmentManager
            fm.commit {
                val newFragment = DatePicker()
                newFragment.show(fm, "datePicker")
            }
            true
        }
        v.findViewById<Button>(R.id.buttonHoraSalida).setOnClickListener {
            val fm = childFragmentManager
            fm.commit {
                val newFragment = TimePicker()
                newFragment.show(fm, "timePicker")
            }
            true
        }
        v.findViewById<Button>(R.id.buttonHoraRegreso).setOnClickListener {
            val fm = childFragmentManager
            fm.commit {
                val newFragment = TimePicker()
                newFragment.show(fm, "timePicker")
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
    }

    fun updateTimeTextView(formattedTime: String) {
        horaSalidaTextView.text = formattedTime
        horaRegresoTextView.text = formattedTime
    }

    fun updateDateTextView(formattedDate: String) {
        fechaSalidaTextView.text = formattedDate
        fechaRegresoTextView.text = formattedDate
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ReservaFragment().apply {

            }
    }
}