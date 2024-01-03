import android.content.Context
import project.roomsiswa.data.DatabaseCafe
import project.roomsiswa.data.MenuDao
import project.roomsiswa.data.PesananDao
import project.roomsiswa.repositori.RepositoriMenu
import project.roomsiswa.repositori.RepositoriPesanan

interface ContainerApp {
    val repositoriMenu: RepositoriMenu
    val repositoriPesanan: RepositoriPesanan
}

// masing-masing dari repositori menu & pesanan menyematkan kedua DAO untuk digabungkan dalam repositori yang sama (menu & pesanan), karena kedua DAO tersebut berada dalam object database yg sama yakni DatabaseCafe

class ContainerDataApp(private val context: Context) : ContainerApp {
    private val menuDao: MenuDao = DatabaseCafe.getDatabase(context).menuDao()
    private val pesananDao: PesananDao = DatabaseCafe.getDatabase(context).pesananDao()

    override val repositoriMenu: RepositoriMenu by lazy {
        OfflineRepositoriCafe(menuDao, pesananDao)
    }

    override val repositoriPesanan: RepositoriPesanan by lazy {
        OfflineRepositoriCafe(menuDao, pesananDao)
    }
}
