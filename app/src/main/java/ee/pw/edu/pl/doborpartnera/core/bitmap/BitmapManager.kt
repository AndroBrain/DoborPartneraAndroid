package ee.pw.edu.pl.doborpartnera.core.bitmap

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.core.graphics.scale
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import javax.inject.Inject

private val DEFAULT_COMPRESSION_FORMAT = Bitmap.CompressFormat.JPEG
private const val DEFAULT_COMPRESSION_QUALITY = 70

private const val IMAGE_WIDTH = 1080
private const val IMAGE_HEIGHT = 1920

class BitmapManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun compress(
        uri: Uri,
        format: Bitmap.CompressFormat = DEFAULT_COMPRESSION_FORMAT,
        quality: Int = DEFAULT_COMPRESSION_QUALITY,
        width: Int = IMAGE_WIDTH,
        height: Int = IMAGE_HEIGHT,
    ): ByteArray? = try {
        val bitmap = get(uri)
        val baos = ByteArrayOutputStream()

        bitmap.scale(width, height).compress(format, quality, baos)
        baos.toByteArray()
    } catch (e: FileNotFoundException) {
        null
    }

    fun get(uri: Uri): Bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        ImageDecoder.decodeBitmap(
            ImageDecoder.createSource(context.contentResolver, uri),
        )
    } else {
        MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
    }
}