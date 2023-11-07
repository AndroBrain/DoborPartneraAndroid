package ee.pw.edu.pl.data.core.serializer

import androidx.annotation.Keep
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Keep
object DateSerializer : KSerializer<Date> {

    private val dateVersionFormat = SimpleDateFormat("yyyy.MM.dd", Locale.US)

    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("Date", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Date {
        val string = decoder.decodeString()
        return try {
            dateVersionFormat.parse(string) ?: Date()
        } catch (pe: ParseException) {
            Date()
        }
    }

    override fun serialize(encoder: Encoder, value: Date) {
        val string = dateVersionFormat.format(value)
        encoder.encodeString(string)
    }
}
