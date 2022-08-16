package br.com.cofermeta.toolbox.ui.components

import android.Manifest
import android.content.Context
import android.content.Context.CAMERA_SERVICE
import android.content.pm.PackageManager
import android.hardware.camera2.CameraManager
import android.util.Size
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraProvider
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.material.icons.rounded.FlashOn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.cofermeta.toolbox.data.defaultPadding
import br.com.cofermeta.toolbox.model.CameraAnalyzer
import br.com.cofermeta.toolbox.viewmodels.QueryViewModel
import dagger.hilt.android.qualifiers.ApplicationContext


@Composable
fun ScannerDialog(
    onShowDialog: (Boolean) -> Unit
) {
    Dialog(onDismissRequest = { onShowDialog(false) }) {
        Toast.makeText(LocalContext.current, "Gire para escanear.", Toast.LENGTH_SHORT).show()
        Surface(
            modifier = Modifier
                .width(250.dp)
                .height(550.dp),
            shape = RoundedCornerShape(defaultPadding)
        ) {
            Scanner({ onShowDialog(it) })
            ScannerHeader { onShowDialog(it) }
        }
    }
}

@Composable
fun ScannerHeader(onShowDialog: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        IconButton(
            onClick = { onShowDialog(false) },
            enabled = true,
        ) {
            Icon(
                imageVector = Icons.Rounded.FlashOn,
                contentDescription = "voltar",
                tint = Color.White,
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
                    .rotate(90F)
            )
        }
        IconButton(
            onClick = { onShowDialog(false) },
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
}


@Composable
fun Scanner(
    onShowDialog: (Boolean) -> Unit,
    queryViewModel: QueryViewModel = viewModel()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val previewView = PreviewView(context)
    val preview = Preview.Builder().build()
    val selector = CameraSelector.Builder()
        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
        .build()

    val imageAnalysis = ImageAnalysis.Builder()
        .setTargetResolution(Size(previewView.width, previewView.height))
        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
        .build()

    var hasCamPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            hasCamPermission = granted
        }
    )
    LaunchedEffect(key1 = true) {
        launcher.launch(Manifest.permission.CAMERA)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        if (hasCamPermission) {
            AndroidView(
                factory = { context ->
                    preview.setSurfaceProvider(previewView.surfaceProvider)
                    imageAnalysis.setAnalyzer(
                        ContextCompat.getMainExecutor(context),
                        CameraAnalyzer { result ->
                            //Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
                            queryViewModel.onReferenciaChange(context, result)
                            onShowDialog(false)
                        }
                    )
                    try {
                        ProcessCameraProvider.getInstance(context).get().unbindAll()
                        ProcessCameraProvider.getInstance(context).get().bindToLifecycle(
                            lifecycleOwner,
                            selector,
                            preview,
                            imageAnalysis
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    previewView
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(defaultPadding, defaultPadding, defaultPadding, 45.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }
    }
}
