package br.com.cofermeta.toolbox.model

import br.com.cofermeta.toolbox.data.imgPlaceHolder
import com.google.gson.JsonElement
import java.text.NumberFormat
import kotlin.math.roundToInt

abstract class DataFormating {

    private fun formatData(data: JsonElement?) = data.toString().replace("\"", "").trim()
    private fun formatCodprod(codprod: JsonElement?) = "#$codprod"
    private fun formatEndimagem(endimagem: JsonElement?) = if (endimagem.toString().contains("http")) formatData(endimagem) else imgPlaceHolder
    private fun formatMarca(marca: JsonElement?) = if (marca.toString().contains("null")) "Sem marca" else formatData(marca)
    private fun formatVlr(vlr: JsonElement?) = if (!vlr.toString().contains("0")) {
        "R$${formatData(vlr).replace(".", ",")}"
/*        val input = formatData(vlr).toFloat()
        val maskFloat = (input * 100.0).roundToInt() / 100.0
        val rs = maskFloat.toString().replace(".", ",")
        "R$${rs}"*/
    } else "Sem preço"

    fun getCodprod(index: Int, rows: JsonElement?) = formatCodprod(rows?.asJsonArray?.get(index)?.asJsonArray?.get(0))
    fun getMarca(index: Int, rows: JsonElement?) = formatMarca(rows?.asJsonArray?.get(index)?.asJsonArray?.get(1))
    fun getVlrvenda(index: Int, rows: JsonElement?) = formatVlr(rows?.asJsonArray?.get(index)?.asJsonArray?.get(2))
    fun getDescrprod(index: Int, rows: JsonElement?) = formatData(rows?.asJsonArray?.get(index)?.asJsonArray?.get(3))
    fun getEndimagem(index: Int, rows: JsonElement?) = formatEndimagem(rows?.asJsonArray?.get(index)?.asJsonArray?.get(4))
    fun getCodprodleg(index: Int, rows: JsonElement?) = formatData(rows?.asJsonArray?.get(index)?.asJsonArray?.get(5))
    fun getCodvol(index: Int, rows: JsonElement?) = formatData(rows?.asJsonArray?.get(index)?.asJsonArray?.get(6))
    fun getLocprin(index: Int, rows: JsonElement?) = formatData(rows?.asJsonArray?.get(index)?.asJsonArray?.get(7))
    fun getCodprodforn(index: Int, rows: JsonElement?) = formatData(rows?.asJsonArray?.get(index)?.asJsonArray?.get(8))
    fun getRefforn(index: Int, rows: JsonElement?) = formatData(rows?.asJsonArray?.get(index)?.asJsonArray?.get(9))
    fun getReferencia(index: Int, rows: JsonElement?) = formatData(rows?.asJsonArray?.get(index)?.asJsonArray?.get(10))
    fun getDescrgrupoprod(index: Int, rows: JsonElement?) = formatData(rows?.asJsonArray?.get(index)?.asJsonArray?.get(11))
    fun getPesoliq(index: Int, rows: JsonElement?) = formatData(rows?.asJsonArray?.get(index)?.asJsonArray?.get(12))
    fun getOrigprod(index: Int, rows: JsonElement?) = formatData(rows?.asJsonArray?.get(index)?.asJsonArray?.get(13))
    fun getNcm(index: Int, rows: JsonElement?) = formatData(rows?.asJsonArray?.get(index)?.asJsonArray?.get(14))
    fun getCodlocal(index: Int, rows: JsonElement?) = formatData(rows?.asJsonArray?.get(index)?.asJsonArray?.get(15))
    fun getEstoque_reservado(index: Int, rows: JsonElement?) = formatData(rows?.asJsonArray?.get(index)?.asJsonArray?.get(16))
    fun getEstoque(index: Int, rows: JsonElement?) = formatData(rows?.asJsonArray?.get(index)?.asJsonArray?.get(17))
    fun getEstoque_disponivel(index: Int, rows: JsonElement?) = formatData(rows?.asJsonArray?.get(index)?.asJsonArray?.get(18))
    fun getEstmin(index: Int, rows: JsonElement?) = formatData(rows?.asJsonArray?.get(index)?.asJsonArray?.get(19))
    fun getEstmax(index: Int, rows: JsonElement?) = formatData(rows?.asJsonArray?.get(index)?.asJsonArray?.get(20))
    fun getAtivo(index: Int, rows: JsonElement?) = formatData(rows?.asJsonArray?.get(index)?.asJsonArray?.get(21))
    fun getCusger(index: Int, rows: JsonElement?) = formatVlr(rows?.asJsonArray?.get(index)?.asJsonArray?.get(22))
    fun getCusrep(index: Int, rows: JsonElement?) = formatVlr(rows?.asJsonArray?.get(index)?.asJsonArray?.get(23))
    fun getCusvariavel(index: Int, rows: JsonElement?) = formatVlr(rows?.asJsonArray?.get(index)?.asJsonArray?.get(24))
    fun getCodparc_forn(index: Int, rows: JsonElement?) = formatData(rows?.asJsonArray?.get(index)?.asJsonArray?.get(25))
    fun getFornecedor(index: Int, rows: JsonElement?) = formatData(rows?.asJsonArray?.get(index)?.asJsonArray?.get(26))
    fun getNulinha(index: Int, rows: JsonElement?) = formatData(rows?.asJsonArray?.get(index)?.asJsonArray?.get(27))
    fun getDescricao_linha(index: Int, rows: JsonElement?) = formatData(rows?.asJsonArray?.get(index)?.asJsonArray?.get(28))
    fun getCodprodecom(index: Int, rows: JsonElement?) = formatData(rows?.asJsonArray?.get(index)?.asJsonArray?.get(29))
}