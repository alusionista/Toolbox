package br.com.cofermeta.listagem_de_produtos.model

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage

@SuppressLint("UnsafeOptInUsageError")
class BarCodeAnalyser( private val onBarcodeDetected: (barcodes: List<Barcode>) -> Unit): ImageAnalysis.Analyzer {
    override fun analyze(image: ImageProxy) {
        try {
            image.image?.let { imageToAnalyze ->
                val options = BarcodeScannerOptions.Builder()
                    .setBarcodeFormats(Barcode.FORMAT_EAN_13)
                    .build()
                val barcodeScanner = BarcodeScanning.getClient(options)
                val imageToProcess = InputImage.fromMediaImage(imageToAnalyze, image.imageInfo.rotationDegrees)

                barcodeScanner.process(imageToProcess)
                    .addOnSuccessListener { barcodes ->
                        if (barcodes.isNotEmpty()) {
                            onBarcodeDetected(barcodes)
                            image.close()

                        } else {
                            Log.d("TAG", "analyze: No barcode Scanned")
                        }
                    }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            image.close()
        }
    }
}











