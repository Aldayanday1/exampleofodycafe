package project.roomsiswa.repositori

import Menu
import Pesanan
import kotlinx.coroutines.flow.Flow

// repositori -> antarmuka untuk mengelola data secara internal antara aplikasi dan data (interaksi dengan DAO)

interface RepositoriMenu {
    suspend fun insertMenu(menu: Menu)

    suspend fun updateMenu(menu: Menu)

    suspend fun deleteMenu(menu: Menu)

    fun getMenuStream(id: Int): Flow<Menu?>

    fun getAllMenuStream(): Flow<List<Menu>>
}

interface RepositoriPesanan {
    suspend fun insertPesanan(pesanan: Pesanan)

    suspend fun updatePesanan(pesanan: Pesanan)

    suspend fun deletePesanan(pesanan: Pesanan)

    fun getPesananStream(id: Int): Flow<Pesanan?>

    fun getAllPesananStream(): Flow<List<Pesanan>>

    fun getPesananByMenuId(idMenuForeignKey: Int): Flow<List<Pesanan>>
    suspend fun checkMenuExistence(idMenuForeignKey: Int): Int
}

//
//interface RepositoriSiswa {
//    suspend fun insertSiswa(siswa: Siswa)
//
//    suspend fun updateSiswa(siswa: Siswa)
//
//    suspend fun deleteSiswa(siswa: Siswa)
//
//    fun getSiswaStream(id: Int): Flow<Siswa?>
//
//    fun getAllSiswaStream(): Flow<List<Siswa>>
//}