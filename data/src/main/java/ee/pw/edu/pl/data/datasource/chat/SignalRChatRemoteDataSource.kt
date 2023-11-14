package ee.pw.edu.pl.data.datasource.chat

import android.util.Log
import com.microsoft.signalr.HubConnection
import com.microsoft.signalr.HubConnectionBuilder
import ee.pw.edu.pl.data.datasource.auth.AuthLocalDataSource
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
            { receiver, sender, message ->
                Log.d("Message", message)
                trySend(message)
            },
            String::class.java,
            String::class.java,
            String::class.java,
        )
        connection.start()
        awaitClose {
            connection.stop()
        }
    }

    override fun sendMessage(message: String) {
        connection.invoke("SendMessage", "alicja@gmail.com", "testowa wiadomość hubowa")
    }
}
