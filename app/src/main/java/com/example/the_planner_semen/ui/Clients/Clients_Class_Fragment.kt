package com.example.the_planner_semen.ui.Statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.the_planner_semen.databinding.FragmentClientsBinding

class Clients_Class_Fragment : Fragment() {

    private var _binding: FragmentClientsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(Types_Class_ViewModel::class.java)

        _binding = FragmentClientsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textClient
        slideshowViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}