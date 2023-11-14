package ee.pw.edu.pl.data.datasource.chat.remote

import android.util.Log
import com.microsoft.signalr.HubConnection
import com.microsoft.signalr.HubConnectionBuilder
import ee.pw.edu.pl.data.datasource.auth.AuthLocalDataSource
import ee.pw.edu.pl.data.model.chat.remote.SendMessageRequest
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first

class SignalRChatRemoteDataSource(
    private val authLocalDataSource: AuthLocalDataSource,
) : ChatRemoteDataSource {
    private lateinit var connection: HubConnection
    override fun connectToChat() = callbackFlow<String> {
        val token = authLocalDataSource.getToken().first() ?: return@callbackFlow
        connection = HubConnectionBuilder.create("http://localhost:8081/hub/chat")
            .withAccessTokenProvider(
                Single.defer { Single.just(token) }
            )
            .build()
        connection.on(
            "ReceiveMessage",
            { receiverId, senderId, message ->
                Log.d("Message", "receiver $receiverId sender $senderId message $message")
                trySend(message)
            },
            Int::class.java,
            Int::class.java,
            String::class.java,
        )
        connection.start()
        awaitClose {
            connection.stop()
        }
    }

    override fun sendMessage(request: SendMessageRequest) {
        Log.d("MessageRequest", request.toString())
        connection.invoke("SendMessage", request.receiverId, request.message)
    }
}
