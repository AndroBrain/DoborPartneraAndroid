package ee.pw.edu.pl.doborpartnera.ui.screen.profile.edit

import androidx.annotation.StringRes
import ee.pw.edu.pl.doborpartnera.core.viewmodel.UiState
import kotlinx.parcelize.Parcelize

@Parcelize
data class EditProfileState(
    val profileImageUrl: String? = null,
    val description: String = "",
    val interests: List<String> = emptyList(),
    @StringRes val descriptionError: Int? = null,
    val pictures: List<String> = emptyList(),
    @StringRes val errorMsg: Int? = null,
    val isLoading: Boolean = false,
    val isSaved: Boolean = false,
) : UiState
