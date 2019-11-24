//package app.rest.service
//
//import app.core.entity.TipoDespesaCombustivel
//import app.core.persistence.TipoDespesaCombustivelDAO
//import app.rest.data.TipoDespesaCombustivelReqData
//import app.rest.data.TipoDespesaCombustivelResData
//import javax.ws.rs.Path
//
//@Path("tipo/combustiveis")
//open class TipoDespesaCombustiveisREST : CrudREST<TipoDespesaCombustivel, TipoDespesaCombustivelReqData, TipoDespesaCombustivelResData, TipoDespesaCombustivelDAO>() {
//
//    override fun antesDeExcluir(entidade: TipoDespesaCombustivel) {
////        if (!PagamentoCombustivelDAO.instance().buscar(entidade).isEmpty()) violationException.addViolation("Existem pagamentos associados a este tipo de despesa")
//    }
//}