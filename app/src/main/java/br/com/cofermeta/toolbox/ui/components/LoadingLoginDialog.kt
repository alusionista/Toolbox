package br.com.cofermeta.toolbox.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.cofermeta.toolbox.viewmodels.LoginViewModel

@Composable
fun LoadingLoginDialog(loginViewModel: LoginViewModel = viewModel()) {
    val loading by loginViewModel.loading.observeAsState(true)
    if (loading) {
        Dialog(
            onDismissRequest = { loginViewModel.onLoadingChange(false) },
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
    }
}
