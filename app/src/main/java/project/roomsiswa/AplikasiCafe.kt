package project.roomsiswa

import ContainerApp
import ContainerDataApp
import android.app.Application

// class AplikasiCafe (kelas yg mewakili kseluruhan olah data dari ContainerDataApp saat apk dijalankan lewat onCreate) & di alokasikan terhadap manifest.xml sebagai application dari project saya
// wadah dari keseluruhan olah data

class AplikasiCafe : Application() {

    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(this)
    }
}
