package project.roomsiswa.model

import android.text.Spannable.Factory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import project.roomsiswa.AplikasiCafe

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(
                aplikasiCafe().container.repositoriMenu,
                aplikasiCafe().container.repositoriPesanan,
            )
        }
        initializer {
            EntryViewModel(
                aplikasiCafe().container.repositoriMenu,
                aplikasiCafe().container.repositoriPesanan
            )
        }
        initializer {
            DetailsViewModel(
                createSavedStateHandle(),
                aplikasiCafe().container.repositoriMenu,
                aplikasiCafe().container.repositoriPesanan
            )
        }
        initializer {
            EditViewModel(
                createSavedStateHandle(),
                aplikasiCafe().container.repositoriMenu,
                aplikasiCafe().container.repositoriPesanan
            )
        }
    }
}

fun CreationExtras.aplikasiCafe(): AplikasiCafe =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiCafe)