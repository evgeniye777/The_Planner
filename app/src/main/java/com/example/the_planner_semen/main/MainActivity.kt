package com.example.the_planner_semen.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.ui.AppBarConfiguration
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.the_planner_semen.data_bd.DatabaseManager
import com.example.the_planner_semen.data_bd.SharedViewModel
import com.example.the_planner_semen.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var managerFragment: ManagementFragment

    // доступ к модулю данных
    private val sharedViewModel: SharedViewModel by viewModels()
    private lateinit var databaseManager: DatabaseManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Установка флагов для прозрачного статус-бара
        supportActionBar?.hide()

        //чтение данных из БД и передача в модуль данных
        databaseManager = DatabaseManager(this,sharedViewModel)

        //Управление меню в шторке
        managerFragment = ManagementFragment(binding, this, supportFragmentManager)
        managerFragment.initializingMenu()
    }


}

//https\://services.gradle.org/distributions/gradle-8.12.1-bin.zip
//file\:///D:/MyFiles/Trainings/0_Gradle/gradle-8.12.1-bin.zip