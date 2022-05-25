package org.d3if0064.assessment2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.google.firebase.database.*
import org.d3if0064.assessment2.databinding.FragmentDataBinding
import org.d3if0064.assessment2.model.Konversi

class DataFragment : Fragment() {

    private lateinit var binding : FragmentDataBinding
    private lateinit var database: DatabaseReference
    private lateinit var listKonversi: ListView
    private  lateinit var konversiList : MutableList<Konversi>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDataBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        database = FirebaseDatabase.getInstance().getReference("konversi")

        listKonversi = binding.listKonversi
        konversiList = mutableListOf()

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(data: DataSnapshot) {
                if (data.exists()) {
                    for (h in data.children) {
                        val konversi = h.getValue(Konversi::class.java)
                        if (konversi != null) {
                            konversiList.add(konversi)
                        }
                    }

                    val adapter = activity?.let { KonversiAdapter(it.applicationContext, R.layout.item_konversi, konversiList) }
                    listKonversi.adapter = adapter
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

}