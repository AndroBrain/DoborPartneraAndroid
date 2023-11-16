package ee.pw.edu.pl.data.datasource.chat.remote

import com.microsoft.signalr.HubConnection
import com.microsoft.signalr.HubConnectionBuilder
import ee.pw.edu.pl.data.core.remote.ApiService
import ee.pw.edu.pl.data.datasource.auth.local.AuthLocalDataSource
import ee.pw.edu.pl.data.model.apiCall
import ee.pw.edu.pl.data.model.chat.remote.MessageResponse
import ee.pw.edu.pl.data.model.chat.remote.SendMessageRequest
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first

class SignalRChatRemoteDataSource(
    private val authLocalDataSource: AuthLocalDataSource,
    private val api: ApiService,
) : ChatRemoteDataSource {
    private lateinit var connection: HubConnection
    override fun connectToChat() = callbackFlow {
        val token = authLocalDataSource.getToken().first() ?: return@callbackFlow
        connection = HubConnectionBuilder.create("http://localhost:8081/hub/chat")
            .withAccessTokenProvider(
                Single.defer { Single.just(token) }
            )
            .build()
        connection.on(
            "ReceiveMessage",
            { id, toUser, fromUser, message, timestamp ->
                trySend(
                    MessageResponse(
                        id = id,
                        fromUser = fromUser,
                        toUser = toUser,
                        messageText = message,
                        sentTimestamp = timestamp,
                    )
                )
            },
            Int::class.java,
            Int::class.java,
            Int::class.java,
            String::class.java,
            Long::class.java,
        )
        connection.start()
        awaitClose {
            connection.stop()
        }
    }

    override fun sendMessage(request: SendMessageRequest) {
        connection.invoke("SendMessage", request.receiverId, request.message)
    }

    override suspend fun getChats() = apiCall {
        api.getConversations()
    }
}
