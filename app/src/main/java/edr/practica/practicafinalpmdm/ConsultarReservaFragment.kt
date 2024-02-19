package edr.practica.practicafinalpmdm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edr.practica.practicafinalpmdm.models.DatosViaje


class ConsultarReservaFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var reservaAdapter: ReservaAdapter
    private val datosViajeList = mutableListOf<DatosViaje>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_consultar_reserva, container, false)
        recyclerView = view.findViewById(R.id.ConsultarRecycleViewer)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RecyclerView and adapter
        reservaAdapter = ReservaAdapter(datosViajeList)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = reservaAdapter
        }

        // Add sample data
        datosViajeList.add(
            DatosViaje(
                "Salida 1",
                "2024-02-20",
                "08:00 AM",
                "Destino 1",
                "2024-02-25",
                "06:00 PM",
                2
            )
        )

        // Notify adapter about the data change
        reservaAdapter.notifyDataSetChanged()
    }

    fun addDatosViaje(datosViaje: DatosViaje) {
        datosViajeList.add(datosViaje)
        reservaAdapter.notifyDataSetChanged()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ConsultarReservaFragment().apply {

            }
    }
}