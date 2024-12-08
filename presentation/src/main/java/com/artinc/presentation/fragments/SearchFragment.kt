package com.artinc.presentation.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.artinc.presentation.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SearchFragment : BottomSheetDialogFragment() {
    private lateinit var secondEditText: EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState).apply {
            val dialog = BottomSheetDialog(requireContext(), theme)
            dialog.setOnShowListener {
                val bottomSheetDialog = it as BottomSheetDialog
                val parentLayout = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
                parentLayout?.let { it ->
                    val behaviour = BottomSheetBehavior.from(it)
                    setupFullHeight(it)
                    behaviour.state = BottomSheetBehavior.STATE_EXPANDED
                }
            }
            return dialog
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            navigationBarColor = ContextCompat.getColor(requireContext(), R.color.grey2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Извлечение данных из arguments
        val departText = arguments?.getString("depart")
        view.findViewById<EditText>(R.id.search_first_input).setText(departText)

        clearText(view)
        setText(view)

        val firstEditText = view.findViewById<EditText>(R.id.search_first_input)
        secondEditText = view.findViewById(R.id.search_second_input)
        secondEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (!secondEditText.text.isNullOrEmpty()) {
                    changeScreen(firstEditText.text.toString(), secondEditText.text.toString())
                    dismiss()
                }
                true
            } else {
                false
            }
        }
    }

    private fun changeScreen(depart: String, arrived: String) {
        val bundle = Bundle().apply {
            putString("depart", depart)
            putString("arrived", arrived)
        }
        val newFragment = SearchNextFragment().apply {
            arguments = bundle
        }
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, newFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }

    private fun clearText(view: View) {
        view.findViewById<ImageButton>(R.id.clear_btn).setOnClickListener {
            view.findViewById<EditText>(R.id.search_second_input).setText("")
        }
    }

    private fun setText(view: View) {
        view.findViewById<LinearLayout>(R.id.search_first_layout).setOnClickListener {
            view.findViewById<EditText>(R.id.search_second_input).setText("Стамбул")
        }
        view.findViewById<LinearLayout>(R.id.search_second_layout).setOnClickListener {
            view.findViewById<EditText>(R.id.search_second_input).setText("Сочи")
        }
        view.findViewById<LinearLayout>(R.id.search_third_layout).setOnClickListener {
            view.findViewById<EditText>(R.id.search_second_input).setText("Пхукет")
        }
        view.findViewById<LinearLayout>(R.id.hard_nav).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, EmptyFragment())
                .addToBackStack(null)
                .commit()
            dismiss()
        }
        view.findViewById<LinearLayout>(R.id.random).setOnClickListener {
            view.findViewById<EditText>(R.id.search_second_input).setText("Куда угодно")
        }
        view.findViewById<LinearLayout>(R.id.holidays).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, EmptyFragment())
                .addToBackStack(null)
                .commit()
            dismiss()
        }
        view.findViewById<LinearLayout>(R.id.fire_tickets).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, EmptyFragment())
                .addToBackStack(null)
                .commit()
            dismiss()
        }
    }
}