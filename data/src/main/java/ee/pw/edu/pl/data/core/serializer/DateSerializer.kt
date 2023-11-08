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

private const val ISO_8601_PATTERN = "yyyy-MM-dd'T'HH:mm:ss"

@Keep
object DateSerializer : KSerializer<Date> {

    private val dateFormat = SimpleDateFormat(ISO_8601_PATTERN, Locale.US)

    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("Date", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Date {
        val string = decoder.decodeString()
        return try {
            dateFormat.parse(string) ?: Date()
        } catch (pe: ParseException) {
            Date()
        }
    }

    override fun serialize(encoder: Encoder, value: Date) {
        val string = dateFormat.format(value)
        encoder.encodeString(string)
    }
}
