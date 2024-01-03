import kotlinx.coroutines.flow.Flow
import project.roomsiswa.data.MenuDao
import project.roomsiswa.data.PesananDao
import project.roomsiswa.repositori.RepositoriMenu
import project.roomsiswa.repositori.RepositoriPesanan

class OfflineRepositoriCafe(

    // kedua dao diimplementasikan di class OfflineRepositoriCafe

    private val menuDao: MenuDao,
    private val pesananDao: PesananDao


    // menghubungkan antara repositori - DAO & mengambil dependensi di DAO  untuk ketersediaan interface dibagian repositori nya

): RepositoriMenu, RepositoriPesanan {

    override suspend fun insertMenu(menu: Menu) = menuDao.insert(menu)

    override suspend fun updateMenu(menu: Menu) = menuDao.update(menu)

    override suspend fun deleteMenu(menu: Menu) = menuDao.delete(menu)

    override fun getMenuStream(id: Int): Flow<Menu?> = menuDao.getMenu(id)

    override fun getAllMenuStream(): Flow<List<Menu>> = menuDao.getAllMenu()


    override suspend fun insertPesanan(pesanan: Pesanan) = pesananDao.insert(pesanan)

    override suspend fun updatePesanan(pesanan: Pesanan) = pesananDao.update(pesanan)

    override suspend fun deletePesanan(pesanan: Pesanan) = pesananDao.delete(pesanan)

    override fun getPesananStream(id: Int): Flow<Pesanan?> = pesananDao.getPesanan(id)

    override fun getAllPesananStream(): Flow<List<Pesanan>> = pesananDao.getAllPesanan()

    override fun getPesananByMenuId(idMenuForeignKey: Int): Flow<List<Pesanan>> =
        pesananDao.getPesananByMenuId(idMenuForeignKey)

    override suspend fun checkMenuExistence(idMenuForeignKey: Int): Int =
        pesananDao.checkMenuExistence(idMenuForeignKey)
}
