package project.roomsiswa.model

import Siswa
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import project.roomsiswa.repositori.RepositoriSiswa

class EntryViewModel(private val repositoriSiswa: RepositoriSiswa): ViewModel(){

    /*
    * Berisi status Siswa saat ini
    */
    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    /* Fungsi untuk memvalidasi input */
    private fun validasiInput(uiState: DetailSiswa = uiStateSiswa.detailSiswa): Boolean {
        return with(uiState){
            /**  memeriksa apakah nilai id tidak sama dengan nilai defaultnya (0). */
            id != 0 && nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }
    fun updateUiState(detailSiswa: DetailSiswa){
        uiStateSiswa =
            UIStateSiswa(detailSiswa = detailSiswa, isEntryValid = validasiInput(detailSiswa))
    }

    // insert
    suspend fun saveSiswa(){
        if (validasiInput()){
            repositoriSiswa.insertSiswa(uiStateSiswa.detailSiswa.toSiswa())
        }
    }
}

// Mewakili Status Ui untuk Siswa
data class UIStateSiswa(
    val detailSiswa: DetailSiswa = DetailSiswa(),
    val isEntryValid: Boolean = false
)

data class DetailSiswa(
    val id: Int? = null, // Tipe data integer yang dapat berisi nilai null (nullable),
    val nama: String = "",
    val alamat: String = "",
    val telpon: String = "",
)

// Fungsi untuk mengkonversi data input ke data dalam tabel sesuai jenis datanya
fun DetailSiswa.toSiswa(): Siswa = Siswa(
    id = id ?: 0, // Jika id null, maka gunakan nilai default 0 setelah operator elvis
    nama = nama,
    alamat = alamat,
    telpon = telpon
)

fun Siswa.toUiStateSiswa(isEntryValid: Boolean = false): UIStateSiswa = UIStateSiswa(
    detailSiswa = this.toDetailSiswa(),
    isEntryValid = isEntryValid
)

fun Siswa.toDetailSiswa(): DetailSiswa = DetailSiswa(
    id = id,
    nama = nama,
    alamat = alamat,
    telpon = telpon
)

