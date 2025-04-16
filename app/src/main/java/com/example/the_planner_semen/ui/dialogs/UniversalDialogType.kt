package com.example.the_planner_semen.ui.dialogs

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.the_planner_semen.R
import com.example.the_planner_semen.data_bd.DialogItemType
import com.example.the_planner_semen.data_bd.SharedViewModel
import com.example.the_planner_semen.data_bd.TypeAccumulation
import com.example.the_planner_semen.data_bd.TypeMaterial
import com.example.the_planner_semen.data_bd.TypeName
import com.example.the_planner_semen.data_bd.TypeStatusComing
import com.example.the_planner_semen.data_bd.TypeStatusOrder
import com.example.the_planner_semen.data_bd.TypeStatusPay
import com.example.the_planner_semen.data_bd.TypeUnit

class UniversalDialogType<T : DialogItemType>(
    private val context: Context,
    private val sharedViewModel: SharedViewModel,
    private val action: Int = 0,
    private var item: T? = null
) {

    @SuppressLint("MissingInflatedId")
    fun showDialog() {
        val inflater = LayoutInflater.from(context)
        val dialogView: View = inflater.inflate(R.layout.dialog_new_type, null)

        val editTextName: EditText = dialogView.findViewById(R.id.id_editTextName)
        val checkBoxTextVisibility: CheckBox = dialogView.findViewById(R.id.id_CheckBoxVisibility)
        val linearCheckBox: LinearLayout = dialogView.findViewById(R.id.id_linear_checkbox)

        var title = ""
        if (action == 0) {
            linearCheckBox.visibility = View.GONE
            title = context.getString(R.string.add_list)
        } else {
            editTextName.setText(item?.name ?: "")
            checkBoxTextVisibility.isChecked = (item?.visibility ?: 0) == 1
            title = context.getString(R.string.change_list)
        }

        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setView(dialogView)
            .setTitle(title)
            .setPositiveButton(context.getString(R.string.save_list), null)
            .setNegativeButton(context.getString(R.string.close_list)) { dialog, which -> dialog.dismiss() }

        val dialog = dialogBuilder.create()

        dialog.setOnShowListener {
            // Изменяем цвет функциональных кнопок
            val button = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            button.setTextColor(ContextCompat.getColor(context, R.color.black))
            val button2 = dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
            button2.setTextColor(ContextCompat.getColor(context, R.color.black))

            // Обработчик кнопки сохранения
            button.setOnClickListener {
                if (editTextName.text.toString().isNotEmpty()) {
                    val name = editTextName.text.toString()
                    val visibility = if (checkBoxTextVisibility.isChecked) 1 else 0

                    if (action == 0) {
                        // Создаем новый элемент в зависимости от типа
                        try {
                            val newItem: T = when (item) {
                                is TypeMaterial -> TypeMaterial(name = name)
                                is TypeName -> TypeName(name = name)
                                is TypeStatusComing -> TypeStatusComing(name = name)
                                is TypeStatusOrder -> TypeStatusOrder(name = name)
                                is TypeStatusPay -> TypeStatusPay(name = name)
                                is TypeAccumulation -> TypeAccumulation(name = name)
                                is TypeUnit -> TypeUnit (name = name)
                                else -> throw IllegalArgumentException("Unsupported item type")
                            }as T

                            sharedViewModel.addItem(newItem)
                            showToast("Данные добавлены")
                        }catch (e:Exception) {showToast("Неизвестная ошибка добавления")  }

                    } else {
                        if (item != null) {
                            item!!.name = name
                            item!!.visibility = visibility
                            sharedViewModel.updateItem(item)
                            showToast("Данные сохранены")
                        } else {
                            showToast("Неизвестная ошибка сохранения")
                        }
                    }
                    dialog.dismiss()
                } else {
                    showToast("Необходимо заполнить обязательные поля *")
                }
            }
        }

        dialog.show()
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}