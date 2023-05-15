package ee.pw.edu.pl.doborpartnera.ui.screen.profile

import androidx.annotation.StringRes
import ee.pw.edu.pl.doborpartnera.core.viewmodel.UiState
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileState(
    val email: String = "",
    val name: String = "",
    val surname: String = "",
    val shortDescription: String = "",
    val imageUrl: String? = null,
    val imageGallery: List<String> = emptyList(),
    val isLoading: Boolean = true,
    @StringRes val errorMsg: Int? = null,
    val isInError: Boolean = false,
) : UiState
