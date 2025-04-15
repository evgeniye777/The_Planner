package com.example.the_planner_semen.my_menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.the_planner_semen.R

class FragmentMenu : Fragment(), My_Menu_Adapter.HideFragmentListener{
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: My_Menu_Adapter
    private lateinit var items: List<Data_menu_item>
    private lateinit var listener: InterfaceMenu.OnItemClickListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.my_menu_recycler, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //скрываем меню при клике на оставшуюся часть экрана
        val linearAllmenu: LinearLayout = view.findViewById(R.id.my_linear_layout)
        linearAllmenu.setOnClickListener{parentFragmentManager.beginTransaction().hide(this).commit() }

        //инициализация списка
        recyclerView = view.findViewById(R.id.id_my_menu)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Инициализируйте адаптер с обработчиком нажатий
        adapter = My_Menu_Adapter(items, listener,this) // Передаем текущий фрагмент как слушатель
        recyclerView.adapter = adapter
    }

    fun getDate(items0: List<Data_menu_item>,listener0: InterfaceMenu.OnItemClickListener) {
        items = items0
        listener = listener0
    }

    //реализуем интерфейс для скрытия меню при выборе элемента
    override fun onHideFragment() {
        // Скрываем фрагмент
        parentFragmentManager.beginTransaction().hide(this).commit()
    }
}