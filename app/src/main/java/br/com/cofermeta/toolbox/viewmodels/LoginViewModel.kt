package br.com.cofermeta.toolbox.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import br.com.cofermeta.toolbox.data.*
import br.com.cofermeta.toolbox.model.Auth
import br.com.cofermeta.toolbox.sankhya

class LoginViewModel: ViewModel() {

    private val auth = Auth()
    private val _user = MutableLiveData(sankhya.user)
    private val _password = MutableLiveData(sankhya.password)
    private val _loading = MutableLiveData(false)

    val user: LiveData<String> = _user
    val password: LiveData<String> = _password
    val loading: LiveData<Boolean> = _loading

    fun onUserChange(user: String) { _user.value = user }
    fun onPasswordChange(password: String) { _password.value = password }
    fun onLoadingChange(newValue: Boolean) { _loading.value = newValue }

    fun login(
        context: Context,
        navController: NavController,
        user: String,
        password: String
    ){
        onLoadingChange(true)
        auth.verifyLogin(context, user, password)
        val statusMessage = sankhya.statusMessage.ifEmpty { EMPTY_LOGIN }
        if (sankhya.jsessionid.isEmpty()) {
            Toast.makeText(context, statusMessage, Toast.LENGTH_SHORT).show()
            onLoadingChange(false)
        } else {
            navController.navigate("query_ui")
            auth.logout()
            Thread.sleep(SLEEP_300)
            onLoadingChange(false)
            Toast.makeText(
                context,
                "Olá ${ sankhya.firstName }!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}