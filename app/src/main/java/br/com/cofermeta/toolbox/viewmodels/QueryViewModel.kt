package br.com.cofermeta.toolbox.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.cofermeta.toolbox.model.ProductQuery
import br.com.cofermeta.toolbox.model.dataclasses.QueryFields
import br.com.cofermeta.toolbox.model.dataclasses.sankhya
import br.com.cofermeta.toolbox.data.noProductFound

class QueryViewModel : ViewModel() {

    private val _empresa = MutableLiveData(sankhya.codemp)
    private val _codigo = MutableLiveData("")
    private val _marca = MutableLiveData("")
    private val _local = MutableLiveData("")
    private val _descricao = MutableLiveData("")

    val empresa: LiveData<String> = _empresa
    val codigo: LiveData<String> = _codigo
    val marca: LiveData<String> = _marca
    val local: LiveData<String> = _local
    val descricao: LiveData<String> = _descricao

    fun onEmpresaChange(empresa: String) { _empresa.value = empresa }
    fun onCodigoChange(codigo: String) { _codigo.value = codigo }
    fun onMarcaChange(marca: String) { _marca.value = marca }
    fun onLocalChange(local: String) { _local.value = local }
    fun onDescricaoChange(descricao: String) { _descricao.value = descricao }

    fun productQuery(
        context: Context,
        empresa: String,
        codigo: String,
        marca: String,
        localizacao: String,
        descricao: String,
    ) {
        val queryField = QueryFields()

        queryField.codemp = empresa
        queryField.marca = marca
        queryField.codprod = codigo
        queryField.locprin = localizacao
        queryField.descrprod = descricao

        if (queryField.marca.isNotEmpty() ||
            queryField.codprod.isNotEmpty() ||
            queryField.locprin.isNotEmpty() ||
            queryField.descrprod.isNotEmpty()
        ) ProductQuery().tryQuery(context, queryField)
        else Toast.makeText(context, noProductFound, Toast.LENGTH_SHORT).show()
    }
}