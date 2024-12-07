package com.artinc.avia.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.artinc.avia.R
import com.artinc.avia.adapters.FeedAdapter
import com.artinc.avia.utils.FeedMarginItemDecoration
import com.artinc.network.viewModels.FeedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var modalSearch: SearchFragment? = null
    private val viewModel: FeedViewModel by viewModel()
    private lateinit var adapter: FeedAdapter
    private lateinit var firstEdit: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstEdit = view.findViewById(R.id.home_first_input)

        // Загружаем сохраненное значение
        loadSavedInput()

        // Сохраняем текст при его изменении
        firstEdit.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) saveInput(firstEdit.text.toString())
        }

        setupRecyclerView(view)
        observeViewModel()

        view.findViewById<EditText>(R.id.home_second_input).setOnClickListener {
            openModal(firstEdit.text.toString())
        }

        viewModel.getFeed()
    }

    private fun openModal(text: String) {
        val bundle = Bundle().apply {
            putString("depart", text)
        }
        modalSearch = SearchFragment().apply {
            arguments = bundle
        }
        modalSearch?.show(parentFragmentManager, modalSearch?.tag)
    }

    private fun setupRecyclerView(view: View) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewFeed)
        recyclerView.addItemDecoration(
            FeedMarginItemDecoration(
                startMargin = (16 * resources.displayMetrics.density).toInt(),
                endMargin = (16 * resources.displayMetrics.density).toInt(),
                defaultMargin = (67 * resources.displayMetrics.density).toInt(),
            )
        )
        adapter = FeedAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.feedLiveData.observe(viewLifecycleOwner) { items ->
            adapter.updateItems(items)
        }
    }

    private fun saveInput(input: String) {
        val sharedPreferences = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit()
            .putString("home_first_input", input)
            .apply()
    }

    private fun loadSavedInput() {
        val sharedPreferences = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val savedInput = sharedPreferences.getString("home_first_input", "")
        firstEdit.setText(savedInput)
    }
}