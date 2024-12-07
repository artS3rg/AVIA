package com.artinc.avia.fragments

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.artinc.avia.R
import com.artinc.avia.adapters.TicketsAdapter
import com.artinc.avia.utils.TicketsMarginItemDecoration
import com.artinc.network.viewModels.TicketsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TicketsFragment : DialogFragment(R.layout.fragment_tickets) {
    private val viewModel: TicketsViewModel by viewModel()
    private lateinit var adapter: TicketsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Извлечение данных из arguments
        val departText = arguments?.getString("depart")
        val arrivedText = arguments?.getString("arrived")
        val dataText = arguments?.getString("data")

        view.findViewById<TextView>(R.id.tickets_cities).text = "${departText}-${arrivedText}"
        view.findViewById<TextView>(R.id.tickets_info).text = "${dataText}, 1 пассажир"

        setupRecyclerView(view)
        setupBackButton(view)
        observeViewModel()

        viewModel.getTickets()
    }

    private fun setupRecyclerView(view: View) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.addItemDecoration(
            TicketsMarginItemDecoration(
                topMargin = (112 * resources.displayMetrics.density).toInt(),
                bottomMargin = (78 * resources.displayMetrics.density).toInt(),
                defaultMargin = (8 * resources.displayMetrics.density).toInt(),
                additionalTopMargin = (16 * resources.displayMetrics.density).toInt()
            )
        )
        adapter = TicketsAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.ticketsLiveData.observe(viewLifecycleOwner) { items ->
            adapter.updateItems(items)
        }
    }

    private fun setupBackButton(view: View) {
        val backButton: ImageButton = view.findViewById(R.id.tickets_back)
        backButton.setOnClickListener {
            parentFragmentManager.popBackStack() // Возврат к предыдущему фрагменту
        }
    }
}