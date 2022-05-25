package org.d3if0064.assessment2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_home.*
import org.d3if0064.assessment2.databinding.FragmentHomeBinding
import org.d3if0064.assessment2.model.Konversi

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var database: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Connect Database
        database = FirebaseDatabase.getInstance().getReference("konversi")

        binding.konvert.setOnClickListener { konversiSuhu() }
        binding.reset.setOnClickListener { resetView() }
    }


    //    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding.konvert.setOnClickListener {
//             }
//        binding.reset.setOnClickListener {
//            resetView()
//        }
//    }
    private fun konversiSuhu() {
        var hasil: Double

        val suhu = binding.celciusEditText.text.toString()

        val selectedId = binding.radioGroup.checkedRadioButtonId

        if (selectedId != -1) {
//            val radio: RadioButton = findViewById(id)

            // Kelvin
            if (kelvin.isChecked) {
                hasil = suhu.toInt() + 273.15
                binding.suhuKonvert.text = hasil.toString()

                binding.kategoriSuhu.text = binding.kelvin.text
            }

            // Farenheit
            if (farenheit.isChecked) {
                hasil = (suhu.toDouble() * 9 / 5) + 32
                binding.suhuKonvert.text = hasil.toString()

                binding.kategoriSuhu.text = binding.farenheit.text
            }

            // Reamur
            if (reamur.isChecked) {
                hasil = suhu.toDouble() * 4 / 5
                binding.suhuKonvert.text = hasil.toString()

                binding.kategoriSuhu.text = binding.reamur.text
            }

            val suhuCelcius: String = binding.celciusEditText.text.toString()
            val hasilKonversi: String = binding.suhuKonvert.text.toString()
            val jenisKonversi: String = binding.kategoriSuhu.text.toString()

            val konvertId: String? = database.push().key

            val konversiSuhu = konvertId?.let {
                Konversi(it, suhuCelcius, hasilKonversi, jenisKonversi)
            }

            if (konvertId != null) {
                database.child(konvertId).setValue(konversiSuhu).addOnCompleteListener {
                    Toast.makeText(
                        activity as AppCompatActivity,
                        "Data berhasil disimpan!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                resetView()
            }

        } else {
            Toast.makeText(context, "Anda tidak memilih konversi apapun", Toast.LENGTH_SHORT).show()
        }
    }

    private fun findViewById(id: Int): RadioButton {
        return findViewById(id)
    }

    private fun resetView() {
        binding.apply {
            celciusEditText.text.clear()
            radioGroup.clearCheck()
            suhuKonvert.text = " "
            kategoriSuhu.text = " "
        }
    }
}