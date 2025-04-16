package com.example.the_planner_semen.ui.Statistics

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.the_planner_semen.R
import com.example.the_planner_semen.data_bd.Client
import com.example.the_planner_semen.data_bd.DialogItemType
import com.example.the_planner_semen.data_bd.SharedViewModel
import com.example.the_planner_semen.databinding.FragmentTypesBinding
import com.example.the_planner_semen.ui.adapters.UniversalAdapter

class Types_Class_Fragment : Fragment() {
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterR: UniversalAdapter<DialogItemType>
    private var _binding: FragmentTypesBinding? = null

    private var nTypeNow: Int = 0

    private lateinit var spinnerType: Spinner

    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.typeMaterials.observe(viewLifecycleOwner, Observer { newData ->
            updateRecycler(0,true)
        })
        sharedViewModel.typeNames.observe(viewLifecycleOwner, Observer { newData ->
            updateRecycler(1,true)
        })
        sharedViewModel.typeStatusComings.observe(viewLifecycleOwner, Observer { newData ->
            updateRecycler(2,true)
        })
        sharedViewModel.typeStatusOrders.observe(viewLifecycleOwner, Observer { newData ->
            updateRecycler(3,true)
        })
        sharedViewModel.typeStatusPays.observe(viewLifecycleOwner, Observer { newData ->
            updateRecycler(4,true)
        })
        sharedViewModel.typeAccumulations.observe(viewLifecycleOwner, Observer { newData ->
            updateRecycler(5,true)
        })
        sharedViewModel.typeUnits.observe(viewLifecycleOwner, Observer { newData ->
            updateRecycler(6,true)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTypesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //Инициализация спинера Типов
        spinnerType = binding.idSpinnerTypeTypes

        //Адаптер строк типов
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.type_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerType.adapter = adapter
        //Слушатель клика Спинера Типов
        spinnerType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                updateRecycler(position,false)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        //инициализация списка
        recyclerView = binding.idRecuclerTypes
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Инициализируйте адаптер с обработчиком нажатий
        adapterR = UniversalAdapter(sharedViewModel.typeMaterials.value?:listOf(), sharedViewModel,requireContext()) // Передаем текущий фрагмент как слушатель
        recyclerView.adapter = adapterR

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateRecycler(nType:Int,pass: Boolean) {
        // Обновляем данные в адаптере
        if (!pass||nType==nTypeNow) {
        adapterR.items = when (nType) {
            0 -> sharedViewModel.typeMaterials.value ?: listOf()
            1 -> sharedViewModel.typeNames.value ?: listOf()
            2 -> sharedViewModel.typeStatusComings.value ?: listOf()
            3 -> sharedViewModel.typeStatusOrders.value ?: listOf()
            4 -> sharedViewModel.typeStatusPays.value ?: listOf()
            5 -> sharedViewModel.typeAccumulations.value ?: listOf()
            6 -> sharedViewModel.typeUnits.value ?: listOf()
            else -> listOf()
        }
        adapterR.notifyDataSetChanged() // Уведомляем адаптер об изменениях
        }
    }
}