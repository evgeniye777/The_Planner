package com.example.the_planner_semen.data_bd

data class Client(
    var id: Int=-1,
    val name: String?,
    val phone: String?,
    val email: String?,
    val visibility: Int = 1
)

data class Coming(
    var id: Int=-1,
    val statusComingId: Int = 1,
    val typeAccumulationId: Int = 1,
    val date: String?,
    val nameId: Int = -1,
    val materialId: Int = -1,
    val sX: Int = -1,
    val sY: Int = -1,
    val sZ: Int = -1,
    val sScalar: Double = -1.0,
    val unitId: Int = -1,
    val count: Int = -1,
    val priceOne: Double?,
    val statusPayId: Int = 1,
    val datePay: String?,
    val ordersIds: String?,
    val workerId: Int = -1,
    val salary: Double?,
    val delivery: Double?
)

data class Order(
    var id: Int=-1,
    val statusOrderId: Int = 1,
    val date: String?,
    val clientId: Int = -1,
    val nameText: String?,
    val comingIds: String? = null,
    val count: Int = -1,
    val priceOne: Double? = 0.0,
    val statusPayId: Int = 1,
    val dateClosing: String? = null,
    val reaction: Int = -1,
    val workerId: Int = -1,
    val salary: Double? = null
)

data class TypeMaterial(
    var id: Int=-1,
    val name: String?,
    val i: Int = -1,
    val visibility: Int = 1
)

data class TypeName(
    var id: Int=-1,
    val name: String?,
    val i: Int = -1,
    val visibility: Int = 1
)

data class TypeStatusComing(
    var id: Int=-1,
    val name: String?,
    val i: Int = -1,
    val visibility: Int = 1
)

data class TypeStatusOrder(
    var id: Int=-1,
    val name: String?,
    val i: Int = -1,
    val visibility: Int = 1
)

data class TypeStatusPay(
    var id: Int=-1,
    val name: Int,
    val i: Int = -1,
    val visibility: Int = 1
)

data class TypeAccumulation(
    var id: Int=-1,
    val name: String?,
    val i: Int = -1,
    val visibility: Int = 1
)

data class TypeUnit(
    var id: Int=-1,
    val name: String?,
    val i: Int = -1,
    val visibility: Int = 1
)

data class Worker(
    var id: Int=-1,
    val name: String?,
    val phone: String?,
    val email: String?,
    val visibility: Int = 1
)
