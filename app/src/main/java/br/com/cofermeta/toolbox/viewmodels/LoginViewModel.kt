package br.com.cofermeta.toolbox.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import br.com.cofermeta.toolbox.data.*
import br.com.cofermeta.toolbox.model.Auth
import br.com.cofermeta.toolbox.model.dataclasses.Sankhya
import br.com.cofermeta.toolbox.model.dataclasses.sankhya

class LoginViewModel: ViewModel() {

    private val _user = MutableLiveData(defaultUser)
    private val _password = MutableLiveData(defaultPassword)

    val user: LiveData<String> = _user
    val password: LiveData<String> = _password

    fun onUserChange(user: String) { _user.value = user }
    fun onPasswordChange(password: String) { _password.value = password }

    private val auth = Auth()

    fun login(
        context: Context,
        navController: NavController,
        user: String,
        password: String
    ){
        auth.verifyLogin(context, user, password, sankhya)
        //sankhya.jsessionid = fakeJsessionid
        val statusMessage = sankhya.statusMessage.ifEmpty { loginEmpty }
        if (sankhya.jsessionid.isEmpty()) Toast.makeText(
            context,
            statusMessage,
            Toast.LENGTH_SHORT
        ).show()
        else {
            Toast.makeText(
                context,
                "Olá ${ sankhya.firstName }!",
                Toast.LENGTH_SHORT
            ).show()
            auth.logout(sankhya)
            navController.navigate("query_ui")
        }
    }
}