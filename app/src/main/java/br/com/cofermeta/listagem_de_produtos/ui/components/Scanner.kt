package br.com.cofermeta.listagem_de_produtos.ui.components

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.cofermeta.listagem_de_produtos.data.DEFAULT_PADDING
import br.com.cofermeta.listagem_de_produtos.data.NO_CAMERA_PERMISSION
import br.com.cofermeta.listagem_de_produtos.model.BarCodeAnalyser
import br.com.cofermeta.listagem_de_produtos.viewmodels.ListagemDeProdutosViewModel
import com.google.common.util.concurrent.ListenableFuture
import java.util.concurrent.Executors


@Composable
fun ScannerDialog(
    listagemDeProdutosViewModel: ListagemDeProdutosViewModel = viewModel()
) {
    Dialog(onDismissRequest = { listagemDeProdutosViewModel.onShowScannerChange(false) }) {
        Surface(
            modifier = Modifier
                .width(550.dp)
                .height(150.dp),
            shape = RoundedCornerShape(DEFAULT_PADDING),
            color = Color.Transparent
        ) {
            CameraPreview()
            ScannerHeader()
        }
    }
}

@Composable
fun ScannerHeader(listagemDeProdutosViewModel: ListagemDeProdutosViewModel = viewModel()) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Top
    ) {
        IconButton(
            onClick = { listagemDeProdutosViewModel.onShowScannerChange(false) },
            enabled = true,
        ) {
            Icon(
                imageVector = Icons.Rounded.Cancel,
                contentDescription = "voltar",
                tint = Color.White,
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
            )
        }
    }
    Row(
        Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        ProvideTextStyle(value = MaterialTheme.typography.h4) {
            CompositionLocalProvider(
                LocalContentAlpha provides ContentAlpha.high,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    text = "|||| ||| || ||| ||||||"
                )
            }
        }
    }
}

@Composable
fun CameraPreview(listagemDeProdutosViewModel: ListagemDeProdutosViewModel = viewModel()) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val isDialogOpen by listagemDeProdutosViewModel.showScanner.observeAsState()
    val barCodeVal = remember { mutableStateOf("") }
    var preview: Preview


    val camSelector = CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()
    val camExecutor = Executors.newSingleThreadExecutor()
    val camProviderFuture: ListenableFuture<ProcessCameraProvider> = ProcessCameraProvider.getInstance(context)

    val contextCompat = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
    var hasCamPermission by remember { mutableStateOf(contextCompat == PackageManager.PERMISSION_GRANTED) }
    val requestPermission = ActivityResultContracts.RequestPermission()
    val launcher = rememberLauncherForActivityResult( requestPermission) { granted -> hasCamPermission = granted }

    LaunchedEffect(key1 = true) { launcher.launch(Manifest.permission.CAMERA) }

    if (hasCamPermission) {
        AndroidView(
            modifier = Modifier
                .fillMaxSize(),
            factory = { AndroidViewContext ->
                PreviewView(AndroidViewContext).apply {
                    this.scaleType = PreviewView.ScaleType.FILL_CENTER
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                    )
                    implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                }
            },
            update = { previewView ->
                if (isDialogOpen == false) camExecutor.shutdown()

                camProviderFuture.addListener({
                    preview = Preview.Builder().build().also {
                        it.setSurfaceProvider(previewView.surfaceProvider)
                    }
                    val cameraProvider: ProcessCameraProvider = camProviderFuture.get()
                    val barcodeAnalyser = BarCodeAnalyser { barcodes ->
                        barcodes.forEach { barcode ->
                            barcode.rawValue?.let { barcodeValue ->
                                barCodeVal.value = barcodeValue
                                Toast.makeText(context, barcodeValue, Toast.LENGTH_SHORT).show()
                                listagemDeProdutosViewModel.onCodeScanned(context, barcodeValue)
                                listagemDeProdutosViewModel.onShowScannerChange(false)
                                camExecutor.shutdown()
                            }
                        }
                    }
                    val imageAnalysis: ImageAnalysis = ImageAnalysis.Builder()
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build()
                        .also { it.setAnalyzer(camExecutor, barcodeAnalyser) }

                    try {
                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        camSelector,
                        preview,
                        imageAnalysis
                        ).also { it.cameraControl.enableTorch(true)}

                    } catch (e: Exception) {
                        Log.d("TAG", "CameraPreview: ${e.localizedMessage}")
                    }
                }, ContextCompat.getMainExecutor(context))
            }
        )
    } else Toast.makeText(context, NO_CAMERA_PERMISSION, Toast.LENGTH_SHORT).show()
}

