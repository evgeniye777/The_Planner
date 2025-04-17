package com.example.the_planner_semen.data_bd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {

    var listener: DataChangeListener? = null // Сделаем его публичным

    private val _clients = MutableLiveData<List<Client>>()
    val clients: LiveData<List<Client>> get() = _clients

    private val _resource = MutableLiveData<List<Resource>>()
    val comings: LiveData<List<Resource>> get() = _resource

    private val _setting = MutableLiveData<List<Setting>>()
    val setting: LiveData<List<Setting>> get() = _setting

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

    // Метод для установки списка ресурсов
    fun setResources(resourceList: List<Resource>) {
        _resource.value = resourceList
    }

    // Метод для установки списка данных настроек
    fun setSettings(settingList: List<Setting>) {
        _setting.value = settingList
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
        var new_id: Int = -1
        var r: Boolean = false
        when (item) {
            is Client -> {
                item.id = new_id
                val currentList = _clients.value ?: emptyList()
                _clients.value = currentList + item
            }
            is Resource -> {
                item.id = new_id
                val currentList = _resource.value ?: emptyList()
                _resource.value = currentList + item
            }
            is Setting -> {
                item.id = new_id
                val currentList = _setting.value ?: emptyList()
                _setting.value = currentList + item
            }
            is Order -> {
                item.id = new_id
                val currentList = _orders.value ?: emptyList()
                _orders.value = currentList + item
            }
            is TypeMaterial -> {
                /*item.id = new_id
                val currentList = _typeMaterials.value ?: emptyList()
                _typeMaterials.value = currentList + item*/
                item.id = new_id
                val currentList = _typeMaterials.value ?: emptyList()

                // Проверяем, существует ли элемент с таким же id
                val exists = currentList.any { it.id == item.id }

                if (!exists) {
                    r=true
                    // Если элемент не существует, добавляем его в список
                    _typeMaterials.value = currentList + item
                } else {
                    // Элемент уже существует, можно обработать это событие
                    println("Элемент с id ${item.id} уже существует.")
                }
            }
            is TypeName -> {
                /*item.id = new_id
                val currentList = _typeNames.value ?: emptyList()
                _typeNames.value = currentList + item*/
                item.id = new_id
                val currentList = _typeNames.value ?: emptyList()

                // Проверяем, существует ли элемент с таким же name (сравнение по буквам)
                val exists = currentList.any { it.name == item.name }

                if (!exists) {
                    r=true
                    // Если элемент не существует, добавляем его в список
                    _typeNames.value = currentList + item
                } else {
                    // Элемент уже существует, можно обработать это событие
                    println("Элемент с name '${item.name}' уже существует.")
                }
            }
            is TypeStatusComing -> {
                /*item.id = new_id
                val currentList = _typeStatusComings.value ?: emptyList()
                _typeStatusComings.value = currentList + item*/
                item.id = new_id
                val currentList = _typeStatusComings.value ?: emptyList()

                // Проверяем, существует ли элемент с таким же name (сравнение по буквам)
                val exists = currentList.any { it.name == item.name }

                if (!exists) {
                    r=true
                    // Если элемент не существует, добавляем его в список
                    _typeStatusComings.value = currentList + item
                } else {
                    // Элемент уже существует, можно обработать это событие
                    println("Элемент с name '${item.name}' уже существует.")
                }
            }
            is TypeStatusOrder -> {
               /* item.id = new_id
                val currentList = _typeStatusOrders.value ?: emptyList()
                _typeStatusOrders.value = currentList + item*/
                item.id = new_id
                val currentList = _typeStatusOrders.value ?: emptyList()

                // Проверяем, существует ли элемент с таким же name (сравнение по буквам)
                val exists = currentList.any { it.name == item.name }

                if (!exists) {
                    r=true
                    // Если элемент не существует, добавляем его в список
                    _typeStatusOrders.value = currentList + item
                } else {
                    // Элемент уже существует, можно обработать это событие
                    println("Элемент с name '${item.name}' уже существует.")
                }
            }
            is TypeStatusPay -> {
               /* item.id = new_id
                val currentList = _typeStatusPays.value ?: emptyList()
                _typeStatusPays.value = currentList + item*/
                item.id = new_id
                val currentList = _typeStatusPays.value ?: emptyList()

                // Проверяем, существует ли элемент с таким же name (сравнение по буквам)
                val exists = currentList.any { it.name == item.name }

                if (!exists) {
                    r=true
                    // Если элемент не существует, добавляем его в список
                    _typeStatusPays.value = currentList + item
                } else {
                    // Элемент уже существует, можно обработать это событие
                    println("Элемент с name '${item.name}' уже существует.")
                }
            }
            is TypeAccumulation -> {
                /*item.id = new_id
                val currentList = _typeAccumulations.value ?: emptyList()
                _typeAccumulations.value = currentList + item*/
                item.id = new_id
                val currentList = _typeAccumulations.value ?: emptyList()

                // Проверяем, существует ли элемент с таким же name (сравнение по буквам)
                val exists = currentList.any { it.name == item.name }

                if (!exists) {
                    r=true
                    // Если элемент не существует, добавляем его в список
                    _typeAccumulations.value = currentList + item
                } else {
                    // Элемент уже существует, можно обработать это событие
                    println("Элемент с name '${item.name}' уже существует.")
                }
            }
            is TypeUnit -> {
                /*item.id = new_id
                val currentList = _typeUnits.value ?: emptyList()
                _typeUnits.value = currentList + item*/
                item.id = new_id
                val currentList = _typeUnits.value ?: emptyList()

                // Проверяем, существует ли элемент с таким же name (сравнение по буквам)
                val exists = currentList.any { it.name == item.name }

                if (!exists) {
                    r=true
                    // Если элемент не существует, добавляем его в список
                    _typeUnits.value = currentList + item
                } else {
                    // Элемент уже существует, можно обработать это событие
                    println("Элемент с name '${item.name}' уже существует.")
                }
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
        if (r) {new_id = listener?.onDataChanged(item, 0)?:-1}
        else {new_id = -2}
        return new_id
    }

    //Метод для редактирования объекта
    fun <T> updateItem(item: T){
        listener?.onDataChanged(item, 1)
        when (item) {
            is Client -> {
                val currentList = _clients.value?.toMutableList() ?: mutableListOf()
                val index = currentList.indexOfFirst { it.id == item.id }
                if (index != -1) {
                    currentList[index] = item // Обновляем элемент
                    _clients.value = currentList
                }
            }
            is Resource -> {
                val currentList = _resource.value?.toMutableList() ?: mutableListOf()
                val index = currentList.indexOfFirst { it.id == item.id }
                if (index != -1) {
                    currentList[index] = item // Обновляем элемент
                    _resource.value = currentList
                }
            }
            is Setting -> {
                val currentList = _setting.value?.toMutableList() ?: mutableListOf()
                val index = currentList.indexOfFirst { it.id == item.id }
                if (index != -1) {
                    currentList[index] = item // Обновляем элемент
                    _setting.value = currentList
                }
            }
            is Order -> {
                val currentList = _orders.value?.toMutableList() ?: mutableListOf()
                val index = currentList.indexOfFirst { it.id == item.id }
                if (index != -1) {
                    currentList[index] = item // Обновляем элемент
                    _orders.value = currentList
                }
            }
            is TypeMaterial -> {
                val currentList = _typeMaterials.value?.toMutableList() ?: mutableListOf()
                val index = currentList.indexOfFirst { it.id == item.id }
                if (index != -1) {
                    currentList[index] = item // Обновляем элемент
                    _typeMaterials.value = currentList
                }
            }
            is TypeName -> {
                val currentList = _typeNames.value?.toMutableList() ?: mutableListOf()
                val index = currentList.indexOfFirst { it.id == item.id }
                if (index != -1) {
                    currentList[index] = item // Обновляем элемент
                    _typeNames.value = currentList
                }
            }
            is TypeStatusComing -> {
                val currentList = _typeStatusComings.value?.toMutableList() ?: mutableListOf()
                val index = currentList.indexOfFirst { it.id == item.id }
                if (index != -1) {
                    currentList[index] = item // Обновляем элемент
                    _typeStatusComings.value = currentList
                }
            }
            is TypeStatusOrder -> {
                val currentList = _typeStatusOrders.value?.toMutableList() ?: mutableListOf()
                val index = currentList.indexOfFirst { it.id == item.id }
                if (index != -1) {
                    currentList[index] = item // Обновляем элемент
                    _typeStatusOrders.value = currentList
                }
            }
            is TypeStatusPay -> {
                val currentList = _typeStatusPays.value?.toMutableList() ?: mutableListOf()
                val index = currentList.indexOfFirst { it.id == item.id }
                if (index != -1) {
                    currentList[index] = item // Обновляем элемент
                    _typeStatusPays.value = currentList
                }
            }
            is TypeAccumulation -> {
                val currentList = _typeAccumulations.value?.toMutableList() ?: mutableListOf()
                val index = currentList.indexOfFirst { it.id == item.id }
                if (index != -1) {
                    currentList[index] = item // Обновляем элемент
                    _typeAccumulations.value = currentList
                }
            }
            is TypeUnit -> {
                val currentList = _typeUnits.value?.toMutableList() ?: mutableListOf()
                val index = currentList.indexOfFirst { it.id == item.id }
                if (index != -1) {
                    currentList[index] = item // Обновляем элемент
                    _typeUnits.value = currentList
                }
            }
            is Worker -> {
                val currentList = _workers.value?.toMutableList() ?: mutableListOf()
                val index = currentList.indexOfFirst { it.id == item.id }
                if (index != -1) {
                    currentList[index] = item // Обновляем элемент
                    _workers.value = currentList
                }
            }
            else -> {
                throw IllegalArgumentException("Unsupported type")
            }
        }
    }

    //метод для удаления объекта
    fun <T> deleteItem(item: T) {
        listener?.onDataChanged(item, 2)
        when (item) {
            is Client -> {
                val currentList = _clients.value?.toMutableList() ?: mutableListOf()
                currentList.removeIf { it.id == item.id } // Удаляем элемент
                _clients.value = currentList
            }
            is Resource -> {
                val currentList = _resource.value?.toMutableList() ?: mutableListOf()
                currentList.removeIf { it.id == item.id } // Удаляем элемент
                _resource.value = currentList
            }
            is Setting -> {
                val currentList = _setting.value?.toMutableList() ?: mutableListOf()
                currentList.removeIf { it.id == item.id } // Удаляем элемент
                _setting.value = currentList
            }
            is Order -> {
                val currentList = _orders.value?.toMutableList() ?: mutableListOf()
                currentList.removeIf { it.id == item.id } // Удаляем элемент
                _orders.value = currentList
            }
            is TypeMaterial -> {
                val currentList = _typeMaterials.value?.toMutableList() ?: mutableListOf()
                currentList.removeIf { it.id == item.id } // Удаляем элемент
                _typeMaterials.value = currentList
            }
            is TypeName -> {
                val currentList = _typeNames.value?.toMutableList() ?: mutableListOf()
                currentList.removeIf { it.id == item.id } // Удаляем элемент
                _typeNames.value = currentList
            }
            is TypeStatusComing -> {
                val currentList = _typeStatusComings.value?.toMutableList() ?: mutableListOf()
                currentList.removeIf { it.id == item.id } // Удаляем элемент
                _typeStatusComings.value = currentList
            }
            is TypeStatusOrder -> {
                val currentList = _typeStatusOrders.value?.toMutableList() ?: mutableListOf()
                currentList.removeIf { it.id == item.id } // Удаляем элемент
                _typeStatusOrders.value = currentList
            }
            is TypeStatusPay -> {
                val currentList = _typeStatusPays.value?.toMutableList() ?: mutableListOf()
                currentList.removeIf { it.id == item.id } // Удаляем элемент
                _typeStatusPays.value = currentList
            }
            is TypeAccumulation -> {
                val currentList = _typeAccumulations.value?.toMutableList() ?: mutableListOf()
                currentList.removeIf { it.id == item.id } // Удаляем элемент
                _typeAccumulations.value = currentList
            }
            is TypeUnit -> {
                val currentList = _typeUnits.value?.toMutableList() ?: mutableListOf()
                currentList.removeIf { it.id == item.id } // Удаляем элемент
                _typeUnits.value = currentList
            }
            is Worker -> {
                val currentList = _workers.value?.toMutableList() ?: mutableListOf()
                currentList.removeIf { it.id == item.id } // Удаляем элемент
                _workers.value = currentList
            }
            else -> {
                throw IllegalArgumentException("Unsupported type")
            }
        }
    }
}