package ee.pw.edu.pl.domain.usecase.chat

import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.repository.ChatRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SubscribeToChatUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {
    operator fun invoke(): Flow<UseCaseResult<Chat>> = chatRepository.getChat().map {
        UseCaseResult.Ok(
            Chat(
                text = it,
                isYour = false
            )
        )
    }
}
