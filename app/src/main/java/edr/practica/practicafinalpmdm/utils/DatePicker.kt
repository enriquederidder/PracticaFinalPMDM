package edr.practica.practicafinalpmdm.utils

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import edr.practica.practicafinalpmdm.ReservaFragment
import java.util.Calendar

class DatePicker : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current date as the default date in the picker.
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of DatePickerDialog and return it.
        return DatePickerDialog(requireContext(), this, year, month, day)

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        val formattedDate = "$year-${month + 1}-$day"
        val reservaFragment = parentFragment as? ReservaFragment

        if (tag == "datePickerReturn") {
            val departureDate = reservaFragment?.getDepartureDate()
            if (departureDate != null && formattedDate <= departureDate) {

                Toast.makeText(
                    requireContext(),
                    "Fecha de regreso no puede ser antes la de salida",
                    Toast.LENGTH_SHORT
                ).show()
                return
            }
        }
        when (tag) {
            "datePickerDeparture" -> reservaFragment?.updateDepartureDateTextView(formattedDate)
            "datePickerReturn" -> reservaFragment?.updateReturnDateTextView(formattedDate)
        }
    }

}
