package com.example.the_planner_semen.ui.Orders

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.the_planner_semen.data_bd.Order
import com.example.the_planner_semen.data_bd.SharedViewModel
import com.example.the_planner_semen.databinding.FragmentOrdersBinding
import com.example.the_planner_semen.my_menu.Data_menu_item
import com.example.the_planner_semen.my_menu.FragmentMenu
import com.example.the_planner_semen.my_menu.InterfaceMenu


class Orders_Class_Fragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: OrdersAdapter

    private var _binding: FragmentOrdersBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.orders.observe(viewLifecycleOwner, Observer { newData ->
            updateRecycler()
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(Orders_Class_ViewModel::class.java)

        _binding = FragmentOrdersBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textOrders
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        //инициализация списка
        recyclerView = binding.idRecuclerOrder
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Инициализируйте адаптер с обработчиком нажатий
        adapter = OrdersAdapter(sharedViewModel.orders.value?:listOf(), requireContext()) // Передаем текущий фрагмент как слушатель
        recyclerView.adapter = adapter

        return root
    }

    //инициализация меню
    var menuFragment: FragmentMenu  = init_my_mune()
    private fun init_my_mune():FragmentMenu {
        val dataMenuItem: Data_menu_item = Data_menu_item("Добавить заказ")
        val items = listOf(dataMenuItem) // Пример списка с 100 элементами
        val fragmentMenu = FragmentMenu()
        fragmentMenu.getDate(items,object : InterfaceMenu.OnItemClickListener {
            override fun onItemClick(position: Int) {
                when (position) {
                    0 -> {
                        val order = Order( date = "2023-10-02",
                            nameText = "Заказ без дополнительных параметров")
                        val i: Int = sharedViewModel.addItem(order)
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
        val ordersList = sharedViewModel.orders.value ?: listOf()

        // Обновляем данные в адаптере
        adapter.items = ordersList
        adapter.notifyDataSetChanged() // Уведомляем адаптер об изменениях
    }
}