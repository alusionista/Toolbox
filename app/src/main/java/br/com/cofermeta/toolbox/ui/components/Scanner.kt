package br.com.cofermeta.toolbox.ui.components

import android.Manifest
import android.content.pm.PackageManager
import android.util.Size
import android.view.Surface.ROTATION_0
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.cofermeta.toolbox.data.SCANNER
import br.com.cofermeta.toolbox.data.defaultPadding
import br.com.cofermeta.toolbox.model.CameraAnalyzer
import br.com.cofermeta.toolbox.viewmodels.QueryViewModel
import com.google.zxing.PlanarYUVLuminanceSource


@Composable
fun ScannerDialog(
    onShowDialog: (Boolean) -> Unit
) {
    Dialog(onDismissRequest = { onShowDialog(false) }) {
        Surface(
            modifier = Modifier.width(250.dp),
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colors.primaryVariant
        ) {
            Column() {
                Spacer(modifier = Modifier.height(defaultPadding))
                Box(Modifier.height(32.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        ProvideTextStyle(value = MaterialTheme.typography.h5) {
                            CompositionLocalProvider(
                                LocalContentAlpha provides ContentAlpha.high,
                            ) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center,
                                    maxLines = 1,
                                    fontWeight = FontWeight.ExtraBold,
                                    text = SCANNER,
                                    color = Color.White,
                                )
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 10.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        CompositionLocalProvider(
                            LocalContentAlpha provides ContentAlpha.high,
                        ) {
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
                }
                Spacer(modifier = Modifier.height(defaultPadding))
                Scanner({onShowDialog(it)})
                Spacer(modifier = Modifier.height(defaultPadding))

            }

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
                    .fillMaxWidth()
                    .padding(horizontal = defaultPadding)
                    .height(550.dp)
                    .clip(RoundedCornerShape(15.dp))
            )
        }
    }
}
