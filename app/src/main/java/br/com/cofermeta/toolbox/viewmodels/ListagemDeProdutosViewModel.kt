package br.com.cofermeta.toolbox.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.cofermeta.toolbox.data.ADD_FILTER
import br.com.cofermeta.toolbox.data.NO_PRODUCT_FOUND
import br.com.cofermeta.toolbox.data.SLEEP_300
import br.com.cofermeta.toolbox.model.Auth
import br.com.cofermeta.toolbox.model.ProductQuery
import br.com.cofermeta.toolbox.model.dataclasses.QueryFields
import br.com.cofermeta.toolbox.model.dataclasses.QueryResult
import br.com.cofermeta.toolbox.sankhya

class ListagemDeProdutosViewModel : ViewModel() {

    private val auth = Auth()
    private val queryField = QueryFields()
    var queryResult = QueryResult()
    private val productQuery = ProductQuery(queryResult)

    private val _empresa = MutableLiveData(sankhya.codemp)
    private val _codigo = MutableLiveData("")
    private val _marca = MutableLiveData("")
    private val _local = MutableLiveData("")
    private val _descricao = MutableLiveData("")
    private val _referencia = MutableLiveData("")
    private val _hasResult = MutableLiveData(false)
    private val _loading = MutableLiveData(false)
    private val _showScanner = MutableLiveData(false)

    private val referencia: LiveData<String> = _referencia
    val codemp: LiveData<String> = _empresa
    val codprod: LiveData<String> = _codigo
    val marca: LiveData<String> = _marca
    val locprin: LiveData<String> = _local
    val descrprod: LiveData<String> = _descricao
    val hasResult: LiveData<Boolean> = _hasResult
    val loading: LiveData<Boolean> = _loading
    val showScanner: LiveData<Boolean> = _showScanner

    fun onEmpresaChange(empresa: String) { _empresa.value = empresa }
    fun onCodigoChange(codigo: String) { _codigo.value = codigo }
    fun onMarcaChange(marca: String) { _marca.value = marca }
    fun onLocalChange(local: String) { _local.value = local }
    fun onDescricaoChange(descricao: String) { _descricao.value = descricao }
    fun onReferenciaChange(referencia: String) { _referencia.value = referencia }
    fun onLoadingChange(newValue: Boolean) { _loading.value = newValue }
    fun onShowScannerChange(newValue: Boolean) { _showScanner.value = newValue }
    private fun onHasResultChange(newValue: Boolean) { _hasResult.value = newValue }

    private fun clearQueryFields() {
        onDescricaoChange("")
        onMarcaChange("")
        onCodigoChange("")
        onLocalChange("")
        queryField.marca = ""
        queryField.codprod = ""
        queryField.locprin = ""
        queryField.descrprod = ""

    }

    private fun updateQueryFields() {
        queryField.codemp = codemp.value.toString()
        queryField.marca = marca.value.toString()
        queryField.codprod = codprod.value.toString()
        queryField.locprin = locprin.value.toString()
        queryField.descrprod = descrprod.value.toString()
        queryField.referencia = referencia.value.toString()

    }

    private fun checkEmptyFields(): Boolean {
        return queryField.marca.isNotEmpty() ||
                queryField.codprod.isNotEmpty() ||
                queryField.locprin.isNotEmpty() ||
                queryField.descrprod.isNotEmpty() ||
                queryField.referencia.isNotEmpty()
    }

    fun onCodeScanned(context: Context, referencia: String) {
        clearQueryFields()
        onReferenciaChange(referencia)
        productQuery(context)
    }

    fun productQuery(context: Context) {
        updateQueryFields()
        if (checkEmptyFields()) { sankhyaRequest(context) }
        else {
            Toast.makeText(context, ADD_FILTER, Toast.LENGTH_SHORT).show()
            onLoadingChange(false)
        }
    }

    private fun sankhyaRequest(context: Context) {
        onLoadingChange(true)
        onHasResultChange(false)
        queryResult = productQuery.tryQuery(context, queryField)
        auth.logout()
        while (true) {
            if (queryResult.status == "1" || queryResult.statusMessage.isNotEmpty()) break
            Thread.sleep(SLEEP_300)
        }
        if (queryResult.numberOfRows > 0) {
            onHasResultChange(true)
        } else if (queryResult.statusMessage.isNotEmpty()){
            Toast.makeText(context, queryResult.statusMessage, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, NO_PRODUCT_FOUND, Toast.LENGTH_SHORT).show()
        }
        onLoadingChange(false)
        queryField.referencia = ""
        onReferenciaChange("")
    }
}