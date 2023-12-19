package ee.pw.edu.pl.data.datasource.chat.remote

import android.content.Context
import com.microsoft.signalr.HubConnection
import com.microsoft.signalr.HubConnectionBuilder
import ee.pw.edu.pl.data.R
import ee.pw.edu.pl.data.datasource.auth.local.AuthLocalDataSource
import ee.pw.edu.pl.data.model.message.remote.MessageResponse
import ee.pw.edu.pl.data.model.message.remote.SendMessageRequest
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first

private const val MESSAGE_TARGET = "ReceiveMessage"
private const val MESSAGE_METHOD = "SendMessage"

private const val HUB_CHAT_PATH = "hub/chat"

class SignalRChatRemoteDataSource(
    private val context: Context,
    private val authLocalDataSource: AuthLocalDataSource,
) : ChatRemoteDataSource {
    private lateinit var connection: HubConnection
    override fun connectToChat() = callbackFlow {
        val token = authLocalDataSource.getToken().first() ?: return@callbackFlow
        connection =
            HubConnectionBuilder.create("${context.getString(R.string.api_base_url)}$HUB_CHAT_PATH")
                .withAccessTokenProvider(
                    Single.defer { Single.just(token) }
                )
                .build()
        connection.on(
            MESSAGE_TARGET,
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
        connection.invoke(MESSAGE_METHOD, request.receiverId, request.message)
    }
}
