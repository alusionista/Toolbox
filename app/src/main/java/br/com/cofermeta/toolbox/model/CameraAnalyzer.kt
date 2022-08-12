package br.com.cofermeta.toolbox.model

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.zxing.*
import com.google.zxing.common.HybridBinarizer
import java.nio.ByteBuffer

class CameraAnalyzer( private val onBarcodeScanned: (String) -> Unit ) : ImageAnalysis.Analyzer {
    override fun analyze(image: ImageProxy) {
        val bytes = image.planes.first().buffer.toByteArray()
        val source = PlanarYUVLuminanceSource(
            bytes,
            image.width,
            image.height,
            0,
            0,
            image.width,
            image.height,
            false
        )
        val binaryBmp = BinaryBitmap(HybridBinarizer(source))

        try {
            val result = MultiFormatReader().apply {
                setHints(
                    mapOf(
                        DecodeHintType.POSSIBLE_FORMATS to arrayListOf(
                            BarcodeFormat.QR_CODE,
                            BarcodeFormat.EAN_13
                        )
                    )
                )
            }.decode(binaryBmp)
            onBarcodeScanned(result.text)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            image.close()
        }
    }

    private fun ByteBuffer.toByteArray(): ByteArray {
        rewind()
        return ByteArray(remaining()).also {
            get(it)
        }
    }
}