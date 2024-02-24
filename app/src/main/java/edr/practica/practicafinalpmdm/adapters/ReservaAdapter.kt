package edr.practica.practicafinalpmdm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edr.practica.practicafinalpmdm.R
import edr.practica.practicafinalpmdm.models.DatosViaje

class ReservaAdapter(private val datosViajeList: MutableList<DatosViaje>) : RecyclerView.Adapter<ReservaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_reserva, parent, false)
        return ViewHolder(view)
    }

    fun setData(newData: List<DatosViaje>) {
        datosViajeList.clear()
        datosViajeList.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val datosViaje = datosViajeList[position]
        holder.bind(datosViaje)
    }

    override fun getItemCount(): Int {
        return datosViajeList.size
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val direccionSalidaTextView: TextView = itemView.findViewById(R.id.textViewDireccion)
        private val fechaSalidaTextView: TextView = itemView.findViewById(R.id.textViewFechaSalida)
        private val horaSalidaTextView: TextView = itemView.findViewById(R.id.textViewHoraSalida)
        private val direccionDestinoTextView: TextView = itemView.findViewById(R.id.textViewDestino)
        private val fechaRegresoTextView: TextView = itemView.findViewById(R.id.textViewFechaRegreso)
        private val horaRegresoTextView: TextView = itemView.findViewById(R.id.textViewHoraRegreso)
        private val cantidadPasajerosTextView: TextView = itemView.findViewById(R.id.textViewCantidadPasajeros)


        fun bind(datosViaje: DatosViaje) {
            // Bind datosViaje data
            direccionSalidaTextView.text = "Dirección de salida: ${datosViaje.direccion}"
            fechaSalidaTextView.text = "Fecha de salida: ${datosViaje.fechaSalida}"
            horaSalidaTextView.text = "Hora de salida: ${datosViaje.horaSalida}"
            direccionDestinoTextView.text = "Destino: ${datosViaje.destino}"
            fechaRegresoTextView.text = "Fecha de regreso: ${datosViaje.fechaRegreso}"
            horaRegresoTextView.text = "Hora de regreso: ${datosViaje.horaRegreso}"
            cantidadPasajerosTextView.text = "Cantidad de pasajeros: ${datosViaje.numerodePasajeros}"

        }
    }
}
