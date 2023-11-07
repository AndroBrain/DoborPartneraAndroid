package ee.pw.edu.pl.doborpartnera.ui.screen.profile.edit

import androidx.annotation.StringRes
import ee.pw.edu.pl.doborpartnera.core.viewmodel.UiState
import kotlinx.parcelize.Parcelize

@Parcelize
data class EditProfileState(
    val description: String = "",
    val interests: List<String> = emptyList(),
    @StringRes val descriptionError: Int? = null,
    @StringRes val errorMsg: Int? = null,
    val isLoading: Boolean = false,
    val isSaved: Boolean = false,
) : UiState
