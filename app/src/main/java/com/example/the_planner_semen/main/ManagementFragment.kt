package com.example.the_planner_semen.main

import android.content.Context
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.the_planner_semen.R
import com.example.the_planner_semen.databinding.ActivityMainBinding
import com.example.the_planner_semen.ui.Coming.Coming_Class_Fragment
import com.example.the_planner_semen.ui.Orders.Orders_Class_Fragment
import com.example.the_planner_semen.ui.Statistics.Statistics_Class_Fragment
import com.google.android.material.navigation.NavigationView

class ManagementFragment(val binding: ActivityMainBinding,val context: Context,val fragmentManager: FragmentManager) {
    private lateinit var menu_1: LinearLayout
    private lateinit var menu_2: LinearLayout
    private lateinit var menu_3: LinearLayout
    private lateinit var menu_4: LinearLayout
    private lateinit var menu_5: LinearLayout

    private lateinit var navView: NavigationView
    private lateinit var drawerLayout: DrawerLayout

    fun initializingMenu() {
        navView = binding.navView
        drawerLayout = binding.drawerLayout

        val openDrawerImageView: ImageView = binding.appBarMain.idOpenDrawer

        openDrawerImageView.setOnClickListener { view ->
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        //пункты меню
        menu_1 = navView.getHeaderView(0).findViewById(R.id.id_orders_menu)
        menu_2 = navView.getHeaderView(0).findViewById(R.id.id_coming_menu)
        menu_3 = navView.getHeaderView(0).findViewById(R.id.id_statistics_menu)

        completionMenu(listOf(menu_1,menu_2,menu_3),
            listOf("Заказы","Ресурсы","Статистика"),
            listOf(Orders_Class_Fragment(),Coming_Class_Fragment(),Statistics_Class_Fragment()))
        menu_1.performClick()
        //обработчик 1 пункта
        /*menu_1.setOnClickListener {
            loadFragment(Orders_Class_Fragment())
            drawerLayout.closeDrawer(GravityCompat.START) // Закрыть навигационное меню
        }
        //обработчик 2 пункта
        menu_2.setOnClickListener {
            loadFragment(Coming_Class_Fragment())
            drawerLayout.closeDrawer(GravityCompat.START) // Закрыть навигационное меню
        }
        //обработчик 2 пункта
        menu_3.setOnClickListener {
            loadFragment(Statistics_Class_Fragment())
            drawerLayout.closeDrawer(GravityCompat.START) // Закрыть навигационное меню
        }*/
        //обработчик 2 пункта
        /*menu_4.setOnClickListener {
            loadFragment(Coming_Class_Fragment())
            drawerLayout.closeDrawer(GravityCompat.START) // Закрыть навигационное меню
        }
        //обработчик 2 пункта
        menu_5.setOnClickListener {
            loadFragment(Coming_Class_Fragment())
            drawerLayout.closeDrawer(GravityCompat.START) // Закрыть навигационное меню
        }*/
    }

    private fun loadFragment(fragment: Fragment) {
        // Получаем текущий фрагмент
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        // Проверяем, существует ли фрагмент
        val existingFragment = fragmentManager.findFragmentByTag(fragment.javaClass.simpleName)

        if (existingFragment != null) {
            fragmentTransaction.show(existingFragment)
        } else {
            fragmentTransaction.add(R.id.fragment_container, fragment, fragment.javaClass.simpleName)
        }
        // Скрываем все остальные фрагменты
        var i: Int = 0
        for (f in fragmentManager.fragments) {
            if (f != existingFragment && f.isVisible) {
                i++
                fragmentTransaction.hide(f)
            }
        }
        fragmentTransaction.commit()
    }

    private fun completionMenu(masMenu: List<LinearLayout>, masText: List<String>, masFragment: List<Fragment>) {
        // Проверка на совпадение размеров списков
        if (masMenu.size != masText.size || masMenu.size != masFragment.size) {
            throw IllegalArgumentException("Списки должны иметь одинаковый размер")
        }
        masMenu.forEachIndexed { index, menu ->
            val imageView: ImageView = menu.findViewById(R.id.menu_image)
            val textView: TextView = menu.findViewById(R.id.menu_text)
            // Установка текста для TextView
            textView.text = masText[index]
            // Установка обработчика клика
            menu.setOnClickListener {
                changeStyleMenu(index,masMenu)
                loadFragment(masFragment[index])
                drawerLayout.closeDrawer(GravityCompat.START) // Закрыть навигационное меню
            }
        }
    }

    private fun changeStyleMenu(position:Int,masMenu: List<LinearLayout>) {
        masMenu.forEachIndexed { index, menu ->
            if (index == position) {
                menu.setBackgroundResource(R.drawable.style_main_menu_1)
            }
            else {menu.setBackgroundResource(R.drawable.style_main_menu_0)}
        }
    }

    //вывод сообщения makeText
    private fun vivod(s: String) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
    }
}