package edr.practica.practicafinalpmdm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edr.practica.practicafinalpmdm.R
import edr.practica.practicafinalpmdm.models.DatosCliente
import edr.practica.practicafinalpmdm.models.DatosViaje
import edr.practica.practicafinalpmdm.models.RecogidaDatosViewModel

class ReservaAdapter(
    private val datosViajeList: MutableList<DatosViaje>,
    private val recogidaDatosViewModel: RecogidaDatosViewModel


) : RecyclerView.Adapter<ReservaAdapter.ViewHolder>() {
    var click: ((Int, DatosCliente) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_reserva, parent, false)
        return ViewHolder(view)
    }

    fun setData(newData: List<DatosViaje>) {
        datosViajeList.clear()
        datosViajeList.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val datosViaje = datosViajeList[position]
        holder.idViewViaje.text = position.toString()
        holder.direccionSalidaTextView.text = datosViaje.direccion
        holder.fechaSalidaTextView.text = datosViaje.fechaSalida
        holder.horaSalidaTextView.text = datosViaje.horaSalida
        holder.direccionDestinoTextView.text = datosViaje.destino
        holder.fechaRegresoTextView.text = datosViaje.fechaRegreso
        holder.horaRegresoTextView.text = datosViaje.horaRegreso
        holder.cantidadPasajerosTextView.text = datosViaje.numerodePasajeros.toString()

    }

    override fun getItemCount(): Int {
        return datosViajeList.size
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idViewViaje: TextView = itemView.findViewById(R.id.item_numer_viaje)

        val direccionSalidaTextView: TextView = itemView.findViewById(R.id.textViewDireccion)
        val fechaSalidaTextView: TextView = itemView.findViewById(R.id.textViewFechaSalida)
        val horaSalidaTextView: TextView = itemView.findViewById(R.id.textViewHoraSalida)
        val direccionDestinoTextView: TextView = itemView.findViewById(R.id.textViewDestino)
        val fechaRegresoTextView: TextView = itemView.findViewById(R.id.textViewFechaRegreso)
        val horaRegresoTextView: TextView = itemView.findViewById(R.id.textViewHoraRegreso)
        val cantidadPasajerosTextView: TextView = itemView.findViewById(R.id.textViewCantidadPasajeros)

    }
}
