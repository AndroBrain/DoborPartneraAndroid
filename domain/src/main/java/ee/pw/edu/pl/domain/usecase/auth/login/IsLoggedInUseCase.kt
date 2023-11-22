package ee.pw.edu.pl.domain.usecase.auth.login

import ee.pw.edu.pl.domain.repository.AuthRepository
import kotlinx.coroutines.flow.map

class IsLoggedInUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke() = authRepository.getToken().map { !it.isNullOrEmpty() }
}
