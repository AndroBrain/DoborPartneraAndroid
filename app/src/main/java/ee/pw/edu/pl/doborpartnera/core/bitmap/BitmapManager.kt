package ee.pw.edu.pl.doborpartnera.core.bitmap

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import javax.inject.Inject

private val DEFAULT_COMPRESSION_FORMAT = Bitmap.CompressFormat.JPEG
private const val DEFAULT_COMPRESSION_QUALITY = 70

class BitmapManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun compress(
        uri: Uri,
        format: Bitmap.CompressFormat = DEFAULT_COMPRESSION_FORMAT,
        quality: Int = DEFAULT_COMPRESSION_QUALITY,
    ): ByteArray? = try {
        val bitmap = get(uri)
        val baos = ByteArrayOutputStream()
        bitmap.compress(format, quality, baos)
        baos.toByteArray()
    } catch (e: FileNotFoundException) {
        null
    }

    fun get(uri: Uri): Bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        ImageDecoder.decodeBitmap(
            ImageDecoder.createSource(context.contentResolver, uri)
        )
    } else {
        MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
    }
}