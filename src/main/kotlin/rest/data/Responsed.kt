package rest.data

interface Responsed<in E> {

    fun preencherCom(entidade: E?)
}