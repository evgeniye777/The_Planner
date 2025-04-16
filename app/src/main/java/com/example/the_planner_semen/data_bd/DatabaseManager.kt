package com.example.the_planner_semen.data_bd
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import java.io.IOException

class DatabaseManager(private val context: Context, private val viewModel: SharedViewModel) : DataChangeListener{
    private val databaseHelper: DataBases = DataBases(context)
    private var database: SQLiteDatabase

    init {
        viewModel.listener = this
        updateDatabase()
        database = databaseHelper.writableDatabase
        loadData()
    }

    private fun updateDatabase() {
        try {
            databaseHelper.updateDataBase()
        } catch (e: IOException) {
            throw Error("Unable to update database")
        }
    }

    private fun loadData() {
        loadClients()
        loadComings()
        loadOrders()
        loadTypeMaterials()
        loadTypeNames()
        loadTypeStatusComings()
        loadTypeStatusOrders()
        loadTypeStatusPays()
        loadTypeAccumulations()
        loadTypeUnits()
        loadWorkers()
    }

    @SuppressLint("Range")
    private fun loadClients() {
        val cursor: Cursor = database.rawQuery("SELECT * FROM clients", null)
        val clients = mutableListOf<Client>()
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val phone = cursor.getString(cursor.getColumnIndex("phone"))
                val email = cursor.getString(cursor.getColumnIndex("email"))
                val visibility = cursor.getInt(cursor.getColumnIndex("visibility"))
                clients.add(Client(id, name, phone, email, visibility))
            } while (cursor.moveToNext())
        }
        cursor.close()
        viewModel.setClients(clients)
    }

    @SuppressLint("Range")
    private fun loadComings() {
        val cursor: Cursor = database.rawQuery("SELECT * FROM coming", null)
        val comings = mutableListOf<Coming>()
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val statusComingId = cursor.getInt(cursor.getColumnIndex("status_coming_id"))
                val typeAccumulationId = cursor.getInt(cursor.getColumnIndex("type_accumulation_id"))
                val date = cursor.getString(cursor.getColumnIndex("date"))
                val nameId = cursor.getInt(cursor.getColumnIndex("name_id"))
                val materialId = cursor.getInt(cursor.getColumnIndex("material_id"))
                val sX = cursor.getInt(cursor.getColumnIndex("s_x"))
                val sY = cursor.getInt(cursor.getColumnIndex("s_y"))
                val sZ = cursor.getInt(cursor.getColumnIndex("s_z"))
                val sScalar = cursor.getDouble(cursor.getColumnIndex("s_scalar"))
                val unitId = cursor.getInt(cursor.getColumnIndex("unit_id"))
                val count = cursor.getInt(cursor.getColumnIndex("count"))
                val priceOne = cursor.getDouble(cursor.getColumnIndex("price_one"))
                val statusPayId = cursor.getInt(cursor.getColumnIndex("status_pay_id"))
                val datePay = cursor.getString(cursor.getColumnIndex("date_pay"))
                val ordersIds = cursor.getString(cursor.getColumnIndex("orders_ids"))
                val workerId = cursor.getInt(cursor.getColumnIndex("worker_id"))
                val salary = cursor.getDouble(cursor.getColumnIndex("salary"))
                val delivery = cursor.getDouble(cursor.getColumnIndex("delivery"))
                comings.add(Coming(id, statusComingId, typeAccumulationId, date, nameId, materialId, sX, sY, sZ, sScalar, unitId, count, priceOne, statusPayId, datePay, ordersIds, workerId, salary, delivery))
            } while (cursor.moveToNext())
        }
        cursor.close()
        viewModel.setComings(comings)
    }

    // Метод для загрузки заказов
    @SuppressLint("Range")
    private fun loadOrders() {
        val cursor: Cursor = database.rawQuery("SELECT * FROM orders", null)
        val orders = mutableListOf<Order>()
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val statusOrderId = cursor.getInt(cursor.getColumnIndex("status_order_id"))
                val date = cursor.getString(cursor.getColumnIndex("date"))
                val clientId = cursor.getInt(cursor.getColumnIndex("client_id"))
                val nameText = cursor.getString(cursor.getColumnIndex("name_text"))
                val comingIds = cursor.getString(cursor.getColumnIndex("coming_ids"))
                val count = cursor.getInt(cursor.getColumnIndex("count"))
                val priceOne = cursor.getDouble(cursor.getColumnIndex("price_one"))
                val statusPayId = cursor.getInt(cursor.getColumnIndex("status_pay_id"))
                val dateClosing = cursor.getString(cursor.getColumnIndex("date_closing"))
                val reaction = cursor.getInt(cursor.getColumnIndex("reaction"))
                val workerId = cursor.getInt(cursor.getColumnIndex("worker_id"))
                val salary = cursor.getDouble(cursor.getColumnIndex("salary"))
                orders.add(Order(id, statusOrderId, date, clientId, nameText, comingIds, count, priceOne, statusPayId, dateClosing, reaction, workerId, salary))
            } while (cursor.moveToNext())
        }
        cursor.close()
        viewModel.setOrders(orders)
    }

    // Метод для загрузки типов материалов
    @SuppressLint("Range")
    private fun loadTypeMaterials() {
        val cursor: Cursor = database.rawQuery("SELECT * FROM types_material", null)
        val typeMaterials = mutableListOf<TypeMaterial>()
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val i = cursor.getInt(cursor.getColumnIndex("i"))
                val visibility = cursor.getInt(cursor.getColumnIndex("visibility"))
                typeMaterials.add(TypeMaterial(id, name, i, visibility))
            } while (cursor.moveToNext())
        }
        cursor.close()
        viewModel.setTypeMaterials(typeMaterials)
    }

    // Метод для загрузки типов имен
    @SuppressLint("Range")
    private fun loadTypeNames() {
        val cursor: Cursor = database.rawQuery("SELECT * FROM types_name", null)
        val typeNames = mutableListOf<TypeName>()
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val i = cursor.getInt(cursor.getColumnIndex("i"))
                val visibility = cursor.getInt(cursor.getColumnIndex("visibility"))
                typeNames.add(TypeName(id, name, i, visibility))
            } while (cursor.moveToNext())
        }
        cursor.close()
        viewModel.setTypeNames(typeNames)
    }

    // Метод для загрузки типов статусов приходов
    @SuppressLint("Range")
    private fun loadTypeStatusComings() {
        val cursor: Cursor = database.rawQuery("SELECT * FROM types_status_coming", null)
        val typeStatusComings = mutableListOf<TypeStatusComing>()
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val i = cursor.getInt(cursor.getColumnIndex("i"))
                val visibility = cursor.getInt(cursor.getColumnIndex("visibility"))
                typeStatusComings.add(TypeStatusComing(id, name, i, visibility))
            } while (cursor.moveToNext())
        }
        cursor.close()
        viewModel.setTypeStatusComings(typeStatusComings)
    }

    // Метод для загрузки типов статусов заказов
    @SuppressLint("Range")
    private fun loadTypeStatusOrders() {
        val cursor: Cursor = database.rawQuery("SELECT * FROM types_status_order", null)
        val typeStatusOrders = mutableListOf<TypeStatusOrder>()
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val i = cursor.getInt(cursor.getColumnIndex("i"))
                val visibility = cursor.getInt(cursor.getColumnIndex("visibility"))
                typeStatusOrders.add(TypeStatusOrder(id, name, i, visibility))
            } while (cursor.moveToNext())
        }
        cursor.close()
        viewModel.setTypeStatusOrders(typeStatusOrders)
    }

    // Метод для загрузки типов статусов оплаты
    @SuppressLint("Range")
    private fun loadTypeStatusPays() {
        val cursor: Cursor = database.rawQuery("SELECT * FROM types_status_pay", null)
        val typeStatusPays = mutableListOf<TypeStatusPay>()
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val i = cursor.getInt(cursor.getColumnIndex("i"))
                val visibility = cursor.getInt(cursor.getColumnIndex("visibility"))
                typeStatusPays.add(TypeStatusPay(id, name, i, visibility))
            } while (cursor.moveToNext())
        }
        cursor.close()
        viewModel.setTypeStatusPays(typeStatusPays)
    }

    // Метод для загрузки типов накоплений
    @SuppressLint("Range")
    private fun loadTypeAccumulations() {
        val cursor: Cursor = database.rawQuery("SELECT * FROM types_type_accumulation", null)
        val typeAccumulations = mutableListOf<TypeAccumulation>()
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val i = cursor.getInt(cursor.getColumnIndex("i"))
                val visibility = cursor.getInt(cursor.getColumnIndex("visibility"))
                typeAccumulations.add(TypeAccumulation(id, name, i, visibility))
            } while (cursor.moveToNext())
        }
        cursor.close()
        viewModel.setTypeAccumulations(typeAccumulations)
    }

    // Метод для загрузки типов единиц
    @SuppressLint("Range")
    private fun loadTypeUnits() {
        val cursor: Cursor = database.rawQuery("SELECT * FROM types_unit", null)
        val typeUnits = mutableListOf<TypeUnit>()
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val i = cursor.getInt(cursor.getColumnIndex("i"))
                val visibility = cursor.getInt(cursor.getColumnIndex("visibility"))
                typeUnits.add(TypeUnit(id, name, i, visibility))
            } while (cursor.moveToNext())
        }
        cursor.close()
        viewModel.setTypeUnits(typeUnits)
    }

    // Метод для загрузки работников
    @SuppressLint("Range")
    private fun loadWorkers() {
        val cursor: Cursor = database.rawQuery("SELECT * FROM workers", null)
        val workers = mutableListOf<Worker>()
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val phone = cursor.getString(cursor.getColumnIndex("phone"))
                val email = cursor.getString(cursor.getColumnIndex("email"))
                val visibility = cursor.getInt(cursor.getColumnIndex("visibility"))
                workers.add(Worker(id, name, phone, email, visibility))
            } while (cursor.moveToNext())
        }
        cursor.close()
        viewModel.setWorkers(workers)
    }

    // Реализация метода интерфейса DataChangeListener
    override fun <T> onDataChanged(data: T, action: Int): Int {
        var new_id:Int = -1
        when (data) {
            is Client -> {
                when (action) {
                    0 -> new_id = addClient(data) // Добавить
                    1 -> updateClient(data) // Изменить
                    2 -> deleteClient(data.id) // Удалить
                }
            }
            is Coming -> {
                when (action) {
                    0 -> new_id = addComing(data) // Добавить
                    1 -> updateComing(data) // Изменить
                    2 -> deleteComing(data.id) // Удалить
                }
            }
            is Order -> {
                when (action) {
                    0 -> new_id = addOrder(data) // Добавить
                    1 -> updateOrder(data) // Изменить
                    2 -> deleteOrder(data.id) // Удалить
                }
            }
            is TypeMaterial -> {
                when (action) {
                    0 -> new_id = addTypesMaterial(data) // Добавить
                    1 -> updateTypesMaterial(data) // Изменить
                    2 -> deleteTypesMaterial(data.id) // Удалить
                }
            }
            is TypeName -> {
                when (action) {
                    0 -> new_id = addTypesName(data) // Добавить
                    1 -> updateTypesName(data) // Изменить
                    2 -> deleteTypesName(data.id) // Удалить
                }
            }
            is TypeStatusComing -> {
                when (action) {
                    0 -> new_id = addTypesStatusComing(data) // Добавить
                    1 -> updateTypesStatusComing(data) // Изменить
                    2 -> deleteTypesStatusComing(data.id) // Удалить
                }
            }
            is TypeStatusOrder -> {
                when (action) {
                    0 -> new_id = addTypesStatusOrder(data) // Добавить
                    1 -> updateTypesStatusOrder(data) // Изменить
                    2 -> deleteTypesStatusOrder(data.id) // Удалить
                }
            }
            is TypeStatusPay -> {
                when (action) {
                    0 -> new_id = addTypesStatusPay(data) // Добавить
                    1 -> updateTypesStatusPay(data) // Изменить
                    2 -> deleteTypesStatusPay(data.id) // Удалить
                }
            }
            is TypeAccumulation -> {
                when (action) {
                    0 -> new_id = addTypesTypeAccumulation(data) // Добавить
                    1 -> updateTypesTypeAccumulation(data) // Изменить
                    2 -> deleteTypesTypeAccumulation(data.id) // Удалить
                }
            }
            is TypeUnit -> {
                when (action) {
                    0 -> new_id = addTypesUnit(data) // Добавить
                    1 -> updateTypesUnit(data) // Изменить
                    2 -> deleteTypesUnit(data.id) // Удалить
                }
            }
            is Worker -> {
                when (action) {
                    0 -> new_id = addWorker(data) // Добавить
                    1 -> updateWorker(data) // Изменить
                    2 -> deleteWorker(data.id) // Удалить
                }
            }
            else -> {
                // Обработка случая, когда тип данных не поддерживается
            }
        }
        return new_id
    }

    ///Добавление Изменение Удаление Client
    private fun addClient(client: Client): Int {
        val values = ContentValues().apply {
            put("name", client.name)
            put("phone", client.phone)
            put("email", client.email)
            put("visibility", client.visibility)
        }
        return (database.insert("clients", null, values)).toInt()
    }

    private fun updateClient(client: Client) {
        val values = ContentValues().apply {
            put("name", client.name)
            put("phone", client.phone)
            put("email", client.email)
            put("visibility", client.visibility)
        }
        database.update("clients", values, "id=?", arrayOf(client.id.toString()))
    }

    private fun deleteClient(clientId: Int) {
        database.delete("clients", "id=?", arrayOf(clientId.toString()))
    }


    ///Добавление Изменение Удаление Coming
    private fun addComing(coming: Coming): Int {
        val values = ContentValues().apply {
            put("status_coming_id", coming.statusComingId)
            put("type_accumulation_id", coming.typeAccumulationId)
            put("date", coming.date)
            put("name_id", coming.nameId)
            put("material_id", coming.materialId)
            put("s_x", coming.sX)
            put("s_y", coming.sY)
            put("s_z", coming.sZ)
            put("s_scalar", coming.sScalar)
            put("unit_id", coming.unitId)
            put("count", coming.count)
            put("price_one", coming.priceOne)
            put("status_pay_id", coming.statusPayId)
            put("date_pay", coming.datePay)
            put("orders_ids", coming.ordersIds)
            put("worker_id", coming.workerId)
            put("salary", coming.salary)
            put("delivery", coming.delivery)
        }
        return database.insert("coming", null, values).toInt()
    }

    private fun updateComing(coming: Coming) {
        val values = ContentValues().apply {
            put("status_coming_id", coming.statusComingId)
            put("type_accumulation_id", coming.typeAccumulationId)
            put("date", coming.date)
            put("name_id", coming.nameId)
            put("material_id", coming.materialId)
            put("s_x", coming.sX)
            put("s_y", coming.sY)
            put("s_z", coming.sZ)
            put("s_scalar", coming.sScalar)
            put("unit_id", coming.unitId)
            put("count", coming.count)
            put("price_one", coming.priceOne)
            put("status_pay_id", coming.statusPayId)
            put("date_pay", coming.datePay)
            put("orders_ids", coming.ordersIds)
            put("worker_id", coming.workerId)
            put("salary", coming.salary)
            put("delivery", coming.delivery)
        }
        database.update("coming", values, "id=?", arrayOf(coming.id.toString()))
    }

    private fun deleteComing(comingId: Int) {
        database.delete("coming", "id=?", arrayOf(comingId.toString()))
    }


    ///Добавление Изменение Удаление Order
    private fun addOrder(order: Order): Int {
        val values = ContentValues().apply {
            put("status_order_id", order.statusOrderId)
            put("date", order.date)
            put("client_id", order.clientId)
            put("name_text", order.nameText)
            put("coming_ids", order.comingIds)
            put("count", order.count)
            put("price_one", order.priceOne)
            put("status_pay_id", order.statusPayId)
            put("date_closing", order.dateClosing)
            put("reaction", order.reaction)
            put("worker_id", order.workerId)
            put("salary", order.salary)
        }
        return database.insert("orders", null, values).toInt()
    }

    private fun updateOrder(order: Order) {
        val values = ContentValues().apply {
            put("status_order_id", order.statusOrderId)
            put("date", order.date)
            put("client_id", order.clientId)
            put("name_text", order.nameText)
            put("coming_ids", order.comingIds)
            put("count", order.count)
            put("price_one", order.priceOne)
            put("status_pay_id", order.statusPayId)
            put("date_closing", order.dateClosing)
            put("reaction", order.reaction)
            put("worker_id", order.workerId)
            put("salary", order.salary)
        }
        database.update("orders", values, "id=?", arrayOf(order.id.toString()))
    }

    private fun deleteOrder(orderId: Int) {
        database.delete("orders", "id=?", arrayOf(orderId.toString()))
    }

    ///Добавление Изменение Удаление Coming
    private fun addTypesMaterial(typesMaterial: TypeMaterial): Int {
        val values = ContentValues().apply {
            put("name", typesMaterial.name)
            put("i", typesMaterial.i)
            put("visibility", typesMaterial.visibility)
        }
        return database.insert("types_material", null, values).toInt()
    }

    private fun updateTypesMaterial(typesMaterial: TypeMaterial) {
        val values = ContentValues().apply {
            put("name", typesMaterial.name)
            put("i", typesMaterial.i)
            put("visibility", typesMaterial.visibility)
        }
        database.update("types_material", values, "id=?", arrayOf(typesMaterial.id.toString()))
    }

    private fun deleteTypesMaterial(typesMaterialId: Int) {
        database.delete("types_material", "id=?", arrayOf(typesMaterialId.toString()))
    }

    ///Добавление Изменение Удаление TypesName
    private fun addTypesName(typesName: TypeName): Int {
        val values = ContentValues().apply {
            put("name", typesName.name)
            put("i", typesName.i)
            put("visibility", typesName.visibility)
        }
        return database.insert("types_name", null, values).toInt()
    }

    private fun updateTypesName(typesName: TypeName) {
        val values = ContentValues().apply {
            put("name", typesName.name)
            put("i", typesName.i)
            put("visibility", typesName.visibility)
        }
        database.update("types_name", values, "id=?", arrayOf(typesName.id.toString()))
    }

    private fun deleteTypesName(typesNameId: Int) {
        database.delete("types_name", "id=?", arrayOf(typesNameId.toString()))
    }

    ///Добавление Изменение Удаление TypesStatusComing
    private fun addTypesStatusComing(typesStatusComing: TypeStatusComing): Int {
        val values = ContentValues().apply {
            put("name", typesStatusComing.name)
            put("i", typesStatusComing.i)
            put("visibility", typesStatusComing.visibility)
        }
        return database.insert("types_status_coming", null, values).toInt()
    }

    private fun updateTypesStatusComing(typesStatusComing: TypeStatusComing) {
        val values = ContentValues().apply {
            put("name", typesStatusComing.name)
            put("i", typesStatusComing.i)
            put("visibility", typesStatusComing.visibility)
        }
        database.update("types_status_coming", values, "id=?", arrayOf(typesStatusComing.id.toString()))
    }

    private fun deleteTypesStatusComing(typesStatusComingId: Int) {
        database.delete("types_status_coming", "id=?", arrayOf(typesStatusComingId.toString()))
    }

    ///Добавление Изменение Удаление TypesStatusOrder
    private fun addTypesStatusOrder(typesStatusOrder: TypeStatusOrder): Int {
        val values = ContentValues().apply {
            put("name", typesStatusOrder.name)
            put("i", typesStatusOrder.i)
            put("visibility", typesStatusOrder.visibility)
        }
        return database.insert("types_status_order", null, values).toInt()
    }

    private fun updateTypesStatusOrder(typesStatusOrder: TypeStatusOrder) {
        val values = ContentValues().apply {
            put("name", typesStatusOrder.name)
            put("i", typesStatusOrder.i)
            put("visibility", typesStatusOrder.visibility)
        }
        database.update("types_status_order", values, "id=?", arrayOf(typesStatusOrder.id.toString()))
    }

    private fun deleteTypesStatusOrder(typesStatusOrderId: Int) {
        database.delete("types_status_order", "id=?", arrayOf(typesStatusOrderId.toString()))
    }

    ///Добавление Изменение Удаление TypesStatusPay
    private fun addTypesStatusPay(typesStatusPay: TypeStatusPay): Int {
        val values = ContentValues().apply {
            put("name", typesStatusPay.name)
            put("i", typesStatusPay.i)
            put("visibility", typesStatusPay.visibility)
        }
        return database.insert("types_status_pay", null, values).toInt()
    }

    private fun updateTypesStatusPay(typesStatusPay: TypeStatusPay) {
        val values = ContentValues().apply {
            put("name", typesStatusPay.name)
            put("i", typesStatusPay.i)
            put("visibility", typesStatusPay.visibility)
        }
        database.update("types_status_pay", values, "id=?", arrayOf(typesStatusPay.id.toString()))
    }

    private fun deleteTypesStatusPay(typesStatusPayId: Int) {
        database.delete("types_status_pay", "id=?", arrayOf(typesStatusPayId.toString()))
    }

    ///Добавление Изменение Удаление TypeAccumulation
    private fun addTypesTypeAccumulation(typesTypeAccumulation: TypeAccumulation): Int {
        val values = ContentValues().apply {
            put("name", typesTypeAccumulation.name)
            put("i", typesTypeAccumulation.i)
            put("visibility", typesTypeAccumulation.visibility)
        }
        return database.insert("types_type_accumulation", null, values).toInt()
    }

    private fun updateTypesTypeAccumulation(typesTypeAccumulation: TypeAccumulation) {
        val values = ContentValues().apply {
            put("name", typesTypeAccumulation.name)
            put("i", typesTypeAccumulation.i)
            put("visibility", typesTypeAccumulation.visibility)
        }
        database.update("types_type_accumulation", values, "id=?", arrayOf(typesTypeAccumulation.id.toString()))
    }

    private fun deleteTypesTypeAccumulation(typesTypeAccumulationId: Int) {
        database.delete("types_type_accumulation", "id=?", arrayOf(typesTypeAccumulationId.toString()))
    }

    ///Добавление Изменение Удаление Unit
    private fun addTypesUnit(typesUnit: TypeUnit): Int {
        val values = ContentValues().apply {
            put("name", typesUnit.name)
            put("i", typesUnit.i)
            put("visibility", typesUnit.visibility)
        }
        return  database.insert("types_unit", null, values).toInt()
    }

    private fun updateTypesUnit(typesUnit: TypeUnit) {
        val values = ContentValues().apply {
            put("name", typesUnit.name)
            put("i", typesUnit.i)
            put("visibility", typesUnit.visibility)
        }
        database.update("types_unit", values, "id=?", arrayOf(typesUnit.id.toString()))
    }

    private fun deleteTypesUnit(typesUnitId: Int) {
        database.delete("types_unit", "id=?", arrayOf(typesUnitId.toString()))
    }

    ///Добавление Изменение Удаление Worker
    private fun addWorker(worker: Worker): Int {
        val values = ContentValues().apply {
            put("name", worker.name)
            put("phone", worker.phone)
            put("email", worker.email)
            put("visibility", worker.visibility)
        }
       return database.insert("workers", null, values).toInt()
    }

    private fun updateWorker(worker: Worker) {
        val values = ContentValues().apply {
            put("name", worker.name)
            put("phone", worker.phone)
            put("email", worker.email)
            put("visibility", worker.visibility)
        }
        database.update("workers", values, "id=?", arrayOf(worker.id.toString()))
    }

    private fun deleteWorker(workerId: Int) {
        database.delete("workers", "id=?", arrayOf(workerId.toString()))
    }
}