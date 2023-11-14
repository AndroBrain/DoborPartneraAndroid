package ee.pw.edu.pl.data.datasource.chat

import android.util.Log
import com.microsoft.signalr.HubConnectionBuilder

class SignalRChatRemoteDataSource : ChatRemoteDataSource {
    override fun connectToChat() {
        val connection = HubConnectionBuilder.create("http://localhost:8081/hub/chat")
            .build()
        connection.on(
            "ReceiveMessage",
            { message -> Log.d("Message", message.toString()) },
            String::class.java,
        )
        connection.start()
    }
}
