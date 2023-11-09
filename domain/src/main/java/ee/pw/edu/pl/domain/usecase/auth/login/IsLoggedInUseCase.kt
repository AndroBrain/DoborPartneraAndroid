package ee.pw.edu.pl.domain.usecase.auth.login

import ee.pw.edu.pl.domain.repository.AuthRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.map

class IsLoggedInUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke() = authRepository.getToken().map { !it.isNullOrEmpty() }
}
