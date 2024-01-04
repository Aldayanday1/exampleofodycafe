import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "tblMenu")
data class Menu(
    @PrimaryKey
    val idmenu : Int,
    val menu : String,
    val harga : String,
    val ketersediaan : String,
    val kategori : String,
)

@Entity(
    tableName = "tblPesanan",
    foreignKeys = [ForeignKey(
        // foreign key nya diambil dari data class Menu
        entity = Menu::class,
        parentColumns = ["idmenu"],
        childColumns = ["idMenuForeignKey"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("idMenuForeignKey")] // Menambahkan index untuk idMenuForeignKey
)
data class Pesanan(
    @PrimaryKey
    val idpesanan : Int,
    val nama : String,
    val detail : String,
    val metode : String,
    val tanggal : String,
    @ColumnInfo(name = "idMenuForeignKey")
    val idMenuForeignKey: Int // Foreign key reference to idmenu from Menu
)
