package br.com.cofermeta.toolbox.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import br.com.cofermeta.toolbox.model.dataclasses.sankhya
import br.com.cofermeta.toolbox.data.loginEmpty
import br.com.cofermeta.toolbox.model.Auth

class LoginViewModel: ViewModel() {

    private val _user = MutableLiveData("")
    private val _password = MutableLiveData("")

    val user: LiveData<String> = _user
    val password: LiveData<String> = _password

    fun onUserChange(user: String) { _user.value = user }
    fun onPasswordChange(password: String) { _password.value = password }

    fun login(
        context: Context,
        navController: NavController
    ){  val auth = Auth()
        auth.verifyLogin(context, user, password, sankhya)
        //sankhya.jsessionid = fakeJsessionid
        val statusMessage = sankhya.statusMessage.ifEmpty { loginEmpty }
        if (sankhya.jsessionid.isEmpty()) Toast.makeText(
            context,
            statusMessage,
            Toast.LENGTH_SHORT
        ).show()
        else {
            navController.navigate("query_ui")
        }
    }
}