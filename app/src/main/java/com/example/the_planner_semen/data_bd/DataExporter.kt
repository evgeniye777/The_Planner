package com.example.the_planner_semen.data_bd

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.documentfile.provider.DocumentFile
import com.example.the_planner_semen.ui.Statistics.ExportImport_Class_Fragment
import com.google.gson.Gson
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DataExporter(private val context: Context, private val sharedViewModel: SharedViewModel) {

    private val gson = Gson()

    // Метод для открытия проводника и выбора директории
    fun openDirectoryPicker(fragment: ExportImport_Class_Fragment) {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT_TREE)
        fragment.filePickerLauncherExport.launch(intent)
    }
    // Метод для преобразования данных в JSON и сохранения в файл
    fun saveJsonToFileInSelectedDirectory(uri: Uri) {
        // Генерируем имя файла
        val fileName = generateFileName()

        // Получаем данные из ViewModel
        val clients = sharedViewModel.clients.value ?: emptyList()
        val resources = sharedViewModel.comings.value ?: emptyList()
        val settings = sharedViewModel.setting.value ?: emptyList()
        val orders = sharedViewModel.orders.value ?: emptyList()
        val typeMaterials = sharedViewModel.typeMaterials.value ?: emptyList()
        val typeNames = sharedViewModel.typeNames.value ?: emptyList()
        val typeStatusComings = sharedViewModel.typeStatusComings.value ?: emptyList()
        val typeStatusOrders = sharedViewModel.typeStatusOrders.value ?: emptyList()
        val typeStatusPays = sharedViewModel.typeStatusPays.value ?: emptyList()
        val typeAccumulations = sharedViewModel.typeAccumulations.value ?: emptyList()
        val typeUnits = sharedViewModel.typeUnits.value ?: emptyList()
        val workers = sharedViewModel.workers.value ?: emptyList()

        // Создаем объект, который будет содержать все данные
        val data = mapOf(
            "clients" to clients,
            "resources" to resources,
            "settings" to settings,
            "orders" to orders,
            "typeMaterials" to typeMaterials,
            "typeNames" to typeNames,
            "typeStatusComings" to typeStatusComings,
            "typeStatusOrders" to typeStatusOrders,
            "typeStatusPays" to typeStatusPays,
            "typeAccumulations" to typeAccumulations,
            "typeUnits" to typeUnits,
            "workers" to workers
        )

        // Преобразуем объект в JSON
        val json = gson.toJson(data)

        // Сохраняем JSON в файл
        saveJsonToFileInSelectedDirectory(uri, json, fileName)
    }

    // Метод для генерации имени файла
    private fun generateFileName(): String {
        val dateFormat = SimpleDateFormat("yyyy.MM.dd-HH:mm", Locale.getDefault())
        val currentDate = dateFormat.format(Date())
        return "DataForPlanner_$currentDate.json"
    }

    //метод для сохрания JSON в файл
    private fun saveJsonToFileInSelectedDirectory(uri: Uri, json: String, fileName: String) {
        val contentResolver = context.contentResolver
        val documentFile = DocumentFile.fromTreeUri(context, uri)

        documentFile?.createFile("application/json", fileName)?.let { file ->
            contentResolver.openOutputStream(file.uri)?.use { outputStream ->
                OutputStreamWriter(outputStream).use { writer ->
                    writer.write(json)
                }
            }
        }
    }
}