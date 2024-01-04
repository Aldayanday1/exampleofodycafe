package project.roomsiswa.data

import Menu
import Pesanan
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

// DAO -> logika bisnis dari sebuah data/manipulasi data objek ke database (CRUD)

@Dao
interface MenuDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(menu: Menu)

    @Update
    suspend fun update(menu: Menu)

    @Delete
    suspend fun delete(menu: Menu)

    // mengambil data dari idpesanan di tabel menu
    @Query("SELECT * from tblMenu WHERE idmenu = :idmenu")
    fun getMenu(idmenu: Int): Flow<Menu>

    // mengambil keseluruhan data dari tblMenu, serta mengurutkan alfabetis dari atribut nama dengan menggunakan ascending & descending, dari A-Z & Z-A
    @Query("SELECT * from tblMenu ORDER BY kategori ASC, ketersediaan DESC")
    fun getAllMenu(): Flow<List<Menu>>
}

@Dao
interface PesananDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pesanan: Pesanan)
    @Transaction
    suspend fun insertWithValidation(pesanan: Pesanan) {
        val menuIdExists = checkMenuExistence(pesanan.idMenuForeignKey) > 0
        if (menuIdExists) {
            insert(pesanan)
        } else {
            throw IllegalArgumentException("ID Menu tidak tersedia")
        }
    }
    @Update
    suspend fun update(pesanan: Pesanan)

    @Delete
    suspend fun delete(pesanan: Pesanan)

    @Query("SELECT * from tblPesanan WHERE idpesanan = :idpesanan")
    fun getPesanan(idpesanan: Int): Flow<Pesanan>

    @Query("SELECT * from tblPesanan ORDER BY nama ASC")
    fun getAllPesanan(): Flow<List<Pesanan>>

    // Mengambil data dari tabel pesanan yang kemudian dihubungkan kedalam tabelmenu, serta keterkaitan hubungan antara idMenuForeignKey di tblPesanan dengan idmenu yang ada di tblMenu
    @Query("SELECT * FROM tblPesanan INNER JOIN tblMenu ON tblPesanan.idMenuForeignKey = tblMenu.idmenu WHERE tblPesanan.idMenuForeignKey = :idMenuForeignKey")
    fun getPesananByMenuId(idMenuForeignKey: Int): Flow<List<Pesanan>>

    // Validasi saat penginputan nilai idMenuForeignKey
    @Query("SELECT COUNT(*) FROM tblMenu WHERE idmenu = :idMenuForeignKey")
    suspend fun checkMenuExistence(idMenuForeignKey: Int): Int
}
