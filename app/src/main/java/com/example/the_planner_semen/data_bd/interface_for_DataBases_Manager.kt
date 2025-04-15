package com.example.the_planner_semen.data_bd

interface DataChangeListener {
    fun <T> onDataChanged(data: T, action: Int) : Int
}