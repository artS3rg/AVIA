package com.artinc.avia.fragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.artinc.avia.R
import com.artinc.avia.adapters.AirAdapter
import com.artinc.network.viewModels.AirViewModel
import com.google.android.material.button.MaterialButton
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class SearchNextFragment : DialogFragment() {
    private lateinit var nextFirstEditText: EditText
    private lateinit var nextSecondEditText: EditText
    private val viewModel: AirViewModel by viewModel()
    private lateinit var adapter: AirAdapter
    private val bundle = Bundle()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_next, container, false)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Извлечение данных из arguments
        val departText = arguments?.getString("depart")
        val arrivedText = arguments?.getString("arrived")

        nextFirstEditText = view.findViewById(R.id.next_first_input)
        nextSecondEditText = view.findViewById(R.id.next_second_input)

        nextFirstEditText.setText(departText)
        nextSecondEditText.setText(arrivedText)

        view.findViewById<MaterialButton>(R.id.show_tickets_btn).setOnClickListener {
            changeScreen(nextFirstEditText.text.toString(), nextSecondEditText.text.toString())
            dismiss()
        }

        view.findViewById<ImageButton>(R.id.next_clear_btn).setOnClickListener {
            clearText(nextSecondEditText)
        }

        view.findViewById<ImageButton>(R.id.next_change_btn).setOnClickListener {
            changeText(nextFirstEditText, nextSecondEditText)
        }
        setupBackButton(view)

        val btnSetBackDate = view.findViewById<MaterialButton>(R.id.date_back_btn)
        val btnSetDepartDate = view.findViewById<MaterialButton>(R.id.date_depart_btn)

        btnSetDepartDate.text = setColor(getCurrentFormattedDate())

        btnSetDepartDate.setOnClickListener {
            showDatePicker { selectedDate ->
                btnSetDepartDate.text = setColor(selectedDate)
            }
        }

        btnSetBackDate.setOnClickListener {
            showDatePicker { selectedDate ->
                btnSetBackDate.text = selectedDate
            }
        }

        setupRecyclerView(view)
        observeViewModel()
        viewModel.getAir()
    }

    private fun changeScreen(depart: String, arrived: String) {
        bundle.apply {
            putString("depart", depart)
            putString("arrived", arrived)
        }
        val newFragment = TicketsFragment().apply {
            arguments = bundle
        }
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, newFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setupBackButton(view: View) {
        val backButton: ImageButton = view.findViewById(R.id.back_btn)
        backButton.setOnClickListener {
            parentFragmentManager.popBackStack() // Возврат к предыдущему фрагменту
        }
    }

    private fun clearText(editText: EditText) {
        editText.setText("")
    }

    private fun changeText(firstEdit: EditText, secondEdit: EditText) {
        val text = firstEdit.text.toString()
        firstEdit.setText(secondEdit.text.toString())
        secondEdit.setText(text)
    }

    private fun setupRecyclerView(view: View) {
        val recyclerView: RecyclerView = view.findViewById(R.id.air_recyclerView)
        adapter = AirAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.airLiveData.observe(viewLifecycleOwner) { items ->
            adapter.updateItems(items)
        }
    }

    private fun showDatePicker(onDateSelected: (String) -> Unit) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = LocalDate.of(selectedYear, selectedMonth + 1, selectedDay)
                val formattedDate = formatDate(selectedDate)
                onDateSelected(formattedDate)
            },
            year, month, day
        )

        // Обработка нажатия на "Отмена"
        datePickerDialog.setOnCancelListener {
            onDateSelected("Обратно")
        }

        datePickerDialog.show()
    }

    private fun formatDate(date: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern("d MMM, E", Locale("ru"))
        val bundleFormatter = DateTimeFormatter.ofPattern("d MMMM", Locale("ru"))
        bundle.apply {
            putString("data", date.format(bundleFormatter))
        }
        return date.format(formatter).replace(".", "")
    }

    private fun getCurrentFormattedDate(): String {
        return formatDate(LocalDate.now())
    }

    private fun setColor(text: String): SpannableString {
        val spannable = SpannableString(text)

        // Применение цветов
        val colorWhite = ContextCompat.getColor(requireContext(), R.color.white)
        val colorGrey = ContextCompat.getColor(requireContext(), R.color.grey6)

        // Применение цвета к разным частям текста
        spannable.setSpan(
            ForegroundColorSpan(colorWhite), 0, text.length-4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannable.setSpan(
            ForegroundColorSpan(colorGrey), text.length-4, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        return spannable
    }
}
