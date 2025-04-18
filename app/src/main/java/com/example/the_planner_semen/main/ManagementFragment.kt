package com.example.the_planner_semen.main

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
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
import com.example.the_planner_semen.my_menu.FragmentMenu
import com.example.the_planner_semen.ui.Coming.Coming_Class_Fragment
import com.example.the_planner_semen.ui.Orders.Orders_Class_Fragment
import com.example.the_planner_semen.ui.Statistics.Clients_Class_Fragment
import com.example.the_planner_semen.ui.Statistics.Settings_Class_Fragment
import com.example.the_planner_semen.ui.Statistics.Statistics_Class_Fragment
import com.example.the_planner_semen.ui.Statistics.ExportImport_Class_Fragment
import com.example.the_planner_semen.ui.Statistics.Types_Class_Fragment
import com.example.the_planner_semen.ui.Statistics.Workers_Class_Fragment
import com.google.android.material.navigation.NavigationView

class ManagementFragment(val binding: ActivityMainBinding,val context: Context,val fragmentManager: FragmentManager) {
    private lateinit var menu_1: LinearLayout
    private lateinit var menu_2: LinearLayout
    private lateinit var menu_3: LinearLayout
    private lateinit var menu_4: LinearLayout
    private lateinit var menu_5: LinearLayout
    private lateinit var menu_6: LinearLayout
    private lateinit var menu_7: LinearLayout
    private lateinit var menu_8: LinearLayout

    private lateinit var navView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var image_my_menu: ImageView
    private var fragmentMenu: FragmentMenu? = null

    fun initializingMenu() {
        navView = binding.navView
        drawerLayout = binding.drawerLayout

        //Активация кнопки открытия и закрытия шторки
        val openDrawerImageView: ImageView = binding.appBarMain.idOpenDrawer

        openDrawerImageView.setOnClickListener { view ->
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        //инициализация кнопки открытия меню
        image_my_menu = binding.appBarMain.idIconMyMenu
        image_my_menu.setOnClickListener{ view ->
            if (fragmentMenu!=null) {loadFragmentMenu(fragmentMenu!!,true)}
        }
        //пункты меню

        //Заказы
        menu_1 = navView.getHeaderView(0).findViewById(R.id.id_orders_menu)
        val orders = Orders_Class_Fragment()
        var dateOrder = DateOneFragment(menu_l = menu_1, menu_name = "Заказы", fragment = orders, img_id = R.drawable.img_main_menu_orders, optionR = true, option_f = orders.menuFragment)

        //Ресурсы
        menu_2 = navView.getHeaderView(0).findViewById(R.id.id_coming_menu)
        val coming = Coming_Class_Fragment()
        var dateComing = DateOneFragment(menu_l = menu_2,menu_name = "Ресурсы",fragment = coming,img_id = R.drawable.img_resources,optionR = true,option_f = coming.menuFragment)

        //Статистика
        menu_3 = navView.getHeaderView(0).findViewById(R.id.id_statistics_menu)
        val statistics = Statistics_Class_Fragment()
        var dateStatistics = DateOneFragment(menu_l = menu_3,menu_name = "Статистика",fragment = statistics,img_id = R.drawable.img_statistics)

        //Клиенты
        menu_4 = navView.getHeaderView(0).findViewById(R.id.id_clients_menu)
        val clients = Clients_Class_Fragment()
        var dateClients = DateOneFragment(menu_l = menu_4,menu_name = "Клиенты",fragment = clients,img_id = R.drawable.img_clients,optionR = true,option_f = clients.menuFragment)

        //Исполнители
        menu_5 = navView.getHeaderView(0).findViewById(R.id.id_workers_menu)
        val workers = Workers_Class_Fragment()
        var dateWorkers = DateOneFragment(menu_l = menu_5,menu_name = "Исполнители",fragment = workers,img_id = R.drawable.img_main_menu_workers,optionR = true,option_f = workers.menuFragment)

        //Настройки
        menu_6 = navView.getHeaderView(0).findViewById(R.id.id_settings_menu)
        val settings = Settings_Class_Fragment()
        var dateSettings = DateOneFragment(menu_l = menu_6,menu_name = "Настройки",fragment = settings,img_id = R.drawable.img_main_menu_settings)

        //Типы данных
        menu_7 = navView.getHeaderView(0).findViewById(R.id.id_types_menu)
        val types = Types_Class_Fragment()
        var dateTypes = DateOneFragment(menu_l = menu_7,menu_name = "Типы данных",fragment = types,img_id = R.drawable.img_type_data)

        //Экспорт Импорт
        menu_8 = navView.getHeaderView(0).findViewById(R.id.id_exportimport_menu)
        val exportimport = ExportImport_Class_Fragment()
        var dateExportImport = DateOneFragment(menu_l = menu_8,menu_name = "Экспорт&Импорт",fragment = exportimport, img_id = R.drawable.img_import_export)

        completionMenu(listOf(dateOrder,dateComing,dateStatistics,dateClients,dateWorkers,dateSettings,dateTypes,dateExportImport))
        menu_1.performClick()
    }

    //Управление фрагментами главного меню
    private fun loadFragment(fragment: Fragment) {
        // Получаем текущий фрагмент
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        // Проверяем, существует ли фрагмент
        var existingFragment = fragmentManager.findFragmentByTag(fragment.javaClass.simpleName)

        if (existingFragment != null) {
            fragmentTransaction.show(existingFragment)
        } else {
            fragmentTransaction.add(R.id.fragment_container, fragment, fragment.javaClass.simpleName)
        }
        ///
        // Скрываем все остальные фрагменты
        var i: Int = 0
        for (f in fragmentManager.fragments) {
            if (f != existingFragment && f.isVisible) {
                i++
                fragmentTransaction.hide(f)
            }
        }
        //удаляем меню для этого фрагмента
        existingFragment = fragmentManager.findFragmentByTag(fragmentMenu?.javaClass?.simpleName)
        if (existingFragment is FragmentMenu) {
            fragmentManager.beginTransaction()
                .remove(existingFragment)
                .commit()
        }
        fragmentTransaction.commit()
    }

    //управление фрагментами меню функций
    @SuppressLint("CommitTransaction")
    private fun loadFragmentMenu(fragment: Fragment, visible:Boolean) {
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        // Проверяем, существует ли фрагмент
        var existingFragment = fragmentManager.findFragmentByTag(fragment.javaClass.simpleName)

        if (existingFragment != null) {
            if (visible) {fragmentTransaction.show(existingFragment)}
            else {fragmentTransaction.hide(existingFragment)}
        } else {
            if (visible) {fragmentTransaction.add(R.id.fragment_container, fragment, fragment.javaClass.simpleName)}
        }
        fragmentTransaction.commit()
    }

    //регистрация пунктов меню
    private fun completionMenu(dataMenu: List<DateOneFragment>) {

        dataMenu.forEachIndexed { index, data ->
            val imageView: ImageView = data.menu_l.findViewById(R.id.menu_image)
            val textView: TextView = data.menu_l.findViewById(R.id.menu_text)
            // Установка текста для TextView
            textView.text = data.menu_name
            if (data.img_id!=-1) {
                try {
                    imageView.setImageResource(data.img_id)
                }catch(_:Exception) {}
            }
            // Установка обработчика клика
            data.menu_l.setOnClickListener {
                changeStyleMenu(index,dataMenu)
                if (data.optionR) {image_my_menu.visibility = View.VISIBLE;

                    fragmentMenu = data.option_f
                }
                else {image_my_menu.visibility = View.GONE;}
                loadFragment(data.fragment)
                drawerLayout.closeDrawer(GravityCompat.START) // Закрыть навигационное меню
            }
        }
    }

    //управление стилями пунктов меню
    private fun changeStyleMenu(position:Int,dataMenu: List<DateOneFragment>) {
        dataMenu.forEachIndexed { index, data ->
            if (index == position) {
                data.menu_l.setBackgroundResource(R.drawable.style_main_menu_1)
            }
            else {data.menu_l.setBackgroundResource(R.drawable.style_main_menu_0)}
        }
    }

    //вывод сообщения makeText
    private fun vivod(s: String) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
    }
}