package com.example.the_planner_semen.ui.Orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.the_planner_semen.databinding.FragmentOrdersBinding
import com.example.the_planner_semen.my_menu.Data_menu_item
import com.example.the_planner_semen.my_menu.FragmentMenu
import com.example.the_planner_semen.my_menu.InterfaceMenu

class Orders_Class_Fragment : Fragment() {

    private var _binding: FragmentOrdersBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        return root
    }

    //инициализация меню
    var menuFragment: FragmentMenu  = init_my_mune()
    private fun init_my_mune():FragmentMenu {
        val items = List(5) { Data_menu_item("Item #$it") } // Пример списка с 100 элементами
        val fragmentMenu = FragmentMenu()
        fragmentMenu.getDate(items,object : InterfaceMenu.OnItemClickListener {
            override fun onItemClick(position: Int) {
                when (position) {
                    0 -> {
                        vivod("0")
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
}