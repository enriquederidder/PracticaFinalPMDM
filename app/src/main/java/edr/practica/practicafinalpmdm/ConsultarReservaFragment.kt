package edr.practica.practicafinalpmdm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edr.practica.practicafinalpmdm.models.RecogidaDatosViewModel
import edr.practica.practicafinalpmdm.models.ReservaViewModel

class ConsultarReservaFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var reservaAdapter: ReservaAdapter
    private val viajeViewModel: ReservaViewModel by activityViewModels()
    private val recogidaDatosViewModel: RecogidaDatosViewModel by activityViewModels()

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
        reservaAdapter = ReservaAdapter(
            mutableListOf(),
            recogidaDatosViewModel
        ) // Pass the RecogidaDatosViewModel instance
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = reservaAdapter
        }

        // Observe changes in ViewModel's data and update UI accordingly
        viajeViewModel.viajes.observe(viewLifecycleOwner, Observer { viajes ->
            viajes?.let {
                reservaAdapter.apply {
                    setData(it)
                    notifyDataSetChanged()
                }
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ConsultarReservaFragment().apply {

            }
    }
}
