package ee.pw.edu.pl.doborpartnera.ui.screen.profile.edit

import android.net.Uri
import androidx.annotation.StringRes
import ee.pw.edu.pl.doborpartnera.core.viewmodel.UiState
import kotlinx.parcelize.Parcelize

@Parcelize
data class EditProfileState(
    val profileImage: Uri? = null,
    val description: String = "",
    val interests: List<String> = emptyList(),
    @StringRes val descriptionError: Int? = null,
    val images: Set<Uri> = emptySet(),
    @StringRes val interestsError: Int? = null,
    @StringRes val errorMsg: Int? = null,
    val isLoading: Boolean = false,
    val isSaved: Boolean = false,
) : UiState
