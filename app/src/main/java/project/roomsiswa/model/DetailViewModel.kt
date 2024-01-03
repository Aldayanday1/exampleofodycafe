package project.roomsiswa.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import project.roomsiswa.repositori.RepositoriSiswa
import project.roomsiswa.ui.halaman.DetailsDestination

class DetailsViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoriSiswa: RepositoriSiswa
) : ViewModel(){

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    private val siswaId: Int = checkNotNull(savedStateHandle[DetailsDestination.siswaIdArg])

    // get id
    val uiState: StateFlow<ItemDetailsUiState> =
        repositoriSiswa.getSiswaStream(siswaId)
            .filterNotNull()
            .map {
                ItemDetailsUiState(detailSiswa = it.toDetailSiswa())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ItemDetailsUiState()
            )

    // delete
    suspend fun deleteItem(){
        repositoriSiswa.deleteSiswa(uiState.value.detailSiswa.toSiswa())
    }
}

data class ItemDetailsUiState(
    val outOfStock: Boolean = true,
    val detailSiswa: DetailSiswa = DetailSiswa(),
)