//package app.rest.service
//
//import app.core.entity.TipoDespesaFixa
//import app.core.persistence.TipoDespesaFixaDAO
//import app.rest.data.TipoDespesaFixaReqData
//import app.rest.data.TipoDespesaFixaResData
//import javax.ws.rs.Path
//
//@Path("tipo/fixas")
//open class TipoDespesaFixasREST : CrudREST<TipoDespesaFixa, TipoDespesaFixaReqData, TipoDespesaFixaResData, TipoDespesaFixaDAO>() {
//
//    override fun antesDeExcluir(entidade: TipoDespesaFixa) {
////        if (!PagamentoFixaDAO.instance().buscar(entidade).isEmpty()) violationException.addViolation("Existem pagamentos associados a este tipo de despesa")
//    }
//}