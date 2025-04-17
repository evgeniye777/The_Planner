package com.example.the_planner_semen.data_bd

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.example.the_planner_semen.ui.Statistics.ExportImport_Class_Fragment
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader

class DataImporter(private val sharedViewModel: SharedViewModel) {

    // Метод для открытия проводника и выбора файла
    fun openFilePicker(fragment: ExportImport_Class_Fragment) {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "application/json"
            addCategory(Intent.CATEGORY_OPENABLE)
        }
        fragment.filePickerLauncherImport.launch(intent)
    }

    // Метод для импорта данных из JSON
    fun importDataFromJson(context: Context, uri: Uri): String {
        val gson = Gson()
        val errorLog = StringBuilder()

        try {
            val reader = BufferedReader(InputStreamReader(context.contentResolver.openInputStream(uri)!!))
            val dataType = object : TypeToken<Map<String, List<Map<String, Any>>>>() {}.type
            val data: Map<String, List<Map<String, Any>>> = gson.fromJson(reader, dataType)

            // Обрабатываем каждый тип данных
            data["clients"]?.forEach { item ->
                val client = Client(
                    id = item["id"] as? Int ?: -1,
                    name = item["name"] as? String,
                    phone = item["phone"] as? String,
                    email = item["email"] as? String,
                    visibility = item["visibility"] as? Int ?: 1
                )
                sharedViewModel.addItem(client)
            }

            data["resources"]?.forEach { item ->
                val resource = Resource(
                    id = item["id"] as? Int ?: -1,
                    statusComingId = item["statusComingId"] as? Int ?: 1,
                    typeAccumulationId = item["typeAccumulationId"] as? Int ?: 1,
                    date = item["date"] as? String,
                    nameId = item["nameId"] as? Int ?: -1,
                    materialId = item["materialId"] as? Int ?: -1,
                    sX = item["sX"] as? Int ?: -1,
                    sY = item["sY"] as? Int ?: -1,
                    sZ = item["sZ"] as? Int ?: -1,
                    sScalar = item["sScalar"] as? Double ?: -1.0,
                    unitId = item["unitId"] as? Int ?: -1,
                    count = item["count"] as? Int ?: -1,
                    priceOne = item["priceOne"] as? Double,
                    statusPayId = item["statusPayId"] as? Int ?: 1,
                    datePay = item["datePay"] as? String,
                    ordersIds = item["ordersIds"] as? String,
                    workerId = item["workerId"] as? Int ?: -1,
                    salary = item["salary"] as? Double,
                    delivery = item["delivery"] as? Double
                )
                sharedViewModel.addItem(resource)
            }

            data["settings"]?.forEach { item ->
                val setting = Setting(
                    id = item["id"] as? Int ?: -1,
                    name = item["name"] as? String,
                    data = item["data"] as? String // Предупреждение, если поле отсутствует
                )
                if (item["data"] == null) {
                    errorLog.append("Предупреждение: Поле 'data' отсутствует для настройки с id=${setting.id}\n")
                }
                sharedViewModel.addItem(setting)
            }
            data["orders"]?.forEach { item ->
                val order = Order(
                    id = item["id"] as? Int ?: -1,
                    statusOrderId = item["statusOrderId"] as? Int ?: 1,
                    date = item["date"] as? String,
                    clientId = item["clientId"] as? Int ?: -1,
                    nameText = item["nameText"] as? String,
                    comingIds = item["comingIds"] as? String,
                    count = item["count"] as? Int ?: -1,
                    priceOne = item["priceOne"] as? Double ?: 0.0,
                    statusPayId = item["statusPayId"] as? Int ?: 1,
                    dateClosing = item["dateClosing"] as? String,
                    reaction = item["reaction"] as? Int ?: -1,
                    workerId = item["workerId"] as? Int ?: -1,
                    salary = item["salary"] as? Double
                )
                sharedViewModel.addItem(order)
            }
            data["typeMaterials"]?.forEach { item ->
                val typeMaterial = TypeMaterial(
                    id = item["id"] as? Int ?: -1,
                    name = item["name"] as? String,
                    i = item["i"] as? Int ?: -1,
                    visibility = item["visibility"] as? Int ?: 1
                )
                sharedViewModel.addItem(typeMaterial)
            }

            data["typeNames"]?.forEach { item ->
                val typeName = TypeName(
                    id = item["id"] as? Int ?: -1,
                    name = item["name"] as? String,
                    i = item["i"] as? Int ?: -1,
                    visibility = item["visibility"] as? Int ?: 1
                )
                sharedViewModel.addItem(typeName)
            }

            data["typeStatusComings"]?.forEach { item ->
                val typeStatusComing = TypeStatusComing(
                    id = item["id"] as? Int ?: -1,
                    name = item["name"] as? String,
                    i = item["i"] as? Int ?: -1,
                    visibility = item["visibility"] as? Int ?: 1
                )
                sharedViewModel.addItem(typeStatusComing)
            }

            data["typeStatusOrders"]?.forEach { item ->
                val typeStatusOrder = TypeStatusOrder(
                    id = item["id"] as? Int ?: -1,
                    name = item["name"] as? String,
                    i = item["i"] as? Int ?: -1,
                    visibility = item["visibility"] as? Int ?: 1
                )
                sharedViewModel.addItem(typeStatusOrder)
            }

            data["typeStatusPays"]?.forEach { item ->
                val typeStatusPay = TypeStatusPay(
                    id = item["id"] as? Int ?: -1,
                    name = item["name"] as? String,
                    i = item["i"] as? Int ?: -1,
                    visibility = item["visibility"] as? Int ?: 1
                )
                sharedViewModel.addItem(typeStatusPay)
            }

            data["typeAccumulations"]?.forEach { item ->
                val typeAccumulation = TypeAccumulation(
                    id = item["id"] as? Int ?: -1,
                    name = item["name"] as? String,
                    i = item["i"] as? Int ?: -1,
                    visibility = item["visibility"] as? Int ?: 1
                )
                sharedViewModel.addItem(typeAccumulation)
            }

            data["typeUnits"]?.forEach { item ->
                val typeUnit = TypeUnit(
                    id = item["id"] as? Int ?: -1,
                    name = item["name"] as? String,
                    i = item["i"] as? Int ?: -1,
                    visibility = item["visibility"] as? Int ?: 1
                )
                sharedViewModel.addItem(typeUnit)
            }

            data["workers"]?.forEach { item ->
                val worker = Worker(
                    id = item["id"] as? Int ?: -1,
                    name = item["name"] as? String,
                    phone = item["phone"] as? String,
                    email = item["email"] as? String,
                    visibility = item["visibility"] as? Int ?: 1
                )
                sharedViewModel.addItem(worker)
            }

        } catch (e: JsonParseException) {
            return "Ошибка при парсинге JSON: ${e.message}"
        } catch (e: Exception) {
            return "Произошла ошибка: ${e.message}"
        }

        return errorLog.toString().ifEmpty { "Импорт завершен без ошибок." }
    }

    companion object {
        const val REQUEST_CODE_PICK_FILE = 1
    }
}
