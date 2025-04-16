package com.example.the_planner_semen.data_bd

interface DialogItemPerson {
    var id: Int
    var name: String?
    var phone: String? 
    var email: String? 
    var visibility: Int
}
interface DialogItemType {
    var id: Int
    var name: String?
    var i: Int
    var visibility: Int
}

data class Client(
    override var id: Int = -1,
    override var name: String?,
    override var phone: String? = null,
    override var email: String? = null,
    override var visibility: Int = 1
) : DialogItemPerson

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
    override var id: Int = -1,
    override var name: String?,
    override var visibility: Int = 1,
    override var i: Int = -1
) : DialogItemType

data class TypeName(
    override var id: Int = -1,
    override var name: String?,
    override var visibility: Int = 1,
    override var i: Int = -1
) : DialogItemType

data class TypeStatusComing(
    override var id: Int = -1,
    override var name: String?,
    override var visibility: Int = 1,
    override var i: Int = -1
) : DialogItemType

data class TypeStatusOrder(
    override var id: Int = -1,
    override var name: String?,
    override var visibility: Int = 1,
    override var i: Int = -1
) : DialogItemType

data class TypeStatusPay(
    override var id: Int = -1,
    override var name: String?,
    override var visibility: Int = 1,
    override var i: Int = -1
) : DialogItemType

data class TypeAccumulation(
    override var id: Int = -1,
    override var name: String?,
    override var visibility: Int = 1,
    override var i: Int = -1
) : DialogItemType

data class TypeUnit(
    override var id: Int = -1,
    override var name: String?,
    override var visibility: Int = 1,
    override var i: Int = -1
) : DialogItemType

data class Worker(
    override var id: Int = -1,
    override var name: String?,
    override var phone: String? = null,
    override var email: String? = null,
    override var visibility: Int = 1
) : DialogItemPerson
