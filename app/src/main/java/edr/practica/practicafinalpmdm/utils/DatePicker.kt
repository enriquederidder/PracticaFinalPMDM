package edr.practica.practicafinalpmdm.utils

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
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
        reservaFragment?.updateDateTextView(formattedDate)
    }

}
