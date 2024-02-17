package edr.practica.practicafinalpmdm.utils

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.Calendar
import android.text.format.DateFormat;
import edr.practica.practicafinalpmdm.ReservaFragment

class TimePicker : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current time as the default values for the picker.
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        // Create a new instance of TimePickerDialog and return it.
        return TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity))
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val formattedTime = "$hourOfDay:$minute"
        val reservaFragment = parentFragment as? ReservaFragment
        when (tag) {
            "timePickerDeparture" -> reservaFragment?.updateDepartureTimeTextView(formattedTime)
            "timePickerReturn" -> reservaFragment?.updateReturnTimeTextView(formattedTime)
        }
    }
}
