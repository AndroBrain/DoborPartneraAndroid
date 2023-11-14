package ee.pw.edu.pl.data.datasource.chat

import android.util.Log
import com.microsoft.signalr.HubConnectionBuilder
import ee.pw.edu.pl.data.datasource.auth.AuthLocalDataSource
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first

class SignalRChatRemoteDataSource(
    private val authLocalDataSource: AuthLocalDataSource,
) : ChatRemoteDataSource {
    override fun connectToChat() = callbackFlow<String> {
        val token = authLocalDataSource.getToken().first() ?: return@callbackFlow
        val connection = HubConnectionBuilder.create("http://localhost:8081/hub/chat")
            .withAccessTokenProvider(Single.defer {
                Single.just(token)
            })
            .build()
        connection.on(
            "ReceiveMessage",
            { message ->
                Log.d("Message", message.toString())
                trySend(message)
            },
            String::class.java,
        )
        connection.start()
    }
}
