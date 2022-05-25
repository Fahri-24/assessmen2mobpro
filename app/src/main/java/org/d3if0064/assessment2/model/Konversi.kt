package org.d3if0064.assessment2.model

data class Konversi(
    val id : String,
    val suhuAsal : String,
    val jenisKonversi: String,
    val hasilKonversi: String
) {
    constructor() : this("", "", "", "")
}
