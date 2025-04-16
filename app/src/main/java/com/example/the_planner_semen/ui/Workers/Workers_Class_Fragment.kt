package com.example.the_planner_semen.ui.Statistics

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.the_planner_semen.data_bd.SharedViewModel
import com.example.the_planner_semen.data_bd.Worker
import com.example.the_planner_semen.databinding.FragmentWorkersBinding
import com.example.the_planner_semen.my_menu.Data_menu_item
import com.example.the_planner_semen.my_menu.FragmentMenu
import com.example.the_planner_semen.my_menu.InterfaceMenu
import com.example.the_planner_semen.ui.adapters.UniversalAdapter
import com.example.the_planner_semen.ui.dialogs.UniversalDialogPerson

class Workers_Class_Fragment : Fragment() {
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UniversalAdapter<Worker>

    private var _binding: FragmentWorkersBinding? = null

    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.workers.observe(viewLifecycleOwner, Observer { newData ->
            updateRecycler()
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkersBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //инициализация списка
        recyclerView = binding.idRecuclerWorker
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Инициализируйте адаптер с обработчиком нажатий
        adapter = UniversalAdapter(sharedViewModel.workers.value?:listOf(), sharedViewModel,requireContext()) // Передаем текущий фрагмент как слушатель
        recyclerView.adapter = adapter

        return root
    }

    //инициализация меню
    var menuFragment: FragmentMenu = init_my_mune()
    private fun init_my_mune(): FragmentMenu {
        val dataMenuItem = Data_menu_item("Добавить исполнителя")
        val items = listOf(dataMenuItem)
        val fragmentMenu = FragmentMenu()
        fragmentMenu.getDate(items,object : InterfaceMenu.OnItemClickListener {
            override fun onItemClick(position: Int) {
                when (position) {
                    0 -> {
                        val workerDialog = UniversalDialogPerson(requireContext(),sharedViewModel,0,Worker(name = ""))
                        workerDialog.showDialog()
                    }
                    1 -> {
                        vivod("1")
                    }
                    2 -> {
                        vivod("2")
                    }
                    else -> {
                        vivod("else")
                    }
                }
            }
        })
        return fragmentMenu
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun vivod(s: String) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateRecycler() {
        // Получаем текущий список заказов или пустой список, если orders.value равно null
        val workersList = sharedViewModel.workers.value ?: listOf()

        // Обновляем данные в адаптере
        adapter.items = workersList
        adapter.notifyDataSetChanged() // Уведомляем адаптер об изменениях
    }
}