package ee.pw.edu.pl.doborpartnera.ui.screen.profile

import androidx.annotation.StringRes
import ee.pw.edu.pl.doborpartnera.core.viewmodel.UiState
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileState(
    val name: String = "",
    val surname: String = "",
    val description: String = "",
    val avatar: String? = null,
    val images: List<String> = emptyList(),
    val interests: List<String> = emptyList(),
    val isLoading: Boolean = true,
    @StringRes val errorMsg: Int? = null,
    val isInError: Boolean = false,
) : UiState
