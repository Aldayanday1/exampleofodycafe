package project.roomsiswa.model

import Siswa
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import project.roomsiswa.repositori.RepositoriSiswa

class HomeViewModel(private val repositoriSiswa: RepositoriSiswa):ViewModel(){
    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }

    // get all data (ASC)
    val homeUiState: StateFlow<HomeUiState> = repositoriSiswa.getAllSiswaStream()
        .map { HomeUiState(listSiswa = it.toList()) }
        .stateIn(scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUiState()
        )

    data class HomeUiState(
        val listSiswa: List<Siswa> = listOf()
    )
}