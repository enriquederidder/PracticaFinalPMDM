package edr.practica.practicafinalpmdm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import edr.practica.practicafinalpmdm.R
import edr.practica.practicafinalpmdm.models.DatosCliente
import edr.practica.practicafinalpmdm.models.RecogidaDatosViewModel
import androidx.activity.viewModels

class ReservaClienteAdapter(
    private val datosViajeClienteList: MutableList<DatosCliente>,
    private val recogidaDatosViewModel: RecogidaDatosViewModel
) : RecyclerView.Adapter<ReservaClienteAdapter.ViewHolder>() {
    var click: ((Int, DatosCliente) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cliente_reserva, parent, false)
        return ViewHolder(view)
    }

    fun setData(newData: List<DatosCliente>) {
        datosViajeClienteList.clear()
        datosViajeClienteList.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val datosCliente = datosViajeClienteList[position]
        holder.idView.text = "ID: ${position}"
        holder.nombreClienteTextView.text = "Nombre: ${datosCliente.nombre}"
        holder.emailTextView.text = "Email: ${datosCliente.email}"
        holder.telefonoTextView.text = "Teléfono: ${datosCliente.numeroTelefone}"

        holder.removeButton.setOnClickListener {
            // Call the removeCliente function from the ViewModel to remove the selected item
            recogidaDatosViewModel.removeCliente(datosCliente)
        }
    }

    override fun getItemCount(): Int {
        return datosViajeClienteList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idView: TextView = itemView.findViewById(R.id.item_number)
        var removeButton: Button = itemView.findViewById(R.id.buttonRemoveSelected)

        val nombreClienteTextView: TextView = itemView.findViewById(R.id.textViewNombre)
        val emailTextView: TextView = itemView.findViewById(R.id.textViewEmail)
        val telefonoTextView: TextView = itemView.findViewById(R.id.textViewNumero)
    }
}
