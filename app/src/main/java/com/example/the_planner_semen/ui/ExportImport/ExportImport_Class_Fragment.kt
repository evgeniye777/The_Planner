package com.example.the_planner_semen.ui.Statistics

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.the_planner_semen.data_bd.DataExporter
import com.example.the_planner_semen.data_bd.DataImporter
import com.example.the_planner_semen.data_bd.SharedViewModel
import com.example.the_planner_semen.databinding.FragmentExportimportBinding

class ExportImport_Class_Fragment : Fragment() {
    lateinit var filePickerLauncherImport: ActivityResultLauncher<Intent>
    lateinit var filePickerLauncherExport: ActivityResultLauncher<Intent>


    private lateinit var dataImporter: DataImporter
    private lateinit var dataExporter: DataExporter
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private lateinit var but_import: Button
    private lateinit var but_export: Button

    private var _binding: FragmentExportimportBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        filePickerLauncherImport = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    // Импортируем данные из выбранного файла
                    val resultMessage = dataImporter.importDataFromJson(requireContext(), uri)
                    // Выводим результат (например, в Toast или TextView)
                    Toast.makeText(requireContext(), resultMessage, Toast.LENGTH_LONG).show()
                }
            }
        }

        // Инициализация filePickerLauncher для выбора директории
        filePickerLauncherExport = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    // Сохраняем данные в выбранной директории
                    dataExporter.saveJsonToFileInSelectedDirectory(uri)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //инициализация класса для вы
        dataImporter = DataImporter(sharedViewModel)
        dataExporter = DataExporter(requireContext(),sharedViewModel)

        _binding = FragmentExportimportBinding.inflate(inflater, container, false)
        val root: View = binding.root

        but_import = binding.idButImport
        but_import.setOnClickListener {
            dataImporter.openFilePicker(this)
        }

        but_export= binding.idButExport
        but_export.setOnClickListener {
            dataExporter.openDirectoryPicker(this)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun vivod(s: String) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
    }
}