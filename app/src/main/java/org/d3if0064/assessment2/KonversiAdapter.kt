package org.d3if0064.assessment2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ArrayAdapter
import android.widget.TextView
import org.d3if0064.assessment2.model.Konversi
import java.text.FieldPosition

class KonversiAdapter(
    val konversiCtx: Context,
    val layoutResId: Int,
    val konversiList: MutableList<Konversi>
) :
    ArrayAdapter<Konversi>(konversiCtx, layoutResId, konversiList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(konversiCtx)

        val view: View = layoutInflater.inflate(layoutResId, null)

        val suhuAsal: TextView = view.findViewById(R.id.asalText)
        val jenisKonversi: TextView = view.findViewById(R.id.jenisText)
        val hasilKonversi: TextView = view.findViewById(R.id.hasilText)

        val konversi = konversiList[position]

        suhuAsal.text = konversi.suhuAsal
        jenisKonversi.text = konversi.jenisKonversi
        hasilKonversi.text = konversi.hasilKonversi

        return view
    }
}