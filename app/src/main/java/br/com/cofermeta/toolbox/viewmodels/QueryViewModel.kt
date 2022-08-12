package br.com.cofermeta.toolbox.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.camera.core.Preview
import androidx.camera.view.PreviewView
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import br.com.cofermeta.toolbox.model.ProductQuery
import br.com.cofermeta.toolbox.model.dataclasses.QueryFields
import br.com.cofermeta.toolbox.model.dataclasses.sankhya
import br.com.cofermeta.toolbox.data.noProductFound
import br.com.cofermeta.toolbox.data.threadSleep
import br.com.cofermeta.toolbox.model.Auth
import br.com.cofermeta.toolbox.model.dataclasses.QueryResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class QueryViewModel : ViewModel() {

    val queryResult = QueryResult()
    private val auth = Auth()
    private val queryField = QueryFields()
    private val productQuery = ProductQuery()

    private val _empresa = MutableLiveData(sankhya.codemp)
    private val _codigo = MutableLiveData("")
    private val _marca = MutableLiveData("")
    private val _local = MutableLiveData("")
    private val _descricao = MutableLiveData("")
    private val _referencia = MutableLiveData("")
    private val _hasResult = MutableLiveData(false)

    val codemp: LiveData<String> = _empresa
    val codprod: LiveData<String> = _codigo
    val marca: LiveData<String> = _marca
    val locprin: LiveData<String> = _local
    val descrprod: LiveData<String> = _descricao
    val referencia: LiveData<String> = _referencia
    val hasResult: LiveData<Boolean> = _hasResult


    fun onEmpresaChange(empresa: String) { _empresa.value = empresa }
    fun onCodigoChange(codigo: String) { _codigo.value = codigo }
    fun onMarcaChange(marca: String) { _marca.value = marca }
    fun onLocalChange(local: String) { _local.value = local }
    fun onDescricaoChange(descricao: String) { _descricao.value = descricao }
    fun onHasResultChange(newValue: Boolean) { _hasResult.value = newValue }

    fun onReferenciaChange(context: Context, referencia: String) {
        queryField.referencia = referencia
        queryField.codemp = codemp.value.toString()
        queryField.marca = marca.value.toString()
        queryField.codprod = codprod.value.toString()
        queryField.locprin = locprin.value.toString()
        queryField.descrprod = descrprod.value.toString()
        onHasResultChange(false)
        productQuery.tryQuery(context, queryField, queryResult, auth)
        auth.logout(sankhya)
        while (true) {
            if (queryResult.status == "1" || queryResult.statusMessage.isNotEmpty()) break
            Thread.sleep(threadSleep)
        }
        if (queryResult.numberOfRows > 0) {
            onHasResultChange(true)
        } else {
            Toast.makeText(context, noProductFound, Toast.LENGTH_SHORT).show()
        }
        queryField.referencia = ""
    }

    fun productQuery(
        context: Context,
        scope: CoroutineScope,
        state: ScaffoldState,
        empresa: String,
        codigo: String,
        marca: String,
        localizacao: String,
        descricao: String,
    ) {
        queryField.codemp = empresa
        queryField.marca = marca
        queryField.codprod = codigo
        queryField.locprin = localizacao
        queryField.descrprod = descricao

        onHasResultChange(false)
        if (queryField.marca.isNotEmpty() ||
            queryField.codprod.isNotEmpty() ||
            queryField.locprin.isNotEmpty() ||
            queryField.descrprod.isNotEmpty()
        ) {
            productQuery.tryQuery(context, queryField, queryResult, auth)
            auth.logout(sankhya)
            while (true) {
                if (queryResult.status == "1" || queryResult.statusMessage.isNotEmpty()) break
                Thread.sleep(threadSleep)
            }
            scope.launch { state.drawerState.close() }
            if (queryResult.numberOfRows > 0) {
                onHasResultChange(true)
            } else {
                Toast.makeText(context, noProductFound, Toast.LENGTH_SHORT).show()
            }
        } else Toast.makeText(context, noProductFound, Toast.LENGTH_SHORT).show()
        scope.launch { state.drawerState.close() }
    }
}