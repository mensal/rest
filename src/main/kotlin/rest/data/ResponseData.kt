package rest.data

interface ResponseData<in E> {

    fun preencherCom(entidade: E?)
}