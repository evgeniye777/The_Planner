package com.example.the_planner_semen.data_bd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {

    var listener: DataChangeListener? = null // Сделаем его публичным

    private val _clients = MutableLiveData<List<Client>>()
    val clients: LiveData<List<Client>> get() = _clients

    private val _comings = MutableLiveData<List<Coming>>()
    val comings: LiveData<List<Coming>> get() = _comings

    private val _orders = MutableLiveData<List<Order>>()
    val orders: LiveData<List<Order>> get() = _orders

    private val _typeMaterials = MutableLiveData<List<TypeMaterial>>()
    val typeMaterials: LiveData<List<TypeMaterial>> get() = _typeMaterials

    private val _typeNames = MutableLiveData<List<TypeName>>()
    val typeNames: LiveData<List<TypeName>> get() = _typeNames

    private val _typeStatusComings = MutableLiveData<List<TypeStatusComing>>()
    val typeStatusComings: LiveData<List<TypeStatusComing>> get() = _typeStatusComings

    private val _typeStatusOrders = MutableLiveData<List<TypeStatusOrder>>()
    val typeStatusOrders: LiveData<List<TypeStatusOrder>> get() = _typeStatusOrders

    private val _typeStatusPays = MutableLiveData<List<TypeStatusPay>>()
    val typeStatusPays: LiveData<List<TypeStatusPay>> get() = _typeStatusPays

    private val _typeAccumulations = MutableLiveData<List<TypeAccumulation>>()
    val typeAccumulations: LiveData<List<TypeAccumulation>> get() = _typeAccumulations

    private val _typeUnits = MutableLiveData<List<TypeUnit>>()
    val typeUnits: LiveData<List<TypeUnit>> get() = _typeUnits

    private val _workers = MutableLiveData<List<Worker>>()
    val workers: LiveData<List<Worker>> get() = _workers

    // Метод для установки списка клиентов
    fun setClients(clientList: List<Client>) {
        _clients.value = clientList
    }

    // Метод для установки списка приходов
    fun setComings(comingList: List<Coming>) {
        _comings.value = comingList
    }

    // Метод для установки списка заказов
    fun setOrders(orderList: List<Order>) {
        _orders.value = orderList
    }

    // Метод для установки списка типов материалов
    fun setTypeMaterials(typeMaterialList: List<TypeMaterial>) {
        _typeMaterials.value = typeMaterialList
    }

    // Метод для установки списка типов имен
    fun setTypeNames(typeNameList: List<TypeName>) {
        _typeNames.value = typeNameList
    }

    // Метод для установки списка типов статусов приходов
    fun setTypeStatusComings(typeStatusComingList: List<TypeStatusComing>) {
        _typeStatusComings.value = typeStatusComingList
    }

    // Метод для установки списка типов статусов заказов
    fun setTypeStatusOrders(typeStatusOrderList: List<TypeStatusOrder>) {
        _typeStatusOrders.value = typeStatusOrderList
    }

    // Метод для установки списка типов статусов оплаты
    fun setTypeStatusPays(typeStatusPayList: List<TypeStatusPay>) {
        _typeStatusPays.value = typeStatusPayList
    }

    // Метод для установки списка типов накоплений
    fun setTypeAccumulations(typeAccumulationList: List<TypeAccumulation>) {
        _typeAccumulations.value = typeAccumulationList
    }

    // Метод для установки списка типов единиц
    fun setTypeUnits(typeUnitList: List<TypeUnit>) {
        _typeUnits.value = typeUnitList
    }

    // Метод для установки списка работников
    fun setWorkers(workerList: List<Worker>) {
        _workers.value = workerList
    }

    //обобщенный метод добавления объекта
    fun <T> addItem(item: T):Int {
        val new_id: Int = listener?.onDataChanged(item, 0)?:-1
        when (item) {
            is Client -> {
                item.id = new_id
                val currentList = _clients.value ?: emptyList()
                _clients.value = currentList + item
            }
            is Coming -> {
                item.id = new_id
                val currentList = _comings.value ?: emptyList()
                _comings.value = currentList + item
            }
            is Order -> {
                item.id = new_id
                val currentList = _orders.value ?: emptyList()
                _orders.value = currentList + item
            }
            is TypeMaterial -> {
                item.id = new_id
                val currentList = _typeMaterials.value ?: emptyList()
                _typeMaterials.value = currentList + item
            }
            is TypeName -> {
                item.id = new_id
                val currentList = _typeNames.value ?: emptyList()
                _typeNames.value = currentList + item
            }
            is TypeStatusComing -> {
                item.id = new_id
                val currentList = _typeStatusComings.value ?: emptyList()
                _typeStatusComings.value = currentList + item
            }
            is TypeStatusOrder -> {
                item.id = new_id
                val currentList = _typeStatusOrders.value ?: emptyList()
                _typeStatusOrders.value = currentList + item
            }
            is TypeStatusPay -> {
                item.id = new_id
                val currentList = _typeStatusPays.value ?: emptyList()
                _typeStatusPays.value = currentList + item
            }
            is TypeAccumulation -> {
                item.id = new_id
                val currentList = _typeAccumulations.value ?: emptyList()
                _typeAccumulations.value = currentList + item
            }
            is TypeUnit -> {
                item.id = new_id
                val currentList = _typeUnits.value ?: emptyList()
                _typeUnits.value = currentList + item
            }
            is Worker -> {
                item.id = new_id
                val currentList = _workers.value ?: emptyList()
                _workers.value = currentList + item
            }
            else -> {
                throw IllegalArgumentException("Unsupported type")
            }
        }
        return new_id
    }
}